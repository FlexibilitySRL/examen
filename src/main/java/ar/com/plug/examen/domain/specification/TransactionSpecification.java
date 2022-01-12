package ar.com.plug.examen.domain.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ar.com.plug.examen.domain.model.Transaction;

public class TransactionSpecification implements Specification<Transaction> {

	private static final long serialVersionUID = 4581197563918768722L;
	private Transaction filter;

	public TransactionSpecification(Transaction filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate p = criteriaBuilder.disjunction();

		if (filter.getStatus() != null) {
			p.getExpressions().add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
		}
		
		return p;
	}

}
