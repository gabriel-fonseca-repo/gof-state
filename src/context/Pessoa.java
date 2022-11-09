package context;

import states.State;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

public class Pessoa {

	private String nome;

	private String cpf;

	private LocalDate dataNascimento;

	private BufferedImage documento;

	private State state;

	public Pessoa(String nome, String cpf, LocalDate dataNascimento, BufferedImage documento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.documento = documento;
	}

	public void initializeState(State state) {
		this.setState(state);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public BufferedImage getDocumento() {
		return documento;
	}

	public void setDocumento(BufferedImage documento) {
		this.documento = documento;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
