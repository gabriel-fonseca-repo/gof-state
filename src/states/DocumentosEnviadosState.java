package states;

import context.Pessoa;
import utils.CadastroUtil;

public class DocumentosEnviadosState extends State {
	public DocumentosEnviadosState(Pessoa stateful) {
		super(stateful, "Envio de documentos cadastrais");
	}

	@Override
	public void executarPassoCadastro() {

		exibirPasso("Enviando o documento");
		boolean resultado = CadastroUtil.enviarApi("/cadastro/documentos", getStateful().getDocumento());

		if (resultado) {
			exibirPasso("Dados validados com sucesso");
		} else {
			exibirPasso("Dados invalidos, abortando");
		}

		setStateComplete(resultado);

	}

	@Override
	public void changeState() {
		getStateful().setState(new DocumentosValidadosState(getStateful()));
	}

}
