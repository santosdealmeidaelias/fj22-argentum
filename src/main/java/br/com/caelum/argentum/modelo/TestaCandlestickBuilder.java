package br.com.caelum.argentum.modelo;

import java.util.GregorianCalendar;

public class TestaCandlestickBuilder {
	
	public static void main(String[] args) {
		Candlestick candle = new CandleBuilder().comAbertura(40.5)
			.comFechamento(42.3)
			.comMinimo(39.8)
			.comMaximo(45.0)
			.comVolume(145234.20)
			.comData(new GregorianCalendar(2015, 7, 22, 0, 0, 0))
			.geraCandle();
		
		System.out.println(candle);
	}
}
