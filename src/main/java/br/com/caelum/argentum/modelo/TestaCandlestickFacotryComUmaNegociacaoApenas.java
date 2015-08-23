package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandlestickFacotryComUmaNegociacaoApenas {
	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		List<Negociacao> negociacoes = Arrays.asList(negociacao1);
		CandlestickFactory fabrica = new CandlestickFactory();
		
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		System.out.println(candle);
		
	}
}
