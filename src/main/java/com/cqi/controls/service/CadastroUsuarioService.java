package com.cqi.controls.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.cqi.controls.model.Usuario;
import com.cqi.controls.repository.Usuarios;
import com.cqi.controls.util.jpa.Transactional;

/**
 * @author cqfb
 */

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	/**	
	 * Metodo que salva apenas se NOME ainda não existe (NOME deve ser único) ou se for uma edição
	 * @param usuario
	 * @return 
	 */
	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario usuarioExistente = usuarios.porNome(usuario.getNome());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario))  {
			throw new NegocioException("Já existe um usuário com o NOME informado.");
		}

		return usuarios.guardar(usuario);

	}

}
