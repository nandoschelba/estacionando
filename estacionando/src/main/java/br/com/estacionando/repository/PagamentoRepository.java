package br.com.estacionando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estacionando.model.Ticket;

public interface PagamentoRepository extends JpaRepository<Ticket, Long> {

	Ticket findByVeiculoPlaca(String placa);

}
