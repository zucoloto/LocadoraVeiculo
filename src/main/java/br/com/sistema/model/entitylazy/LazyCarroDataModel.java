package br.com.sistema.model.entitylazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.sistema.model.dao.CarroDAO;
import br.com.sistema.model.entity.Carro;

public class LazyCarroDataModel extends LazyDataModel<Carro> implements Serializable {

	private static final long serialVersionUID = 1L;

	private CarroDAO carroDAO;

	public LazyCarroDataModel(CarroDAO carroDAO) {
		this.carroDAO = carroDAO;
	}

	@Override
	public List<Carro> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Carro> carros = this.carroDAO.buscarComPaginacao(first, pageSize);

		this.setRowCount(this.carroDAO.encontrarQuantidadeDeCarros().intValue());

		return carros;
	}
}
