package io.sicredi.pautavoting.api.controller;

import io.sicredi.pautavoting.api.dto.PautaVotingDTO;
import io.sicredi.pautavoting.api.service.PautaVotingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/pauta-voting")
public class PautaVotingController {

    private final PautaVotingService service;

    public PautaVotingController(PautaVotingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<PautaVotingDTO>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
