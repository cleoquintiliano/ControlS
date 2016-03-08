package com.cqi.controls.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cqi.controls.model.Produto;
import com.cqi.controls.repository.filter.ProdutoFilter;
import com.cqi.controls.service.NegocioException;
import com.cqi.controls.util.jpa.Transactional;

/**
 * @author cqfb
 */

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	/**
	 * Metodo "merge" do EntityManager insere ou atualiza;
	 * 
	 * @param produto
	 * @return instancia do produto persistido
	 */
	public Produto guardar(Produto produto) {
		return manager.merge(produto);

	}

	/**
	 * "porId" conecta para persistencia / "remove" apenas seleciona para
	 * remoção /"flush" executa pendencias de execução (remove) / em caso de
	 * erro na persistencia (flush) como FK por exemplo lança NegocioException
	 * 
	 * @param produto
	 */
	@Transactional
	public void remover(Produto produto) {
		try {
			produto = porId(produto.getId());
			manager.remove(produto);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser excluído.");
		}
	}

	/**
	 * Metodo verifica se o SKU já existe
	 * 
	 * @param sku
	 * @return null
	 */
	public Produto porSku(String sku) {
		try {
			return manager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * "unwrap" Retorna um objeto do tipo especificado para permitir o acesso à
	 * API específica do fornecedor (Hibernate) "Criteria" permite construir
	 * consultas estruturadas ( API de consulta por critério) "Restrictions"
	 * define os métodos da fábrica para obter certos tipos de criterios pré
	 * fabricados. "eq" equivale a equals , "ilike" é um case-insensitive "like"
	 * (LIKE procura uma string padrão com %) / "MatchMode" insere os "%" no
	 * like na posição desejada (ANYWHERE) / @SuppressWarnings("unchecked") foi
	 * necessario pois a list retornada não está tipada
	 * 
	 * @param filtro
	 * @return criteria com lista de produtos em ordem ascendente (nome)
	 */

	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);

		if (StringUtils.isNotBlank(filtro.getSku())) {
			criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
	}

}
