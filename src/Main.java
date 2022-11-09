import context.Pessoa;
import states.DadosEnviadosState;
import states.State;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		Pessoa pessoaEmCadastro = new Pessoa("Gabriel", "222.222.222-22", LocalDate.now(), new BufferedImage(22, 22, 2));
		State estadoInicial = new DadosEnviadosState(pessoaEmCadastro);
		pessoaEmCadastro.initializeState(estadoInicial);

		pessoaEmCadastro.getState().executarPassoCadastro();
		pessoaEmCadastro.getState().irAoProximoPasso();

		pessoaEmCadastro.getState().executarPassoCadastro();
		pessoaEmCadastro.getState().irAoProximoPasso();

		pessoaEmCadastro.getState().executarPassoCadastro();
		pessoaEmCadastro.getState().irAoProximoPasso();

		pessoaEmCadastro.getState().executarPassoCadastro();
		pessoaEmCadastro.getState().irAoProximoPasso();

		pessoaEmCadastro.getState().executarPassoCadastro();

	}
}