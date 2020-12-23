package io.sicredi.pautavoting.api.controller;

import io.sicredi.pautavoting.api.dto.PautaVotingDTO;
import io.sicredi.pautavoting.api.dto.form.PautaVotingAnswerFormDTO;
import io.sicredi.pautavoting.api.service.PautaVotingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("{pautaId}/createSessionPautaVoting")
    public ResponseEntity<Void> createSessionPautaVoting(@PathVariable("pautaId") @Valid String pautaId) {
        service.createSessionPautaVoting(pautaId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("{id}/addPautaVotingAnswer")
    public ResponseEntity<PautaVotingDTO> addPautaVotingAnswer(@PathVariable("id") @Valid String id,
                                                               @RequestBody @Valid PautaVotingAnswerFormDTO pautaVotingAnswerFormDTO) {
        return new ResponseEntity<>(service.addAnswerPautaVoting(id, pautaVotingAnswerFormDTO), HttpStatus.CREATED);
    }
}