package com.cqi.controls.util.mail;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;

/**
 * Classe Bean CDI que cria objetos p/ envio de e-mail
 * @author cqfb
 *
 */

@RequestScoped
public class Mailer implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * SessionConfig: Classe de configuração da sessão de envio de e-mail
	 */
	@Inject
	private SessionConfig config;
	
	/**
	 * @return MailMessageImpl que é a implementação padrão da interface MailMessage
	 */
	public MailMessage novaMensagem() {
		return new MailMessageImpl(this.config);
	}
	
}
