package ar.com.flexibility.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Base object for all business domain classes
 */
@Data
@MappedSuperclass
public abstract class BaseObject implements Serializable {

	private static final long serialVersionUID = -5168400955536562592L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	protected boolean deleted;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	protected Date creationDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	protected Date lastUpdate;

}
