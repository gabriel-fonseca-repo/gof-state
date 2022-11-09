package states;

import context.Pessoa;
import utils.CadastroUtil;

public class DadosEnviadosState extends State {

	public DadosEnviadosState(Pessoa stateful) {
		super(stateful, "Envio de dados cadastrais");
	}

	@Override
	public void executarPassoCadastro() {

		exibirPasso("Enviando os dados");
		boolean resultado = CadastroUtil.enviarApi("/cadastro?cpf=" + getStateful().getCpf());

		if (resultado) {
			exibirPasso("Dados enviados com sucesso");
		} else {
			exibirPasso("Erro no envio dos dados");
		}

		setStateComplete(resultado);

	}

	@Override
	public void changeState() {
		getStateful().setState(new DadosValidadosState(getStateful()));
	}

}
