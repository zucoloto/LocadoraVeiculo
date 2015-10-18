package br.com.sistema.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.sistema.model.dao.ModeloCarroDAO;
import br.com.sistema.model.entity.ModeloCarro;

@FacesConverter(forClass = ModeloCarro.class)
public class ModeloCarroConverter implements Converter {

	@Inject
	private ModeloCarroDAO modeloDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ModeloCarro retorno = null;
		if (value != null && !value.equals("")) {
			retorno = modeloDAO.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			ModeloCarro retorno = (ModeloCarro) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}
}
