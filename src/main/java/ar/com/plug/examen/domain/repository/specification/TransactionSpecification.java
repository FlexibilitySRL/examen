package ar.com.plug.examen.domain.repository.specification;

import java.util.Date;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.enums.StatusEnum;
import ar.com.plug.examen.domain.model.Transaction;

public final class TransactionSpecification {

	private static final String LIKE_FILTER = "%%%s%%";
	
	/** Each specification validates the filter data exists and adds it as a filter condition **/
	
	public static Specification<Transaction> especificacionClientId(ClientApi client) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			if (Objects.nonNull(client) && Objects.nonNull(client.getId())) {
				return criteriaBuilder.equal(root.join("client").get("id"), client.getId());
			}
			return null;
		};
	}
	
	public static Specification<Transaction> especificacionClientName(ClientApi client) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			if (Objects.nonNull(client) && Strings.isNotEmpty(client.getName())) {
				return criteriaBuilder.like(root.join("client").get("name"), String.format(LIKE_FILTER, client.getName()));
			}
			return null;
		};
	}
	
	public static Specification<Transaction> especificacionSellerId(SellerApi seller) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			if (Objects.nonNull(seller) && Objects.nonNull(seller.getId())) {
				return criteriaBuilder.equal(root.join("seller").get("id"), seller.getId());
			}
			return null;
		};
	}
	
	public static Specification<Transaction> especificacionSellerName(SellerApi seller) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			if (Objects.nonNull(seller) && Strings.isNotEmpty(seller.getName())) {
				return criteriaBuilder.like(root.join("seller").get("name"), String.format(LIKE_FILTER, seller.getName()));
			}
			return null;
		};
	}
	
	public static Specification<Transaction> especificacionStatus(StatusEnum status) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			if (Objects.nonNull(status)) {
				return criteriaBuilder.equal(root.get("status"), status);
			}
			return null;
		};
	}
	
	public static Specification<Transaction> especificacionDate(Date date) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			if (Objects.nonNull(date)) {
				return criteriaBuilder.equal(root.get("date"), date);
			}
			return null;
		};
	}
}
