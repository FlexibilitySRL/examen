package ar.com.plug.examen.domain.validator;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.utils.FieldValidator;

public class ClientNameValidator  implements Validator<ClientApi>{

	@Override
	public String validate(ClientApi value) {
		
		return FieldValidator.validateRequired("name",value.getName());
	}

}
