package ar.com.plug.examen.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( value="Cliente" )
public class Cliente extends Persona{

}
