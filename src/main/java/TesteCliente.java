import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cqi.controls.model.Cliente;
import com.cqi.controls.model.Endereco;
import com.cqi.controls.model.TipoPessoa;

/**
 * @author cqfb
 */
public class TesteCliente {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ControlsPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Karina de Fatima Quintiliano");
		cliente.setEmail("arinda@luiza.com");
		cliente.setDocumentoReceitaFederal("123.123.123-12");
		cliente.setTipo(TipoPessoa.FISICA);
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua dos Passaros");
		endereco.setNumero("111");
		endereco.setCidade("Londrina");
		endereco.setUf("PR");
		endereco.setCep("38400-000");
		endereco.setCliente(cliente);
		
		cliente.getEnderecos().add(endereco);
		
		manager.persist(cliente);
		
		trx.commit();
		
		
	}
	
	

}
