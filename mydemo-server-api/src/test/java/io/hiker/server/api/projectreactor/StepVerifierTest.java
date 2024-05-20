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
}
