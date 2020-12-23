package io.sicredi.pautavoting.api.service;

import io.sicredi.pautavoting.api.dto.form.PautaVotingAnswerFormDTO;
import io.sicredi.pautavoting.api.model.PautaVotingAnswer;
import io.sicredi.pautavoting.api.repository.PautaVotingAnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class PautaVotingAnswerService {

    private final PautaVotingAnswerRepository repository;

    public PautaVotingAnswerService(PautaVotingAnswerRepository repository) {
        this.repository = repository;
    }

    public PautaVotingAnswer save(PautaVotingAnswerFormDTO pautaVotingAnswerFormDTO) {
        PautaVotingAnswer pautaVotingAnswer = generatePautaVotingAnswer(pautaVotingAnswerFormDTO);
        return repository.save(pautaVotingAnswer);
    }

    private PautaVotingAnswer generatePautaVotingAnswer(PautaVotingAnswerFormDTO pautaVotingAnswerFormDTO) {
        PautaVotingAnswer pautaVotingAnswer = new PautaVotingAnswer();
        pautaVotingAnswer.setAnswer(pautaVotingAnswerFormDTO.getAnswer().toUpperCase());
        pautaVotingAnswer.setCpf(pautaVotingAnswerFormDTO.getCpf());

        return pautaVotingAnswer;
    }
}
