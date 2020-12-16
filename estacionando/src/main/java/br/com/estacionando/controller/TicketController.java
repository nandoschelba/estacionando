package br.com.estacionando.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estacionando.controller.dto.TicketDto;
import br.com.estacionando.controller.form.TicketForm;
import br.com.estacionando.model.Ticket;
import br.com.estacionando.repository.PagamentoRepository;
import br.com.estacionando.repository.TicketRepository;

@RestController
@RequestMapping(value = "/estacionando")
public class TicketController {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@GetMapping("/tickets")
	public List<TicketDto> listaTickets(String placa) {
		if (placa == null) {
			List<Ticket> tickets = ticketRepository.findAll();
			return TicketDto.converter(tickets);
		} else {
			List<Ticket> tickets = ticketRepository.findByVeiculoPlaca(placa);
			return TicketDto.converter(tickets);
		}

	}

	@PostMapping("/ticket")
	public ResponseEntity<Ticket> salvaTicket(@RequestBody @Valid TicketForm form, UriComponentsBuilder uriBuilder) {
		Ticket ticket = form.converter();
		ticketRepository.save(ticket);

		URI uri = uriBuilder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();
		return ResponseEntity.created(uri).body(ticket);
	}

	@DeleteMapping("/ticket")
	public void deletaVeiculo(@RequestBody Ticket ticket) {
		ticketRepository.delete(ticket);
	}

	@PutMapping("/ticket")
	public Ticket atualizaTicket(@RequestBody Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@PutMapping("/encerrar/{placa}")
	@Transactional
	public ResponseEntity<TicketDto> gerarPagamento(@PathVariable String placa){
		Ticket ticket = pagamentoRepository.findByVeiculoPlaca(placa);
		ticket.calcularPagamento();
		return ResponseEntity.ok(new TicketDto(ticket));
	}
	
	@GetMapping("/relatorio")
	public Double getForecastTotals() {
		return ticketRepository.selectTotal();
	}
}
