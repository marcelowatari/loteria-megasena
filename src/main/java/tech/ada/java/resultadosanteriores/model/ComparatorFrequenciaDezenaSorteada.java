package tech.ada.java.resultadosanteriores.model;

import java.util.Comparator;

public class ComparatorFrequenciaDezenaSorteada implements Comparator<FrequenciaDezenaSorteada> {

	@Override
	public int compare(
			FrequenciaDezenaSorteada frequenciaDezenaSorteada1, 
			FrequenciaDezenaSorteada frequenciaDezenaSorteada2) {
		int resultado = frequenciaDezenaSorteada2.getQtdeVezesSorteada() - frequenciaDezenaSorteada1.getQtdeVezesSorteada();
		if ( resultado == 0 ) {
			resultado = frequenciaDezenaSorteada1.getDezena() - frequenciaDezenaSorteada2.getDezena();
		}
		return resultado;
	}

}
