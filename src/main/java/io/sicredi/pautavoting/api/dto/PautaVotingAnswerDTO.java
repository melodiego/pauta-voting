package io.sicredi.pautavoting.api.dto;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PautaVotingAnswerDTO implements Serializable {

    private String cpf;
    private String answer;
}
