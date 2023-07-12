package tech.ada.java;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Map;

import tech.ada.java.dto.ConcursoResultado;
import tech.ada.java.service.CarregarResultadosAnteriores;

public class App {

	public static void main(String[] args) {
		
		try( EntradaDeDados leitor = new EntradaDeDados() ) {
			new Loterias( leitor ).processar();
		}
		
//		String path = "resultados-anteriores.csv";
//		System.out.println("dados carregados...");
//		try {
//			CarregarResultadosAnteriores.carregar( path );
//			Map<Integer, ConcursoResultado> mapResultadoJogosAnteriores = CarregarResultadosAnteriores.mapResultadoJogosAnteriores;
//			
//			System.out.println( mapResultadoJogosAnteriores.size() );
//			for (ConcursoResultado umConcurso : mapResultadoJogosAnteriores.values()) {
//				
//				System.out.println("Concurso: " + umConcurso.getConcurso() );
//				System.out.println("Data: " + umConcurso.getDataSorteio() );
//				HashSet<Integer> hsNumerosSorteados = umConcurso.getNumerosSorteados();
//				
//				System.out.println("Numeros: " );
//				hsNumerosSorteados.stream().forEach( x -> System.out.println( x ) );
//
//			}
//			
//		} catch (URISyntaxException | IOException e) {
//			System.out.println("Nao foi possivel carregar os resultados anteriores da mega sena");
//
//		}
//		
//		System.out.println("dados carregados.");
	}

}
