package ar.com.flexibility.examen.domain.model;

public interface ClientVisitor<R> {
	public R visit(NaturalClient client);
	public R visit(LegalClient client);
}
