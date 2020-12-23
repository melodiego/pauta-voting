package io.sicredi.pautavoting.api.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${client.url.pauta-info}", name = "pauta-info")
public interface PautaInfoClient {

    @PostMapping("/{id}/existsPautaById")
    ResponseEntity<Boolean> existsPautaById(@PathVariable String id);
}