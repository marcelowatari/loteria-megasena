package tech.ada.java;

import java.util.Map;

import tech.ada.java.meusjogos.dto.MeuJogoNumerosEscolhidos;
import tech.ada.java.meusjogos.service.CarregarMeusJogos;
import tech.ada.java.resultadosanteriores.dto.ConcursoResultado;
import tech.ada.java.resultadosanteriores.service.CarregarResultadosAnteriores;
import tech.ada.java.resultadosanteriores.service.ResultadosAnterioresService;

public class Loterias {
	private final String filePathMeusJogos = "meus-jogos.csv";
	private final String filePathResultadosAnteriores = "resultados-anteriores.csv";

    private final EntradaDeDados leitor;
    
    ResultadosAnterioresService resultadosAnterioresService;
    
    private final String DIGITE_OPCAO_DESEJADA = "Digite a opção desejada: ";
    private final String OPCAO_SAIR = "x";
    private final String OPCAO_CHECAR_JOGO_JA_SORTEADO = "1";
    private final String OPCAO_VER_FREQUENCIA_COM_QUE_AS_DEZENAS_SAEM_NOS_SORTEIOS = "2";
    private final String OPCAO_VER_AS_DEZENAS_MAIS_SORTEADAS = "3";
//    private final String OPCAO_BUSCA_POR_ID = "4";
//    private final String OPCAO_BUSCA_POR_NOME = "5";

    Map<Integer, MeuJogoNumerosEscolhidos> meusJogos;
    
	public Loterias(EntradaDeDados leitor) {
		this.leitor = leitor;
		iniciaApp();
		
        pularLinha(2);

		Map<Integer, ConcursoResultado> resultadosAnteriores = CarregarResultadosAnteriores.carregar(filePathResultadosAnteriores);
		resultadosAnterioresService = new ResultadosAnterioresService( resultadosAnteriores );

		pularLinha(2);
		meusJogos = CarregarMeusJogos.carregar(filePathMeusJogos);
		pularLinha(2);

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
                pularLinha(2);
                this.checarJogoAlgumaVezSorteado();
                this.pularLinha(2);
                break;
            case OPCAO_VER_FREQUENCIA_COM_QUE_AS_DEZENAS_SAEM_NOS_SORTEIOS:
                this.verFrequenciaDezenasSaemSorteios();
                this.pularLinha(2);
                break;
            case OPCAO_VER_AS_DEZENAS_MAIS_SORTEADAS:
                this.verDezenasMaisSorteadas();
                this.pularLinha(2);
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private void verDezenasMaisSorteadas() {
   	 resultadosAnterioresService.verDezenasMaisSorteadas();
		
	}

	private void verFrequenciaDezenasSaemSorteios() {
    	 resultadosAnterioresService.verFrequenciaDezenasSaemSorteios();
	}

	private void checarJogoAlgumaVezSorteado(){
        System.out.println("Vamos ver se voce teria ganho alguma vez na mega com estes jogos: " );
        pularLinha(1);
        System.out.println("Inicio - listagem dos seus jogos " );

        for (MeuJogoNumerosEscolhidos meuJogo : meusJogos.values()) {
        	 System.out.println ( meuJogo.getNumerosQueEscolhiJogar() );
		}
        
        System.out.println("Fim - listagem dos seus jogos " );
        pularLinha(1);

        System.out.println("*** Inicio - checando se algum destes seus jogos foram sorteados alguma vez na mega sena " );
        resultadosAnterioresService.checarJogoAlgumaVezSorteado( meusJogos );
        System.out.println("*** Fim - checando se algum destes seus jogos foram sorteados alguma vez na mega sena " );

    }
    
    public void pularLinha(int numeroDeLinhas){
        for (int i = 1; i <= numeroDeLinhas; i++) {
            System.out.println();
        }
    }

    private boolean escolheuSair(String opcaoDigitada){
        return opcaoDigitada.equals(OPCAO_SAIR);
    }

    private String obterEntradaDoUsuario(EntradaDeDados leitor){
        carregaMenu();
        System.out.print(DIGITE_OPCAO_DESEJADA);
        return leitor.obterEntrada().toLowerCase();
    }

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
        System.out.println("2 - VER A FREQUECIA COM QUE AS DEZENAS SAEM NOS SORTEIOS");
        System.out.println("3 - VER AS DEZENAS MAIS SORTEADOS");
//        System.out.println("4 - PESQUISAR POR ID");
//        System.out.println("5 - PESQUISAR POR NOME");
        System.out.println("X - SAIR");
    }

    private void carregaNomeApp(){
        System.out.println("******************************************");
        System.out.println("******* Loterias Mega Sena *********");
        System.out.println("******************************************");
    }

}