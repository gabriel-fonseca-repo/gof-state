## State - Gang of four.

Exemplo de uma aplicação do padrão State para manuseamento de processos com múltiplos estados e passos.

## Implementação

1. Class abstrata State, responsável pela assinatura dos métodos relavantes aos estados do contexto atual.

```java
public abstract class State {

	private final String stateLabel;
	private Pessoa stateful;
	private boolean stateComplete;

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

/* getters e setters */
```

1.1. Método para checagem se o passo atual foi executado de forma bem sucedida, para prosseguir para o próximo passo.

```java
	public void irAoProximoPasso() {
		exibirPasso("Prosseguindo ao próximo passo");
		if (stateComplete) {
			changeState();
		} else {
			exibirPasso("Erro no procedimento de passo do cadastro");
		}
	}
```

1.2. Funções que devem ser implementadas pelas classes concretas de State, para execução do passo atual, e lidar com a
passagem para o próximo passo.

```java
public abstract void executarPassoCadastro();

protected abstract void changeState();
```

1.3. Classe a ser utilizada como Stateful/Context, uma classe que representa uma pessoa em processo de avaliação de
cadastro.

```java
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
```

#### As classes que represtam os estados do cadastro são, em ordem de execução:

> state.DadosEnviadosState - Para envio dos dados do cliente.
>
> state.DadosValidadosState - Para a validação dos respectivos dados.
>
> state.DocumentosEnviadosState - Para envio dos dados do cliente.
>
> state.DocumentosValidadosState - Para a validação dos respectivos documentos.
>
> state.CadastroRealizadoState - Para finalização do cadastro.

2. As classes de State irão executar uma lógica diferente com os dados do context para cada um de seus passos, até
   atingirem a validação, e então abrir alas para execução do próximo passo.

```java
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
```

3. Execução do código:

> Input:
```java
public static void main(String[]args){
        Pessoa pessoaEmCadastro=new Pessoa("Gabriel","222.222.222-22",LocalDate.now(),new BufferedImage(22,22,2));
        State estadoInicial=new DadosEnviadosState(pessoaEmCadastro);
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
        pessoaEmCadastro.getState().irAoProximoPasso();

}
```

> Output:
```
Envio de dados cadastrais - Enviando os dados...
Envio de dados cadastrais - Dados enviados com sucesso...
Envio de dados cadastrais - Prosseguindo ao próximo passo...
Validação de dados cadastrais - Validando os dados...
Validação de dados cadastrais - Dados validados com sucesso...
Validação de dados cadastrais - Prosseguindo ao próximo passo...
Envio de documentos cadastrais - Enviando o documento...
Envio de documentos cadastrais - Dados validados com sucesso...
Envio de documentos cadastrais - Prosseguindo ao próximo passo...
Validação de documentos cadastrais - Validando o documento...
Validação de documentos cadastrais - Documento validado com sucesso...
Validação de documentos cadastrais - Prosseguindo ao próximo passo...
Finalização do cadastro - Cadastro realizado com sucesso...
Finalização do cadastro - Prosseguindo ao próximo passo...
Finalização do cadastro - Erro no procedimento de passo do cadastro...

Process finished with exit code 0
```