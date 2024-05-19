package io.hiker.server.api;


import com.fasterxml.jackson.databind.ser.Serializers;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.asn1.pkcs.CertificationRequestInfo;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.Security;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class FluxTest {


    void emptyFlux() {
        Flux.empty().subscribe(System.out::println);
    }

    @Test
    void just() {
        //创建的是一个立即完成的流，静态数据流，在主线程中执行
        Flux.just("foo", "bar").subscribe(System.out::println);
    }

    @Test
    void fromIterable() {
        //创建的是一个立即完成的流，静态数据流，在主线程中执行
        List<String> list = new ArrayList();
        list.add("foo");
        list.add("bar");
        Flux.fromIterable(list).subscribe(System.out::println);
    }


    @Test
    void error() {
        Flux.error(new IllegalStateException("error")).subscribe(System.out::println);
    }

    @Test
    void counter() throws InterruptedException {
        Flux.interval(Duration.ofMillis(100))
                .take(10)
                .subscribe(System.out::println);
        //创建的是异步流，主线程结束则订阅会中断；
        Thread.sleep(1100);
    }

    @Test
    void never() {
        Flux.never().subscribe(System.out::println);
    }


}
