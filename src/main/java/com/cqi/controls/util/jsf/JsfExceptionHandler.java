package com.cqi.controls.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;
	
	public JsfExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}
	
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}
	
	@Override
	// Iterator fornece uma forma de acessar seqüencialmente os elementos de uma coleção sem expor sua representação interna
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();
		 
		while (events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			// Busca o contexto da exceção (Evento que causou a exceção)
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			
			Throwable exception = context.getException();
			
			try {
				if (exception instanceof ViewExpiredException) {
					redirect("/");
				}
			} finally {
				events.remove();
			}
		}
		//Finaliza o trabalho pegando tratamento  pai (JSF)
		getWrapped().handle();
	}
	// Busca a raiz do contexto do erro da aplicação
	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();
	
			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

}