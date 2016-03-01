package com.cqi.controls.util.jpa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 *         A anotação javax.transaction.Transactional fornece o aplicativo a
 *         capacidade de controlar de forma declarativa limites de transação no
 *         CDI managed beans, bem como classes definidas como beans gerenciados
 *         pela especificação Java EE, tanto a nível de classe e método onde
 *         anotações nível de método substituir os que estão em o nível de
 *         classe. Este apoio é prestado através de uma aplicação de
 *         interceptores CDI que regem a suspensão necessário, retomar, etc. O
 *         interceptor transacional interpõe em invocações de métodos de negócio
 *         e não apenas em eventos de ciclo de vida. métodos de ciclo de vida
 *         são invocados em um contexto de transação não especificado.
 *         
 *         (Interceptador configurado em /ControlS/src/main/resources/META-INF/beans.xml)
 *         
 *         @author cqfb
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Transactional {

}
