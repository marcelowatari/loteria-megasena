package tech.ada.java.resultadosanteriores.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import tech.ada.java.meusjogos.dto.MeuJogoNumerosEscolhidos;
import tech.ada.java.resultadosanteriores.dto.ConcursoResultado;

public class ResultadosAnterioresService {
	
	private static final int QUADRA = 4;
	private static final int QUINA = 5;
	private static final int MEGA = 6;
	private final Map<Integer, ConcursoResultado> mapResultadoJogosAnteriores;

	public ResultadosAnterioresService(Map<Integer, ConcursoResultado> mapResultadoJogosAnteriores ) {
		this.mapResultadoJogosAnteriores = mapResultadoJogosAnteriores;
	}

	public void checarJogoAlgumaVezSorteado(Map<Integer, MeuJogoNumerosEscolhidos> meusJogos) {
		
		//int[] iNumerosAPesquisar = Stream.of(numerosAPesquisar).map(i-> i.trim()).mapToInt(Integer::parseInt).toArray();
		
//		for ( ConcursoResultado umConcurso : mapResultadoJogosAnteriores.values() ) {
//			HashSet<Integer> numerosSorteados = umConcurso.getNumerosSorteados();
//			int qtdeAcertos = 0;
//			for (int umNumeroEscolhido : iNumerosAPesquisar) {
//				boolean contains = numerosSorteados.contains( umNumeroEscolhido );
//				if( contains ) {
//					qtdeAcertos ++;
//				}
//			}

		for ( MeuJogoNumerosEscolhidos umMeuJogo : meusJogos.values() ) {
			System.out.println( "Verificando o jogo " + umMeuJogo.getIdJogo() );
			HashSet<Integer> numerosEscolhidos = umMeuJogo.getNumerosQueEscolhiJogar();

			ArrayList<Integer> arrayNumerosEscolhidosOrdenar = new ArrayList<Integer>( numerosEscolhidos );
			Collections.sort(arrayNumerosEscolhidosOrdenar);
			System.out.println( "Numeros escolhidos: " + arrayNumerosEscolhidosOrdenar);
			
			for ( ConcursoResultado umConcurso : mapResultadoJogosAnteriores.values() ) {
				HashSet<Integer> numerosSorteados = umConcurso.getNumerosSorteados();

				int qtdeAcertos = 6;
				for (int umNumeroEscolhido : numerosEscolhidos) {
					boolean contains = numerosSorteados.contains( umNumeroEscolhido );
					if( ! contains ) {
						qtdeAcertos --;
					}
					if( qtdeAcertos < 4 ) {
						break;
					}
				}
				verificarSeTeriaGanhoAlgo( qtdeAcertos, umConcurso );

			}
//			
//			for (MeuJogoNumerosEscolhidos umMeuJogo : meusJogos.values()) {
//				HashSet<Integer> numerosQueEscolhiJogar = umMeuJogo.getNumerosQueEscolhiJogar();
//				numerosQueEscolhiJogar.retainAll(numerosSorteados);
//			}
			
			
		}
		
	}

	private void verificarSeTeriaGanhoAlgo(int qtdeAcertos, ConcursoResultado concurso) {
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
}
