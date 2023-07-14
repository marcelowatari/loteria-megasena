package tech.ada.java.resultadosanteriores.service;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import tech.ada.java.resultadosanteriores.dto.BeanUmResultado;
import tech.ada.java.resultadosanteriores.dto.ConcursoResultado;

public class CarregarResultadosAnteriores {
	
	private static final Logger logger = LoggerFactory.getLogger(CarregarResultadosAnteriores.class);

	public static Map<Integer, ConcursoResultado> carregar(String filePath) {

		System.out.println("Carregando resultados anteriores da mega sena...");
		
		Path path;
		try {
			path = Paths.get(ClassLoader.getSystemResource(filePath).toURI());
		} catch (URISyntaxException e) {
			logger.error("Nao foi possivel carregar os dados", e);
			throw new IllegalArgumentException(filePath);
		}

		
		Map<Integer, ConcursoResultado> mapResultadoJogosAnteriores = null;
		
		try (Reader reader = Files.newBufferedReader(path)) {
			CsvToBean<BeanUmResultado> cb = new CsvToBeanBuilder<BeanUmResultado>(reader)
					.withType(BeanUmResultado.class).build();

			mapResultadoJogosAnteriores = cb.stream().collect(Collectors.toMap(valor -> valor.getConcurso(),
					beanUmResultado -> new ConcursoResultado(beanUmResultado)));

		} catch (IOException e) {
			logger.error("Nao foi possivel carregar os dados", e);
		}
		
		System.out.println("Dados carregados com sucesso");

		return mapResultadoJogosAnteriores;
	}

}
