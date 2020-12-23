package io.sicredi.pautavoting.api.service;

import io.sicredi.pautavoting.api.dto.PautaVotingDTO;
import io.sicredi.pautavoting.api.model.PautaVoting;
import io.sicredi.pautavoting.api.repository.PautaVotingRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PautaVotingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PautaVotingService.class);

    private final PautaVotingRepository repository;
    private final ModelMapper mapper;

    public PautaVotingService(PautaVotingRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PautaVotingDTO> findAll() {
        Collection<PautaVoting> pautasVoting = repository.findAll();
        return pautasVoting.stream().map(pauta -> mapper.map(pauta, PautaVotingDTO.class)).collect(Collectors.toList());
    }
}