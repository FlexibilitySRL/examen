package ar.com.plug.examen.domain.validator;

import java.util.ArrayList;
import java.util.List;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.utils.FieldValidator;

public class ProductIdValidator implements Validator<List<ProductApi>>{

	
	public String validate(ProductApi value) {
		return FieldValidator.validateRequired("id",value.getId());

	}
	@Override
	public String validate(List<ProductApi> values) {
		List<String> results = new ArrayList<>();
		for(ProductApi productApi : values) {
			String result = validate(productApi);
			if(result != null)
				results.add(result);
		}
		return !results.isEmpty() ? results.toString(): null;

	}

}
