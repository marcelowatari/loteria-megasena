package tech.ada.java.meusjogos.dto;
import java.util.Arrays;
import java.util.HashSet;

public class MeuJogoNumerosEscolhidos {
	
	private int idJogo;
	private HashSet<Integer> numerosQueEscolhiJogar;
	
    public MeuJogoNumerosEscolhidos(BeanMeuJogo umJogo) {
    	this.setIdJogo(umJogo.getIdJogo());
		this.setNumerosQueEscolhiJogar(new HashSet<>( Arrays.asList( 
				umJogo.getColuna1(),
				umJogo.getColuna2(), 
				umJogo.getColuna3(), 
				umJogo.getColuna4(), 
				umJogo.getColuna5(), 
				umJogo.getColuna6()
				) ));
	}

	public HashSet<Integer> getNumerosQueEscolhiJogar() {
		return numerosQueEscolhiJogar;
	}



	public void setNumerosQueEscolhiJogar(HashSet<Integer> numerosQueEscolhiJogar) {
		this.numerosQueEscolhiJogar = numerosQueEscolhiJogar;
	}

	public int getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}
}
