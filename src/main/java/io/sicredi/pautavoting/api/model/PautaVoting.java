package io.sicredi.pautavoting.api.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}