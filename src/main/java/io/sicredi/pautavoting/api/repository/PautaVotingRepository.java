package io.sicredi.pautavoting.api.repository;

import io.sicredi.pautavoting.api.model.PautaVoting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaVotingRepository extends MongoRepository<PautaVoting, String> {

}