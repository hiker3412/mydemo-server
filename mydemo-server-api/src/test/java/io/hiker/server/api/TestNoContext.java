package io.hiker.server.api;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class TestNoContext {
    @Test
    void monoTest() {
        Mono.firstWithValue(
                Mono.empty(),
                Mono.delay(Duration.ofMillis(100)).thenReturn("bar"),
                Mono.just(1),
                Mono.delay(Duration.ofMillis(100)).thenReturn("bar")
        ).subscribe(System.out::println);

        Mono.delay(Duration.ofMillis(100)).thenReturn("bar").subscribe(System.out::println);
    }
}
