package io.hiker.server.api.projectreactor;

import io.hiker.server.api.model.User;
import org.junit.jupiter.api.Test;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class OperatorTest {

    @Test
    void map() {
        Mono<User> mono = Mono.just(User.SKYLER);
        mono.map(u -> new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toLowerCase()))
                .subscribe(System.out::println);
    }

    @Test
    void flatMap() throws InterruptedException {
        Flux.just(1, 2, 3, 4, 5).flatMap(i -> Mono.just(i + "s")).subscribe(System.out::println);
        Thread.sleep(1000);
    }

    @Test
    void merge() {
        Flux<Long> flux1 = Flux.interval(Duration.ofSeconds(1)).take(5).map(l -> l + 5);
        Flux<Long> flux2 = Flux.interval(Duration.ofSeconds(1)).take(5);

        Flux.merge(flux1, flux2)
                .doOnNext(System.out::println) //先执行，后传播：先执行 Consumer，后传播 onNext 信号；
                .blockLast(); //无限期阻塞，直到上游发出最后一个元素或发出完成信号；
    }

    @Test
    void concat() {
        Flux<Long> flux1 = Flux.interval(Duration.ofSeconds(1)).take(5).map(l -> l + 5);
        Flux<Long> flux2 = Flux.interval(Duration.ofSeconds(1)).take(5);

        Flux.concat(flux1, flux2)
                .doOnNext(System.out::println) //先执行，后传播：先执行 Consumer，后传播 onNext 信号；
                .blockLast(); //无限期阻塞，直到上游发出最后一个元素或发出完成信号；
    }

    @Test
    void capitalizeMany() {
        Flux.just(User.JESSE, User.SAUL, User.SKYLER, User.WALTER)
                .map(
                        user -> {
                            try {
                                return capitalizeUser(user);
                            } catch (GetOutOfHereException e) {
                                e.printStackTrace();
                                return new User("test", "test", "test");
                            }
                        }
                )
                .subscribe(System.out::println);
    }

    Flux<User> capitalizeMany2(Flux<User> flux) {
        return flux.map(
                user -> {
                    try {
                        return capitalizeUser(user);
                    } catch (Exception e) {
                        throw Exceptions.propagate(e);
                    }
                }
        );
    }

    User capitalizeUser(User user) throws GetOutOfHereException {
        if (user.equals(User.SAUL)) {
            throw new GetOutOfHereException();
        }
        return new User(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    protected final class GetOutOfHereException extends Exception {
        private static final long serialVersionUID = 0L;
    }
}
