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

import com.cqi.controls.model.Cliente;
import com.cqi.controls.repository.filter.ClienteFilter;
import com.cqi.controls.service.NegocioException;
import com.cqi.controls.util.jpa.Transactional;

/**
 * @author cqfb
 */

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	/**
	 * Metodo "merge" do EntityManager insere ou atualiza;
	 * 
	 * @param usuario
	 * @return instancia do usuario persistido
	 */
	public Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);

	}

	/**
	 * "porId" conecta para persistencia / "remove" apenas seleciona para
	 * remoção /"flush" executa pendencias de execução (remove) / em caso de
	 * erro na persistencia (flush) como FK por exemplo lança NegocioException
	 * 
	 * @param usuario
	 */
	@Transactional
	public void remover(Cliente cliente) {
		try {
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cliente não pode ser excluído.");
		}
	}

	/**
	 * Metodo verifica se o documentoReceitaFederal já existe
	 * 
	 * @param nome
	 * @return null
	 */
	public Cliente porDocumentoReceitaFederal(String documentoReceitaFederal) {
		try {
			return manager
					.createQuery("from Cliente where upper(documentoReceitaFederal) = :documentoReceitaFederal",
							Cliente.class)
					.setParameter("documentoReceitaFederal", documentoReceitaFederal.toUpperCase()).getSingleResult();
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
	 * @return criteria com lista de usuários em ordem ascendente (nome)
	 */

	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (StringUtils.isNotBlank(filtro.getDocumentoReceitaFederal())) {
			criteria.add(Restrictions.ilike("documentoReceitaFederal", filtro.getDocumentoReceitaFederal(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}

}
