import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CarregarResultadosAnteriores {

	public static Map<Integer, ConcursoResultado> mapResultadoJogosAnteriores;
	
	public static void carregar(String filePath) throws URISyntaxException, IOException {

		Path path = Paths.get(ClassLoader.getSystemResource(filePath).toURI());

		try (Reader reader = Files.newBufferedReader(path)) {
			CsvToBean<BeanUmResultado> cb = new CsvToBeanBuilder<BeanUmResultado>(reader)
					.withType(BeanUmResultado.class).build();
			int i = 1;
//			for (BeanUmResultado beanUmResultado2 : cb) {
//				System.out.println(i+" " + beanUmResultado2.getConcurso() );
//				i++;
//			}
			// List<ConcursoResultado> collect = cb.stream().map( beanUmResultado -> new
			// ConcursoResultado(beanUmResultado)).collect( Collectors.toList() );

			mapResultadoJogosAnteriores = cb.stream().collect(Collectors.toMap(valor -> valor.getConcurso(),
					beanUmResultado -> new ConcursoResultado(beanUmResultado)));

		}
	}


}
