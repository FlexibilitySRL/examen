package ar.com.plug.examen.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( value="Vendedor" )
public class Vendedor extends Persona{

}
