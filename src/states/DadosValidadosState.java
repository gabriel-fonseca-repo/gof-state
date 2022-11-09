package states;

import context.Pessoa;

public class DadosValidadosState extends State {
	public DadosValidadosState(Pessoa stateful) {
		super(stateful, "Validação de dados cadastrais");
	}

	@Override
	public void executarPassoCadastro() {

		exibirPasso("Validando os dados");
		boolean resultado = getStateful().getCpf().length() == 14;

		if (resultado) {
			exibirPasso("Dados validados com sucesso");
		} else {
			exibirPasso("Dados invalidos, abortando");
		}

		setStateComplete(resultado);

	}

	@Override
	public void changeState() {
		getStateful().setState(new DocumentosEnviadosState(getStateful()));
	}
}
