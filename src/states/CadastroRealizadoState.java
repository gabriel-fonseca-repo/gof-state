package states;

import context.Pessoa;

public class CadastroRealizadoState extends State {
	public CadastroRealizadoState(Pessoa stateful) {
		super(stateful, "Finalização do cadastro");
	}

	@Override
	public void executarPassoCadastro() {
		exibirPasso("Cadastro realizado com sucesso");
	}

	@Override
	protected void changeState() {
		exibirPasso("Não há mais passos, cadastro finalizado");
	}

}
