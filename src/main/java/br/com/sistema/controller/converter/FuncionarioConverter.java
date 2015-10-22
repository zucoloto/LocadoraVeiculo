package br.com.sistema.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.sistema.model.dao.FuncionarioDAO;
import br.com.sistema.model.entity.Funcionario;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {

	@Inject
	private FuncionarioDAO funcionarioDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcionario retorno = null;
		if (value != null && !value.equals("")) {
			retorno = funcionarioDAO.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Funcionario retorno = (Funcionario) value;
			return retorno.getId() == null ? null : retorno.getId().toString();
		}
		return null;
	}
}
