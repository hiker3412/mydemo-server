package io.hiker.server.api.projectreactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class StepVerifierTest {
    @Test
    void expectFooBarComplete() {
        Flux<String> flux = Flux.just("foo", "bar");
        StepVerifier.create(flux).expectNext("foo").expectNext("bar").verifyComplete();
    }

    @Test
    void expectFooBarError() {
        Flux<String> flux = Flux.just("foo", "bar")
                .concatWith(Mono.error(new RuntimeException()));

        StepVerifier.create(flux).expectNext("foo").expectNext("bar")
                .verifyError(RuntimeException.class);
    }

    @Test
    void expectFooBarError2() {
        Flux.just("foo", "bar")
                .concatWith(Flux.just("foo2", "bar2"))
                .subscribe(System.out::println);
    }
}
