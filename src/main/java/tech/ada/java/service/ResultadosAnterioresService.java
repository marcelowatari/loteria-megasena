package tech.ada.java.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

import tech.ada.java.dto.ConcursoResultado;

public class ResultadosAnterioresService {
	
	private static final int QUADRA = 4;
	private static final int QUINA = 5;
	private static final int MEGA = 6;
	private final Map<Integer, ConcursoResultado> mapResultadoJogosAnteriores;

	public ResultadosAnterioresService(Map<Integer, ConcursoResultado> mapResultadoJogosAnteriores ) {
		this.mapResultadoJogosAnteriores = mapResultadoJogosAnteriores;
	}

	public void checarJogoAlgumaVezSorteado(String jogoAPesquisar) {
		String[] numerosAPesquisar = jogoAPesquisar.split(",");
		
		int[] iNumerosAPesquisar = Stream.of(numerosAPesquisar).map(i-> i.trim()).mapToInt(Integer::parseInt).toArray();
		
		for ( ConcursoResultado umConcurso : mapResultadoJogosAnteriores.values() ) {
			HashSet<Integer> numerosSorteados = umConcurso.getNumerosSorteados();
			int qtdeAcertos = 0;
			for (int umNumeroEscolhido : iNumerosAPesquisar) {
				boolean contains = numerosSorteados.contains( umNumeroEscolhido );
				if( contains ) {
					qtdeAcertos ++;
				}
			}
			verificarSeTeriaGanhoAlgo( qtdeAcertos, umConcurso );
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
