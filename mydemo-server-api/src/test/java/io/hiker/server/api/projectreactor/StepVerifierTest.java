package io.hiker.server.api.projectreactor;

import io.hiker.server.api.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class StepVerifierTest {
    @Test
    void expectString() {
        Flux<String> flux = Flux.just("foo", "bar")
                .concatWith(Mono.error(new RuntimeException()));

        StepVerifier.create(flux).expectNext("foo").expectNext("bar")
                .verifyError(RuntimeException.class);
    }

    @Test
    void expectUser() {
        Flux<User> flux = Flux.just(User.SKYLER, User.JESSE);
        StepVerifier.create(flux)
                .assertNext(u -> Assertions.assertThat(u.getUsername()).isEqualTo("swhite"))
                .assertNext(u -> Assertions.assertThat(u.getUsername()).isEqualTo("jpinkman"))
                .verifyComplete();
    }

    @Test
    void create() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("create");

        Flux<Long> flux = Flux.interval(Duration.ofSeconds(1)).take(3);
        //流操作和验证操作都在真实时间环境中进行，阻塞验证
        StepVerifier.create(flux)
                .expectNextCount(3)
                .verifyComplete();

        stopWatch.stop();
        //用时 3s 多
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    @Test
    void withVirtualTime() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("withVirtualTime");

        StepVerifier
                //让流操作在虚拟时间环境中进行
                .withVirtualTime(() -> Flux.interval(Duration.ofSeconds(1)).take(3))
                //等待虚拟时间环境的流操作完成，虚拟时间可控制(时间加速)，因此会马上完成，相当于非阻塞验证
                .thenAwait(Duration.ofSeconds(3))
                //验证操作在真实时间环境中进行
                .expectNextCount(3)
                .verifyComplete();

        stopWatch.stop();
        //立即完成
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    @Test
    void delay() {
        //没有任何输出
        Mono.delay(Duration.ofSeconds(3)).subscribe(System.out::println);
        //触发一次 onNext事件,没有任何输出
        StepVerifier.withVirtualTime(() -> Mono.delay(Duration.ofSeconds(3)))
                .thenAwait(Duration.ofSeconds(3))
                .expectNextCount(1) //触发一次 onNext事件
                .expectComplete()
                .verify();
    }

    @Test
    void requestAndCancel() {
        Flux<User> flux = Flux.just(User.SKYLER, User.JESSE);
        StepVerifier stepVerifier = thenCancel(flux);
        stepVerifier.verify();
    }

    StepVerifier thenCancel(Flux<User> flux) {
        return StepVerifier.create(flux)
                .thenRequest(1) //thenRequest 和 thenCancel 配合使用，
                .expectNext(User.SKYLER)
                .thenRequest(1)
                .expectNext(User.JESSE)
                .thenCancel(); //必须使用 thenCancel 取消 request
    }

    @Test
    void requestAndExpectTimeout() {
        Flux<Integer> flux = Flux.range(1, 2)
                .concatWith(Mono.never()); //expectTimeout必须不能发送任何信号
        StepVerifier stepVerifier = expectTimeout(flux);
        stepVerifier.verify();
    }

    StepVerifier expectTimeout(Flux<Integer> flux) {
        return StepVerifier.create(flux)
                .thenRequest(1) //thenRequest 和 expectTimeout 配合使用，
                .expectNext(1)
                .thenRequest(1)
                .expectNext(2)
                .expectTimeout(Duration.ofSeconds(1));  //必须使用 expectTimeout取消 request;
    }

}
