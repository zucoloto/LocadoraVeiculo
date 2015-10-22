package br.com.sistema.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.sistema.model.dao.MotoristaDAO;
import br.com.sistema.model.entity.Motorista;

@FacesConverter(forClass = Motorista.class)
public class MotoristaConverter implements Converter {

	@Inject
	private MotoristaDAO motoristaDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Motorista retorno = null;
		if (value != null && !value.equals("")) {
			retorno = motoristaDAO.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Motorista retorno = (Motorista) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}
}
