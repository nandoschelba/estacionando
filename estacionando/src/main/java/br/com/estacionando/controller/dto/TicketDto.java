package br.com.estacionando.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.estacionando.model.Ticket;
import br.com.estacionando.model.Veiculo;

public class TicketDto {

	private Long id;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime horaEntrada = LocalDateTime.now();
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime horaSaida;
	private Double valor;
	private Veiculo veiculo;

	public TicketDto(Ticket ticket) {
		this.id = ticket.getId();
		this.horaEntrada = ticket.getHoraEntrada();
		this.horaSaida = ticket.getHoraSaida();
		this.valor = ticket.getValor();
		this.veiculo = ticket.getVeiculo();
	}

	public Long getId() {
		return id;
	}

	public void setHoraEntrada(LocalDateTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public void setHoraSaida(LocalDateTime horaSaida) {
		this.horaSaida = horaSaida;
	}

	public LocalDateTime getHoraSaida() {
		return horaSaida;
	}
	
	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}

	public Double getValor() {
		return valor;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public static List<TicketDto> converter(List<Ticket> tickets) {
		return tickets.stream().map(TicketDto::new).collect(Collectors.toList());
	}

}
