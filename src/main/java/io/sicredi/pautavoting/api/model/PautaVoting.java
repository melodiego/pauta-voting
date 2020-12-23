package io.sicredi.pautavoting.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "pauta_voting")
public class PautaVoting implements Serializable {
    private static final long serialVersionUID = 8364157077004392287L;

    @Id
    private String id;
    @Indexed
    private String pautaId;
    @DBRef
    private List<PautaVotingAnswer> answers;

    private LocalDateTime createdAt = LocalDateTime.now();

    public void addAnswer(PautaVotingAnswer pautaVotingAnswer) {
        if(answers == null) {
            answers = new ArrayList<>();
        }

        answers.add(pautaVotingAnswer);
    }
}