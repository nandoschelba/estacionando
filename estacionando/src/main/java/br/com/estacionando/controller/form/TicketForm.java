package br.com.estacionando.controller.form;

import br.com.estacionando.model.Ticket;
import br.com.estacionando.model.Veiculo;

public class TicketForm {

	private Veiculo veiculo;

	public TicketForm(Veiculo veiculo) {
		super();
		this.veiculo = veiculo;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public TicketForm() {
	}

	public Ticket converter() {
		return new Ticket(veiculo);
	}
}
