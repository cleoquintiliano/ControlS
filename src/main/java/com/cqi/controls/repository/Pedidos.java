package com.cqi.controls.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.cqi.controls.model.Pedido;
import com.cqi.controls.model.Usuario;
import com.cqi.controls.model.valueobject.DataValor;
import com.cqi.controls.repository.filter.PedidoFilter;


/**
 * @author cqfb
 *
 */

public class Pedidos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	@SuppressWarnings({ "unchecked" })
	public Map<Date, BigDecimal> valoresTotaisPorData(Integer numeroDeDias, Usuario criadoPor) {
		Session session = manager.unwrap(Session.class);
		
		numeroDeDias -= 1;
		
		Calendar dataInicial = Calendar.getInstance();
		//metodo truncate elimida as horas da data
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		//classe Calendar apenas add, para remover (retroagir dias) foi inserido um valor negativo.
		dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);
		
		Map<Date, BigDecimal> resultado = criarMapaVazio(numeroDeDias, dataInicial);
		
		Criteria criteria = session.createCriteria(Pedido.class);
		
		/*
		* Projections é uma fábrica para instâncias de Projection. 
		* Pode aplicar uma projeção à uma consulta, chamando o setProjection().
		* Não tendo a necessidade de um "agrupamento por" explícito em uma consulta por critério. 
		* Um alias pode ser atribuído de forma opcional à uma projeção, 
		* assim o valor projetado pode ser referenciado em restrições (where) ou ordenações.
		* Metodo "ge" (maior ou igual)
		 
		* select date(data_criacao) as data, sum(valor_total) as valor 
		* from pedido where data_criacao >= :dataInicial and vendedor_id = :criadoPor 
		* group by date(data_criacao) */
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.sqlGroupProjection("date(data_criacao) as data", 
						"date(data_criacao)", new String[] { "data" }, 
						new Type[] { StandardBasicTypes.DATE } ))
				.add(Projections.sum("valorTotal").as("valor"))
			)
			.add(Restrictions.ge("dataCriacao", dataInicial.getTime()));//getTime do Calendar retorna um date
		
		if (criadoPor != null) {
			criteria.add(Restrictions.eq("vendedor", criadoPor));
		}
		//.setResultTransformer transforma o resultado da consulta em objeto (DataValor)
		List<DataValor> valoresPorData = criteria
				.setResultTransformer(Transformers.aliasToBean(DataValor.class)).list();
		
		for (DataValor dataValor : valoresPorData) {
			//"put" add o par de chaves requerido no Map.
			resultado.put(dataValor.getData(), dataValor.getValor());
		}
		
		return resultado;
	}

	/**
	 * Metodo serve de base para os dados vindos do banco, 
	 * tendo todos os dias em sequencia (o que pode não acontecer no banco 
	 * caso exista dias sem venda por exemplo)
	 * @param numeroDeDias
	 * @param dataInicial
	 * @return mapaInicial
	 */
	private Map<Date, BigDecimal> criarMapaVazio(Integer numeroDeDias,
			Calendar dataInicial) {
		dataInicial = (Calendar) dataInicial.clone();//clone é usado p/ não interferir no objeto já instanciado
		Map<Date, BigDecimal> mapaInicial = new TreeMap<>();

		for (int i = 0; i <= numeroDeDias; i++) {
			mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return mapaInicial;
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> filtrados(PedidoFilter filtro) {
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Pedido.class)
				// faz uma associação (join) com cliente e nomeamos como "c"
				.createAlias("cliente", "c")
				// faz uma associação (join) com vendedor e nomeamos como "v"
				.createAlias("vendedor", "v");
		
		if (filtro.getNumeroDe() != null) {
			// id deve ser maior ou igual (ge = maior ou igual) a filtro.numeroDe
			criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
		}

		if (filtro.getNumeroAte() != null) {
			// id deve ser menor ou igual (le = menor ou igual) a filtro.numeroDe
			criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
		}

		if (filtro.getDataCriacaoDe() != null) {
			criteria.add(Restrictions.ge("dataCriacao", filtro.getDataCriacaoDe()));
		}
		
		if (filtro.getDataCriacaoAte() != null) {
			criteria.add(Restrictions.le("dataCriacao", filtro.getDataCriacaoAte()));
		}
		
		if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
			// acessa o nome do cliente associado ao pedido pelo alias "c", criado anteriormente
			criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filtro.getNomeVendedor())) {
			// acessa o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
			criteria.add(Restrictions.ilike("v.nome", filtro.getNomeVendedor(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
			// adiciona uma restrição "in", passando um array de constantes da enum StatusPedido
			criteria.add(Restrictions.in("status", filtro.getStatuses()));
		}
		
		return criteria.addOrder(Order.asc("id")).list();
	}
	
	public Pedido guardar(Pedido pedido) {
		return this.manager.merge(pedido);
	}
	
	public Pedido porId(Long id) {
		return this.manager.find(Pedido.class, id);
	}
	
}