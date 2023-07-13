package tech.ada.java.meusjogos.dto;
import com.opencsv.bean.CsvBindByName;

public class BeanMeuJogo {
	
	@CsvBindByName(column = "IdJogo")
    private int idJogo;

	public int getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}

	@CsvBindByName
    private int coluna1;
    
    @CsvBindByName
    private int coluna2;
    
    @CsvBindByName
    private int coluna3;
    
    @CsvBindByName
    private int coluna4;
    
    @CsvBindByName
    private int coluna5;
    
    @CsvBindByName
    private int coluna6;

	public int getColuna1() {
		return coluna1;
	}

	public void setColuna1(int coluna1) {
		this.coluna1 = coluna1;
	}

	public int getColuna2() {
		return coluna2;
	}

	public void setColuna2(int coluna2) {
		this.coluna2 = coluna2;
	}

	public int getColuna3() {
		return coluna3;
	}

	public void setColuna3(int coluna3) {
		this.coluna3 = coluna3;
	}

	public int getColuna4() {
		return coluna4;
	}

	public void setColuna4(int coluna4) {
		this.coluna4 = coluna4;
	}

	public int getColuna5() {
		return coluna5;
	}

	public void setColuna5(int coluna5) {
		this.coluna5 = coluna5;
	}

	public int getColuna6() {
		return coluna6;
	}

	public void setColuna6(int coluna6) {
		this.coluna6 = coluna6;
	}

	@Override
	public String toString() {
		return "BeanMeuJogo [idJogo=" + idJogo + ", coluna1=" + coluna1 + ", coluna2=" + coluna2 + ", coluna3="
				+ coluna3 + ", coluna4=" + coluna4 + ", coluna5=" + coluna5 + ", coluna6=" + coluna6 + "]";
	}
	
	
    
}
