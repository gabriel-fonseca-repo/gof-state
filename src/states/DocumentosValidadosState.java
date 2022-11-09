package states;

import context.Pessoa;

public class DocumentosValidadosState extends State {
	public DocumentosValidadosState(Pessoa stateful) {
		super(stateful, "Validação de documentos cadastrais");
	}

	@Override
	public void executarPassoCadastro() {

		exibirPasso("Validando o documento");
		boolean resultado = getStateful().getDocumento() != null;

		if (resultado) {
			exibirPasso("Documento validado com sucesso");
		} else {
			exibirPasso("Erro na validação do documento");
		}

		setStateComplete(resultado);

	}

	@Override
	public void changeState() {
		getStateful().setState(new CadastroRealizadoState(getStateful()));
	}

}
