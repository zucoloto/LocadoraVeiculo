package br.com.sistema.exemplos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.sistema.model.entity.Aluguel;
import br.com.sistema.model.entity.Carro;
import br.com.sistema.model.entity.Carro_;
import br.com.sistema.model.entity.ModeloCarro;
import br.com.sistema.model.entity.ModeloCarro_;

public class ExemplosCriteria {

	private static EntityManagerFactory factory;

	private EntityManager manager;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}

	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();
	}

	@After
	public void tearDown() {
		this.manager.close();
	}

	/* 8.3. Projeções */
	@Test
	public void projecoes() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(carro.<String> get("placa"));

		TypedQuery<String> query = manager.createQuery(criteriaQuery);
		List<String> placas = query.getResultList();

		for (String placa : placas) {
			System.out.println(placa);
		}
	}

	/* 8.4. Funções de agregação */
	/* Shift + Alt + x = executa apenas teste selecionado */
	@Test
	public void funcoesDeAgregacao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);

		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);
		criteriaQuery.select(builder.sum(aluguel.<BigDecimal> get("valorTotal")));

		TypedQuery<BigDecimal> query = manager.createQuery(criteriaQuery);
		BigDecimal total = query.getSingleResult();

		System.out.println("funcoesDeAgregacao");
		System.out.println("Soma de todos os alugueis: " + total);
	}

	/* 8.5. Resultados complexos, tuplas e construtores */
	@Test
	public void resultadoComplexo() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.multiselect(carro.get("placa"), carro.get("valorDiaria"));

		TypedQuery<Object[]> query = manager.createQuery(criteriaQuery);
		List<Object[]> resultado = query.getResultList();

		System.out.println("resultadoComplexo");
		for (Object[] valores : resultado) {
			System.out.println(valores[0] + " - " + valores[1]);
		}
	}

	@Test
	public void resultadoTupla() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.multiselect(carro.get("placa").alias("placaCarro"), carro.get("valorDiaria").alias("valorCarro"));

		TypedQuery<Tuple> query = manager.createQuery(criteriaQuery);
		List<Tuple> resultado = query.getResultList();

		System.out.println("resultadoTupla");
		for (Tuple tupla : resultado) {
			System.out.println(tupla.get("placaCarro") + " - " + tupla.get("valorCarro"));
		}
	}

	@Test
	public void resultadoConstrutores() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PrecoCarro> criteriaQuery = builder.createQuery(PrecoCarro.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(builder.construct(PrecoCarro.class, carro.get("placa"), carro.get("valorDiaria")));

		TypedQuery<PrecoCarro> query = manager.createQuery(criteriaQuery);
		List<PrecoCarro> resultado = query.getResultList();

		System.out.println("resultadoConstrutores");
		for (PrecoCarro precoCarro : resultado) {
			System.out.println(precoCarro.getPlaca() + " - " + precoCarro.getValor());
		}
	}

	/* 8.6. Funções */
	@Test
	public void exemploFuncao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Predicate predicate = builder.equal(builder.upper(carro.<String> get("cor")), "prata".toUpperCase());

		criteriaQuery.select(carro);
		criteriaQuery.where(predicate);

		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();

		System.out.println("exemploFuncao");
		for (Carro c : carros) {
			System.out.println(c.getPlaca() + " - " + c.getCor());
		}
	}

	/* 8.7. Ordenação de resultado */
	@Test
	public void exemploOrdenacao() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Order order = builder.desc(carro.get("valorDiaria"));

		criteriaQuery.select(carro);
		criteriaQuery.orderBy(order);

		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();

		for (Carro c : carros) {
			System.out.println(c.getPlaca() + " - " + c.getValorDiaria());
		}
	}

	/* 8.8. Join e Fetch */
	@Test
	public void exemploJoinEFetch() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Join<Carro, ModeloCarro> modelo = (Join) carro.fetch("modelo");

		criteriaQuery.select(carro);
		criteriaQuery.where(builder.equal(modelo.get("descricao"), "Siena"));

		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();

		for (Carro c : carros) {
			System.out.println(c.getPlaca());
		}
	}

	/* 8.9. Subqueries */
	@Test
	public void mediaDasDiariasDosCarros() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(builder.avg(carro.<Double> get("valorDiaria")));

		TypedQuery<Double> query = manager.createQuery(criteriaQuery);
		Double total = query.getSingleResult();

		System.out.println("Média da diária: " + total);
	}

	@Test
	public void carrosComValoresAcimaDaMedia() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Subquery<Double> subquery = criteriaQuery.subquery(Double.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Root<Carro> carroSub = subquery.from(Carro.class);

		subquery.select(builder.avg(carroSub.<Double> get("valorDiaria")));

		criteriaQuery.select(carro);
		criteriaQuery.where(builder.greaterThanOrEqualTo(carro.<Double> get("valorDiaria"), subquery));

		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();

		for (Carro c : carros) {
			System.out.println(c.getPlaca() + " - " + c.getValorDiaria());
		}
	}

	/* 8.10. Metamodel */
	@Test
	public void exemploMetamodel() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Join<Carro, ModeloCarro> modelo = (Join) carro.fetch(Carro_.modelo);

		criteriaQuery.select(carro);
		criteriaQuery.where(builder.equal(modelo.get(ModeloCarro_.descricao), "Siena"));

		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();

		for (Carro c : carros) {
			System.out.println(c.getPlaca() + " - " + c.getModelo().getDescricao());
		}
	}

}
