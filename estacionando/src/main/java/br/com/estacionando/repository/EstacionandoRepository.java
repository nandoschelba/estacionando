package br.com.estacionando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estacionando.model.Veiculo;

//repository criado para estender ao repository do JPA, trazendo vários métodos
@Repository
public interface EstacionandoRepository extends JpaRepository<Veiculo, Long>{
	
	Veiculo findByPlaca(String placa);
	

}
