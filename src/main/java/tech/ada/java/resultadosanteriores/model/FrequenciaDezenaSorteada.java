package tech.ada.java.resultadosanteriores.model;

public class FrequenciaDezenaSorteada {

	private int dezena;
	private int qtdeVezesSorteada;
	
	public FrequenciaDezenaSorteada(int dezena, int qtdeVezesSorteada) {
		this.dezena = dezena;
		this.qtdeVezesSorteada = qtdeVezesSorteada;
	}
	public int getDezena() {
		return dezena;
	}
	public void setDezena(int dezena) {
		this.dezena = dezena;
	}
	public int getQtdeVezesSorteada() {
		return qtdeVezesSorteada;
	}
	public void setQtdeVezesSorteada(int qtdeVezesSorteada) {
		this.qtdeVezesSorteada = qtdeVezesSorteada;
	}
	
	
}
