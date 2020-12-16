package br.com.estacionando.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column
	private LocalDateTime horaEntrada = LocalDateTime.now();

	@Column
	private LocalDateTime horaSaida;
	private Double valor;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Veiculo veiculo;

	private boolean status;

	public Ticket() {
	}

	public Ticket(Ticket ticket) {
		this.veiculo = ticket.getVeiculo();
		this.valor = 0.0;
	}

	public Ticket(Veiculo veiculo) {
		super();
		this.veiculo = veiculo;
		this.valor = 0.0;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalDateTime dataHoraEntrada) {
		this.horaEntrada = dataHoraEntrada;
	}

	public LocalDateTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(LocalDateTime dataHoraSaida) {
		this.horaSaida = dataHoraSaida;
	}

	public Double calcularPagamento() {
		float duracao;
		this.horaSaida = LocalDateTime.now(ZoneId.of("GMT-3"));
		duracao = ChronoUnit.HOURS.between(this.horaEntrada, this.horaSaida);
		this.status = true;
		if (duracao < 1) {
			valor = 10.0;
		} else {
			float horas = duracao - 1;
			valor = (horas * 2) + 10.0;
		}
		return valor;
	}

}
