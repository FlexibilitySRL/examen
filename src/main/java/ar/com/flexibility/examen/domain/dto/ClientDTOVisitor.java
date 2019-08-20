package ar.com.flexibility.examen.domain.dto;

public interface ClientDTOVisitor<R> {
	public R visit(LegalClientDTO clientDTO);
	public R visit(NaturalClientDTO clientDTO);
}
