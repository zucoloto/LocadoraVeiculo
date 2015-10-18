package br.com.sistema.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.sistema.model.dao.CarroDAO;
import br.com.sistema.model.entity.Carro;

@FacesConverter(forClass = Carro.class)
public class CarroConverter implements Converter {

	@Inject
	private CarroDAO carroDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Carro retorno = null;
		if (value != null && !value.equals("")) {
			retorno = carroDAO.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Carro retorno = (Carro) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}
}
