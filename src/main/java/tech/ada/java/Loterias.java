package tech.ada.java;

import java.util.Map;

import tech.ada.java.dto.ConcursoResultado;
import tech.ada.java.service.CarregarResultadosAnteriores;
import tech.ada.java.service.ResultadosAnterioresService;

public class Loterias {
	private final String filePathResultadosAnteriores = "resultados-anteriores.csv";

    private final EntradaDeDados leitor;
    
    ResultadosAnterioresService resultadosAnterioresService;
    
    private final String DIGITE_OPCAO_DESEJADA = "Digite a opção desejada: ";
    private final String OPCAO_SAIR = "x";
    private final String OPCAO_CHECAR_JOGO_JA_SORTEADO = "1";
    private final String OPCAO_LISTAR_FUNCIONARIOS = "2";
    private final String OPCAO_CADASTRAR_EM_LOTE = "3";
    private final String OPCAO_BUSCA_POR_ID = "4";
    private final String OPCAO_BUSCA_POR_NOME = "5";

	public Loterias(EntradaDeDados leitor) {
		this.leitor = leitor;
		iniciaApp();
		Map<Integer, ConcursoResultado> resultadosAnteriores = CarregarResultadosAnteriores.carregar(filePathResultadosAnteriores);
		resultadosAnterioresService = new ResultadosAnterioresService( resultadosAnteriores );
	}

	public void processar() {

		String opcaoDigitada = obterEntradaDoUsuario(leitor);

		while (!escolheuSair(opcaoDigitada)) {
			tratarOpcaoSelecionada(opcaoDigitada);
			opcaoDigitada = obterEntradaDoUsuario(leitor);
		}

		finalizaApp();

	}

    private void tratarOpcaoSelecionada(String opcaoDigitada) {
        switch (opcaoDigitada){
            case OPCAO_SAIR:
                break;
            case OPCAO_CHECAR_JOGO_JA_SORTEADO:
                this.checarJogoAlgumaVezSorteado();
                System.out.println("Cadastro realizado com sucesso!");
                //pularLinha(2);
                break;
//            case OPCAO_LISTAR_FUNCIONARIOS:
//                listarFuncionarios();
//                pularLinha(2);
//                break;
//            case OPCAO_CADASTRAR_EM_LOTE:
//                carregarFuncionariosEmLote();
//                break;
//            case OPCAO_BUSCA_POR_ID:
//                buscaPorIdHashMap();
//                break;
//            case OPCAO_BUSCA_POR_NOME:
//                buscaPorNomeHashMap();
//                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private void checarJogoAlgumaVezSorteado(){
        System.out.print("Digite o jogo que gostaria de checar no formato N, N, N, N, N, N ");
        String jogoAPesquisar = leitor.obterEntrada();
        System.out.println("Vamos ver se voce teria ganho alguma vez na mega com este jogo: " + jogoAPesquisar );
        resultadosAnterioresService.checarJogoAlgumaVezSorteado( jogoAPesquisar );
    }

//    private void buscaPorIdHashMap(){
//        System.out.print("Digite o id do funcionario: ");
//        Integer id = leitor.obterEntradaAsInt();
//        Funcionario funcionario = this.mapaIdFuncionario.get(id);
//        if(funcionario != null){
//            System.out.println("Funcionário localizado!");
//            System.out.println(funcionario);
//        } else {
//            System.out.println("Nenhum funcionário localizado para o id: " + id);
//        }
//    }
//
//    private void buscaPorNomeHashMap(){
//        System.out.print("Digite o primeiro nome do funcionario: ");
//        String primeiroNome = leitor.obterEntrada().toLowerCase();
//        List<Funcionario> funcionarios = this.mapaFuncionarioPorNome.get(primeiroNome);
//        if(funcionarios != null){
//            System.out.println("Funcionário(s) localizado(s)!");
//            for (Funcionario funcionario: funcionarios){
//                System.out.println(funcionario);
//            }
//        } else {
//            System.out.println("Nenhum funcionário localizado para o nome: " + primeiroNome);
//        }
//    }
//
//    private void listarFuncionarios(){
//        StringBuilder sb = new StringBuilder();
//
//        if (listaDeFuncionarios.isEmpty()) {
//            sb.append("[]");
//        } else {
//            sb.append("[\n");
//            for (Funcionario funcionario : listaDeFuncionarios) {
//                sb.append("\t").append(funcionario).append(",\n");
//            }
//            sb.setLength(sb.length() - 2); // Remover a vírgula extra após o último funcionário
//            sb.append("\n]");
//        }
//
//        System.out.println(sb);
//    }
//
    private boolean escolheuSair(String opcaoDigitada){
        return opcaoDigitada.equals(OPCAO_SAIR);
    }

    private String obterEntradaDoUsuario(EntradaDeDados leitor){
        carregaMenu();
        System.out.print(DIGITE_OPCAO_DESEJADA);
        return leitor.obterEntrada().toLowerCase();
    }
//
//    private Funcionario construirFuncionario(Integer id, EntradaDeDados leitor){
//        System.out.println("Cadastro de novo funcionário");
//        System.out.print("Informe o nome: ");
//        String nome = leitor.obterEntrada();
//        System.out.print("Informe o departamento: ");
//        String departamento = leitor.obterEntrada();
//        System.out.print("Informe o salário: ");
//        double salario = leitor.obterEntradaAsDouble();
//        return new Funcionario(id, nome, departamento, salario);
//
//    }

    private void finalizaApp(){
        System.out.println("Até logo!!");
    }

    private void opcaoInvalida(){
        System.out.println("Opção INVÁLIDA. Tente novamente.");
    }

    private void iniciaApp(){
        carregaNomeApp();
    }

    private void carregaMenu() {
        System.out.println("********  DIGITE A OPÇÃO DESEJADA   ******");
        System.out.println("1 - VERIFICAR SE SEU JOGO JA FOI SORTEADO ALGUMA VEZ");
        System.out.println("2 - LISTAR FUNCIONÁRIOS(AS)");
        System.out.println("3 - CADASTRO EM LOTE (CSV)");
        System.out.println("4 - PESQUISAR POR ID");
        System.out.println("5 - PESQUISAR POR NOME");
        System.out.println("X - SAIR");
    }

    private void carregaNomeApp(){
        System.out.println("******************************************");
        System.out.println("******* Loterias Mega Sena *********");
        System.out.println("******************************************");
    }

}