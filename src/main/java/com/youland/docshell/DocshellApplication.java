package com.youland.docshell;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class DocshellApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocshellApplication.class, args);
    }

    @RestController
    public static class HeathCheckController {

        private static final String TEMPLATE = "%s v%s. Ping %d! %s";
        private final AtomicLong counter = new AtomicLong();

        @Value("${app.version}")
        private String appVersion;

        @Value("${app.name}")
        private String appName;

        @GetMapping
        public String ping() {
            return String.format(
                    TEMPLATE, this.appName, this.appVersion, counter.incrementAndGet(), Instant.now().toString());
        }
    }

}
