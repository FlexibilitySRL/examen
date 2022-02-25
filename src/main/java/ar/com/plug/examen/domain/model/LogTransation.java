package ar.com.plug.examen.domain.model;


import ar.com.plug.examen.domain.enums.Result;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogTransation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLogTransation;
    @Column
    @CreationTimestamp
    private LocalDateTime dateTransation;
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    private Result Result;
    @Column
    private String module;
}
