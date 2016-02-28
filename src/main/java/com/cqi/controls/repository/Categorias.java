package com.cqi.controls.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.cqi.controls.model.Categoria;

/**
 * @author cqfb
 */
public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}

	//Query JPQL (Java Persistence Query Language) que retorna objetos (não colunas)
	public List<Categoria> raizes() {
		return manager.createQuery("from Categoria", Categoria.class).getResultList();
	}

}
