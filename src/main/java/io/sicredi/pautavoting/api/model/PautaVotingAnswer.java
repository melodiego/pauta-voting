package io.sicredi.pautavoting.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Document(value = "pauta_voting_answer")
public class PautaVotingAnswer implements Serializable {
    private static final long serialVersionUID = 8352721072558868285L;

    @Id
    private String id;

    @Indexed
    private String cpf;
    private String answer;
    private LocalDateTime createdAt = LocalDateTime.now();
}