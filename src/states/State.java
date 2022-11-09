package states;

import context.Pessoa;

public abstract class State {

	private final String stateLabel;
	private Pessoa stateful;
	private boolean stateComplete;

	public State(Pessoa stateful, String stateLabel) {
		this.stateful = stateful;
		this.stateLabel = stateLabel;
		this.stateComplete = false;
	}

	public State(String stateLabel) {
		this.stateLabel = stateLabel;
		this.stateComplete = false;
	}

	public void exibirPasso(String passo) {
		System.out.println(getStateLabel() + " - " + passo + "...");
	}

	public void irAoProximoPasso() {
		exibirPasso("Prosseguindo ao próximo passo");
		if (stateComplete) {
			changeState();
		} else {
			exibirPasso("Erro no procedimento de passo do cadastro");
		}
	}

	public abstract void executarPassoCadastro();

	protected abstract void changeState();

	public Pessoa getStateful() {
		return stateful;
	}

	public void setStateful(Pessoa stateful) {
		this.stateful = stateful;
	}

	public String getStateLabel() {
		return stateLabel;
	}

	public void setStateComplete(boolean stateComplete) {
		this.stateComplete = stateComplete;
	}
}
