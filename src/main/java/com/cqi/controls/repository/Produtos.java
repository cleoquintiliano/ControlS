package com.cqi.controls.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.cqi.controls.model.Produto;

/**
 * @author cqfb
 */

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Produto guardar(Produto produto) {
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		// Metodo "merge" do EntityManager insere ou atualiza;
		//"produto" recebe a instancia já persistida que foi passada como parametro ao metodo
		produto = manager.merge(produto);

		trx.commit();

		return produto;
	}
	//Verifica se o SKU já existe
	public Produto porSku(String sku) {
		try {
			return manager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
