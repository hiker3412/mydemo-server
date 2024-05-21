package io.hiker.server.api.projectreactor;

import io.hiker.server.api.model.User;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Locale;

public class Operator {
    @Test
    void map () {
        Mono<User> mono = Mono.just(User.SKYLER);
        mono.map(u -> new User(u.getUsername().toUpperCase(),u.getFirstname().toUpperCase(),u.getLastname().toLowerCase()));
    }

    String capitalize(String str) {
        return str .replaceFirst(".",str.substring(0,1));
    }

    @Test
    void flatMap() throws InterruptedException {
        Flux.just(1,2,3,4,5).flatMap(i -> Mono.just(i + "s")).subscribe(System.out::println);
        Thread.sleep(1000);
    }

    @Test
    void interleave() throws InterruptedException {
        Flux<Integer> flux1 = Flux.just(1, 2, 3, 4);
        Flux<Integer> flux2 = Flux.just(6, 7, 8, 9);
        Flux.merge(flux1,flux2).subscribe(System.out::println);
    }

    @Test
    void delayInterleave() throws InterruptedException {
        Flux<Long> flux1 = Flux.interval(Duration.ofSeconds(1)).take(5);
        Flux<Long> flux2 = Flux.interval(Duration.ofSeconds(5)).take(5);

        Flux.merge(flux1,flux2).subscribe(System.out::println);

        Thread.sleep(10 * 1000);
    }
}
