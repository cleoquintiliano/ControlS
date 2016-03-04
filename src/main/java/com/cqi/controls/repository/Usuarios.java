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

import com.cqi.controls.model.Usuario;
import com.cqi.controls.repository.filter.ProdutoFilter;
import com.cqi.controls.service.NegocioException;
import com.cqi.controls.util.jpa.Transactional;

/**
 * @author cqfb
 */

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	/**
	 * Metodo "merge" do EntityManager insere ou atualiza;
	 * 
	 * @param usuario
	 * @return instancia do usuario persistido
	 */
	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);

	}

	/**
	 * "porId" conecta para persistencia / "remove" apenas seleciona para
	 * remoção /"flush" executa pendencias de execução (remove) / em caso de
	 * erro na persistencia (flush) como FK por exemplo lança NegocioException
	 * 
	 * @param usuario
	 */
	@Transactional
	public void remover(Usuario usuario) {
		try {
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Usuário não pode ser excluído.");
		}
	}

	/**
	 * Metodo verifica se o EMAIL já existe
	 * 
	 * @param nome
	 * @return null
	 */
	public Usuario porEmail(String email) {
		try {
			return manager.createQuery("from Usuario where upper(email) = :email", Usuario.class)
					.setParameter("email", email.toUpperCase()).getSingleResult();
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
	public List<Usuario> filtrados(ProdutoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);

		if (StringUtils.isNotBlank(filtro.getSku())) {
			criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}

}
