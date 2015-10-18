package br.com.sistema.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.sistema.model.dao.FabricanteDAO;
import br.com.sistema.model.entity.Fabricante;

@FacesConverter(forClass = ModeloEntity.class)
public class ModeloConverter implements Converter {

	@Inject
	private ModeloDAO modeloDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ModeloEntity retorno = null;
		if (value != null && !value.equals("")) {
			retorno = modeloDAO.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			ModeloEntity retorno = (ModeloEntity) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}
}
