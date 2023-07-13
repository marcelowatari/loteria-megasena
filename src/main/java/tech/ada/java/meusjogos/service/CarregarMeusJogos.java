package tech.ada.java.meusjogos.service;

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

import tech.ada.java.meusjogos.dto.BeanMeuJogo;
import tech.ada.java.meusjogos.dto.MeuJogoNumerosEscolhidos;

public class CarregarMeusJogos {
	
	private static final Logger logger = LoggerFactory.getLogger(CarregarMeusJogos.class);

	public static Map<Integer, MeuJogoNumerosEscolhidos> carregar(String filePath) {

		System.out.println("Carregando meus jogos que fiz na mega sena...");
		
		Path path;
		try {
			path = Paths.get(ClassLoader.getSystemResource(filePath).toURI());
		} catch (URISyntaxException e) {
			logger.error("Nao foi possivel carregar os dados", e);
			throw new IllegalArgumentException(filePath);
		}

		
		Map<Integer, MeuJogoNumerosEscolhidos> mapMeusJogos = null;
		
		try (Reader reader = Files.newBufferedReader(path)) {
			CsvToBean<BeanMeuJogo> cb = new CsvToBeanBuilder<BeanMeuJogo>(reader)
					.withType(BeanMeuJogo.class).build();

			mapMeusJogos = cb.stream().collect(Collectors.toMap(meuJogo -> meuJogo.getIdJogo(),
					meuJogo -> new MeuJogoNumerosEscolhidos(meuJogo) ) );

		} catch (IOException e) {
			logger.error("Nao foi possivel carregar os dados", e);
		}
		
		System.out.println("... Dados carregados com sucesso");

		return mapMeusJogos;
	}

}
