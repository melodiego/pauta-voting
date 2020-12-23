package io.sicredi.pautavoting.api.integration;

import io.sicredi.pautavoting.api.integration.dto.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${client.url.user-info}", name = "user-info")
public interface UserInfoClient {

    @GetMapping("/users/{cpf}")
    ResponseEntity<UserInfoDTO> getByCpf(@PathVariable String cpf);
}