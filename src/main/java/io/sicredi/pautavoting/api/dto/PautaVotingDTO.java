package io.sicredi.pautavoting.api.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PautaVotingDTO implements Serializable {
    private static final long serialVersionUID = 8541415916777341305L;

    private String id;
    private String pautaId;
    private LocalDateTime createdAt;

    private List<PautaVotingAnswerDTO> answers;
}