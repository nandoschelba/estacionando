package br.com.estacionando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.estacionando.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	List<Ticket> findByVeiculoPlaca(String placa);
	
	@Query(value = "SELECT SUM(valor) FROM tb_ticket", nativeQuery = true)
	Double selectTotal();
}
