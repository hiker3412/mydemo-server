package io.hiker.server.api.projectreactor;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxTest {

    @Test
    void just() {
        Flux.just("foo", "bar").subscribe(System.out::println);
    }

    @Test
    void counterFlux() throws InterruptedException {
        Flux.interval(Duration.ofMillis(100))
                .take(10)
                .subscribe(System.out::println);
        //创建的是异步流，主线程结束则订阅会中断，需要主线程等待订阅完成；
        Thread.sleep(1100);
    }

    @Test
    void errorFlux() {
        Flux.error(new IllegalStateException("error")).subscribe(System.out::println);
    }

    @Test
    void emptyFlux() {
        Flux.empty().subscribe(System.out::println);
    }

    @Test
    void neverFlux() {
        Flux.never().subscribe();
    }

    @Test
    void concatWithFlux() {
        Flux.just("foo", "bar")
                .concatWith(Flux.just("foo2", "bar2"))
                .subscribe(System.out::println);
    }

    @Test
    void onErrorReturnTest() {
        Mono.just("element1")
                .doOnNext((s) -> {
                    throw new IllegalStateException("");
                })
                .onErrorReturn("fallback to element1")
                .subscribe(System.out::println);
    }

    @Test
    void onErrorResumeTest() {
        Mono.just("element1")
                .doOnNext((s) -> {
                    throw new IllegalStateException("");
                })
                .onErrorResume(s -> Mono.just("fallback to origin Mono:" + "fallback element2"))
                .subscribe(System.out::println);
    }
    @Test
    void testScheduler() {
        Flux.range(1, 3)
                .doOnNext(
                        data -> System.out.println(
                                "线程" + Thread.currentThread().getName() + "发送元素：" + data + "，给operator"
                        )
                )
                // 指定订阅在(Schedulers.elastic())调度器上执行
                .subscribeOn(Schedulers.boundedElastic())
                .map(
                        integer -> {
                            System.out.println(
                                    "线程" + Thread.currentThread().getName() + "执行operator，处理元素：" + integer
                            );
                            return integer * 2;
                        }
                )
                // 改变下游操作的执行调度器到(Schedulers.single())
                .publishOn(Schedulers.single())
                .doOnNext(
                        transformedData -> System.out.println(
                                "线程" + Thread.currentThread().getName() + "发送元素：" + transformedData + "，给consumer"
                        )
                )
                .blockLast();
    }


}
