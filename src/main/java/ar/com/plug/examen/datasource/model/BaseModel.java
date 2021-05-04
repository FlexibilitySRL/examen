package ar.com.plug.examen.datasource.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BaseModel {

    @javax.persistence.Id
    @GeneratedValue
    @ToString.Include
    @EqualsAndHashCode.Include
    Long id;

    @Temporal(TemporalType.TIMESTAMP)
    Date deleted;

}
