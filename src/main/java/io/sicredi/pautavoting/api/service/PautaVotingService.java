package io.sicredi.pautavoting.api.service;

import io.sicredi.pautavoting.api.dto.PautaVotingDTO;
import io.sicredi.pautavoting.api.dto.form.PautaVotingAnswerFormDTO;
import io.sicredi.pautavoting.api.enums.UserInfoStatusEnum;
import io.sicredi.pautavoting.api.exception.BadRequestException;
import io.sicredi.pautavoting.api.integration.dto.UserInfoDTO;
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
    public static final String UNABLE_TO_VOTE_WITH_THIS_CPF = "Unable to vote with this CPF ";

    private final PautaVotingRepository repository;
    private final ModelMapper mapper;
    private final PautaVotingAnswerService answerService;
    private final UserInfoService userInfoService;

    public PautaVotingService(PautaVotingRepository repository, ModelMapper mapper,
                              PautaVotingAnswerService answerService, UserInfoService userInfoService) {
        this.repository = repository;
        this.mapper = mapper;
        this.answerService = answerService;
        this.userInfoService = userInfoService;
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
        UserInfoDTO userInfoDTO = userInfoService.getByCpf(pautaVotingAnswerFormDTO.getCpf());

        if(!isAbleToVote(userInfoDTO.getStatus())) {
            throw new BadRequestException(UNABLE_TO_VOTE_WITH_THIS_CPF + pautaVotingAnswerFormDTO.getCpf());
        }

        Optional<PautaVoting> pautaVoting = repository.findById(id);

        if(pautaVoting.isEmpty()) {
            throw new BadRequestException(CANNOT_FIND_ANY_REGISTRY_WITH_THIS_ID + id);
        }

        PautaVotingAnswer pautaVotingAnswer = answerService.save(pautaVotingAnswerFormDTO);
        pautaVoting.get().addAnswer(pautaVotingAnswer);

        repository.save(pautaVoting.get());
        return mapper.map(pautaVoting.get(), PautaVotingDTO.class);
    }

    private boolean isAbleToVote(String status) {
        return UserInfoStatusEnum.ABLE_TO_VOTE.getStatus().equalsIgnoreCase(status);
    }
}