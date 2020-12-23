package io.sicredi.pautavoting.api.service;

import io.sicredi.pautavoting.api.dto.PautaVotingDTO;
import io.sicredi.pautavoting.api.dto.form.PautaVotingAnswerFormDTO;
import io.sicredi.pautavoting.api.exception.BadRequestException;
import io.sicredi.pautavoting.api.model.PautaVoting;
import io.sicredi.pautavoting.api.model.PautaVotingAnswer;
import io.sicredi.pautavoting.api.repository.PautaVotingRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PautaVotingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PautaVotingService.class);

    public static final String CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID = "Cannot find any registry with this ID ";

    private final PautaVotingRepository repository;
    private final ModelMapper mapper;
    private PautaVotingAnswerService answerService;

    public PautaVotingService(PautaVotingRepository repository, ModelMapper mapper, PautaVotingAnswerService answerService) {
        this.repository = repository;
        this.mapper = mapper;
        this.answerService = answerService;
    }

    public List<PautaVotingDTO> findAll() {
        Collection<PautaVoting> pautasVoting = repository.findAll();
        return pautasVoting.stream().map(pauta -> mapper.map(pauta, PautaVotingDTO.class)).collect(Collectors.toList());
    }

    public void createSessionPautaVoting(String pautaId) {
        PautaVoting pautaVoting = new PautaVoting();
        pautaVoting.setPautaId(pautaId);
        repository.save(pautaVoting);
    }

    public PautaVotingDTO addAnswerPautaVoting(String id, PautaVotingAnswerFormDTO pautaVotingAnswerFormDTO) {
        Optional<PautaVoting> pautaVoting = repository.findById(id);

        if(pautaVoting.isEmpty()) {
            throw new BadRequestException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id);
        }

        PautaVotingAnswer pautaVotingAnswer = answerService.save(pautaVotingAnswerFormDTO);
        pautaVoting.get().addAnswer(pautaVotingAnswer);

        repository.save(pautaVoting.get());
        return mapper.map(pautaVoting.get(), PautaVotingDTO.class);
    }
}