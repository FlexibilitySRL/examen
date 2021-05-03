package ar.com.plug.examen.datasource.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IdModel {

    @javax.persistence.Id
    @GeneratedValue
    @ToString.Include
    @EqualsAndHashCode.Include
    Long id;

}
