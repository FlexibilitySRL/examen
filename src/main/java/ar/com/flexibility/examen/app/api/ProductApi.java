package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductApi {
	
	long id;
	String code;
	String name;
	String unitMeasure;
	String observations;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnitMeasure() {
		return unitMeasure;
	}
	public void setUnitMeasure(String unitMeasure) {
		this.unitMeasure = unitMeasure;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}	
}
