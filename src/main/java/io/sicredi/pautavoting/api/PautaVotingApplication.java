package io.sicredi.pautavoting.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PautaVotingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PautaVotingApplication.class, args);
    }
}