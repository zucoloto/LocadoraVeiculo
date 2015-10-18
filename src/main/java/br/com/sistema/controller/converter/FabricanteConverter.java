package br.com.sistema.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.sistema.model.dao.FabricanteDAO;
import br.com.sistema.model.entity.Fabricante;

@FacesConverter(forClass = Fabricante.class)
public class FabricanteConverter implements Converter {

	@Inject
	private FabricanteDAO fabricanteDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Fabricante retorno = null;
		if (value != null && !value.equals("")) {
			retorno = fabricanteDAO.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Fabricante retorno = (Fabricante) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}
}
