package com.cqi.controls.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.cqi.controls.model.Pedido;
import com.cqi.controls.util.jsf.FacesUtil;
import com.cqi.controls.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

/**
 * @author cqfb
 *
 */

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public void enviarPedido() {
		MailMessage message = mailer.novaMensagem();
		
		message.to(this.pedido.getCliente().getEmail())
		.subject("Pedido " + this.pedido.getId())
		.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/pedido.template")))
		//"put" define uma variavel no contexto do Velocity
		.put("pedido", this.pedido)
		//instancia classe utilitária de formatacao de números
		.put("numberTool", new NumberTool())
		.put("locale", new Locale("pt", "BR"))
		.send();
		
		FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}
	
	
}
