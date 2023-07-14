package tech.ada.java.resultadosanteriores.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import tech.ada.java.meusjogos.dto.MeuJogoNumerosEscolhidos;
import tech.ada.java.resultadosanteriores.dto.ConcursoResultado;
import tech.ada.java.resultadosanteriores.model.ComparatorFrequenciaDezenaSorteada;
import tech.ada.java.resultadosanteriores.model.FrequenciaDezenaSorteada;

public class ResultadosAnterioresService {
	
	private static final int QUADRA = 4;
	private static final int QUINA = 5;
	private static final int MEGA = 6;
	private final Map<Integer, ConcursoResultado> mapResultadoJogosAnteriores;

	public ResultadosAnterioresService(Map<Integer, ConcursoResultado> mapResultadoJogosAnteriores ) {
		this.mapResultadoJogosAnteriores = mapResultadoJogosAnteriores;
	}

	public void checarJogoAlgumaVezSorteado(Map<Integer, MeuJogoNumerosEscolhidos> meusJogos) {

		for ( MeuJogoNumerosEscolhidos umMeuJogo : meusJogos.values() ) {
			System.out.println( "Verificando o jogo " + umMeuJogo.getIdJogo() );
			HashSet<Integer> numerosEscolhidos = umMeuJogo.getNumerosQueEscolhiJogar();

			ArrayList<Integer> arrayNumerosEscolhidosOrdenar = new ArrayList<Integer>( numerosEscolhidos );
			Collections.sort(arrayNumerosEscolhidosOrdenar);
			System.out.println( "Numeros escolhidos: " + arrayNumerosEscolhidosOrdenar);
			
			boolean jogoSorteado = false;
			for ( ConcursoResultado umConcurso : mapResultadoJogosAnteriores.values() ) {
				HashSet<Integer> numerosSorteados = umConcurso.getNumerosSorteados();

				int qtdeNumerosAcertados = 6;
				for (int umNumeroEscolhido : numerosEscolhidos) {
					boolean contains = numerosSorteados.contains( umNumeroEscolhido );
					if( ! contains ) {
						qtdeNumerosAcertados --;
					}
					if( qtdeNumerosAcertados < 4 ) {
						break;
					}
				}
				
				if( qtdeNumerosAcertados >  3 ) {
					jogoSorteado  = true;
					printPremiado( qtdeNumerosAcertados, umConcurso );
				}
			}
			
			if( ! jogoSorteado ) {
				printJogoNuncaSorteado();

			}
			pularLinha(1);

		}
	}

	private void printJogoNuncaSorteado() {
		System.out.println("Este jogo nunca foi sorteado nos resultados anteriores.");
	}

	private void printPremiado(int qtdeAcertos, ConcursoResultado concurso) {
		String ganhou = null;
		if( qtdeAcertos == QUADRA ) {
			ganhou = "QUADRA";
		}
		if( qtdeAcertos == QUINA ) {
			ganhou = "QUINA";
		}
		if( qtdeAcertos == MEGA ) {
			ganhou = "MEGA";
		}
		
		if( ganhou != null ) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataSorteio = concurso.getDataSorteio();
			String dataSorteioFormatada = dataSorteio.format( dateTimeFormatter );
			String msg = String.format("Voce teria acertado a %s no concurso %d, sorteado em %s", 
					ganhou,
					concurso.getConcurso(), 
					dataSorteioFormatada );
			System.out.println(msg);
		}
	}
	
	private void pularLinha(int numeroDeLinhas){
        for (int i = 1; i <= numeroDeLinhas; i++) {
            System.out.println();
        }
    }

	public void verFrequenciaDezenasSaemSorteios() {
		
		List<FrequenciaDezenaSorteada> listaFrequenciaDezenaSorteada = calcularFrequenciaDezenasSorteadas();
		
		for (FrequenciaDezenaSorteada frequenciaDezenaSorteada : listaFrequenciaDezenaSorteada) {
			int dezena = frequenciaDezenaSorteada.getDezena();
			int qtdeSorteada = frequenciaDezenaSorteada.getQtdeVezesSorteada();
			String msg = String.format("A dezena %d foi sorteada %d vezes", dezena, qtdeSorteada);
			System.out.println(msg);
		}
		pularLinha(1);
	}
	
	private List<FrequenciaDezenaSorteada> calcularFrequenciaDezenasSorteadas() {
		int inicioDezenaMegaSena = 1;
		int fimDezenaMegaSena = 60;
		
		List<FrequenciaDezenaSorteada> listaFrequenciaDezenaSorteada = new ArrayList<>();
		
		for( int dezena = inicioDezenaMegaSena; dezena <= fimDezenaMegaSena; dezena ++ ) {
			int qtdeSorteada = 0;
			for (ConcursoResultado umConcurso : mapResultadoJogosAnteriores.values()) {
				HashSet<Integer> numerosSorteados = umConcurso.getNumerosSorteados();
				if( numerosSorteados.contains( dezena ) ) {
					qtdeSorteada ++;
				}
			}
			listaFrequenciaDezenaSorteada.add( new FrequenciaDezenaSorteada( dezena, qtdeSorteada ) );
		}
		return listaFrequenciaDezenaSorteada;
	}
	
	

	public void verDezenasMaisSorteadas() {
		List<FrequenciaDezenaSorteada> listaFrequenciaDezenaSorteada = calcularFrequenciaDezenasSorteadas();
		listaFrequenciaDezenaSorteada.sort( new ComparatorFrequenciaDezenaSorteada() );
		
		String msg = "A dezena %d foi sorteada %d vezes";
		
		listaFrequenciaDezenaSorteada
			.stream()
			.forEach( item -> 
				System.out.println( String.format(msg, item.getDezena(), item.getQtdeVezesSorteada() ) ) );
		
	}
}
