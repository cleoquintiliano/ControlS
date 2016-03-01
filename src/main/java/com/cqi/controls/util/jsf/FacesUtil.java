package com.cqi.controls.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author cqfb
 */

public class FacesUtil {
	/*Verifica se é o contexto é Postback (quanto a página inteira e seus conteúdo são enviados para o servidor 
	para processamento de algumas informações e, em seguida, o servidor mostra a mesma página de volta ao navegador.*/
	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}
	
	public static boolean isNotPostback() {
		return !isPostback();
	}

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
}