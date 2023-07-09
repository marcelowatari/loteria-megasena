import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class ConcursoResultado {

	private int concurso;
	private Date dataSorteio;
	private HashSet<Integer> numerosSorteados;
	
	public ConcursoResultado(BeanUmResultado beanUmResultado) {
		this.setConcurso(beanUmResultado.getConcurso());
		this.dataSorteio = beanUmResultado.getDataSorteio();
		this.numerosSorteados = new HashSet<>( Arrays.asList( 
				beanUmResultado.getColuna1(),
				beanUmResultado.getColuna2(), 
				beanUmResultado.getColuna3(), 
				beanUmResultado.getColuna4(), 
				beanUmResultado.getColuna5(), 
				beanUmResultado.getColuna6()
				) );
	}
	
	
	public Date getDataSorteio() {
		return dataSorteio;
	}
	public void setDataSorteio(Date dataSorteio) {
		this.dataSorteio = dataSorteio;
	}
	public HashSet<Integer> getNumerosSorteados() {
		return numerosSorteados;
	}
	public void setNumerosSorteados(HashSet<Integer> numerosSorteados) {
		this.numerosSorteados = numerosSorteados;
	}


	public int getConcurso() {
		return concurso;
	}


	public void setConcurso(int concurso) {
		this.concurso = concurso;
	}
	
}
