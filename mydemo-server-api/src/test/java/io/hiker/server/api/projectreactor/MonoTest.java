package io.hiker.server.api.projectreactor;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MonoTest {

    @Test
    void never() {
        Mono.never();
    }
}
