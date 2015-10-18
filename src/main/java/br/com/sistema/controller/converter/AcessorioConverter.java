package br.com.sistema.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.sistema.model.dao.AcessorioDAO;
import br.com.sistema.model.entity.Acessorio;

@FacesConverter("acessorioConverter")
public class AcessorioConverter implements Converter {

	@Inject
	private AcessorioDAO acessorioDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Acessorio retorno = null;
		if (value != null && !value.equals("")) {
			retorno = acessorioDAO.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Acessorio retorno = (Acessorio) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}
}
