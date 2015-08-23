package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandlestickFacotryComSequenciaDescrescente {
	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(49.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(53.3, 100, hoje);

		List<Negociacao> negociacoes = Arrays.asList(negociacao4, negociacao3, negociacao2, negociacao1);
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		System.out.println(candle);
	}
}
