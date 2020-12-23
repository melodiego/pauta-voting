package io.sicredi.pautavoting.api.dto.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PautaVotingAnswerFormDTO implements Serializable {
    private static final long serialVersionUID = -3657656120950166904L;

    @NotBlank(message = "The pautaId could not be null or empty.")
    private String pautaId;
    @NotBlank(message = "The cpf could not be null or empty.")
    private String cpf;
    @NotBlank(message = "The answer could not be null or empty.")
    private String answer;
}