package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CandlestickFactory {
	
	public Candlestick constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {
		double maximo = 0;
		double minimo = Double.MAX_VALUE;
		double volume = 0;
		
		for(Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();
			
			if (negociacao.getPreco() > maximo){
				maximo = negociacao.getPreco();
			}
			
			if(negociacao.getPreco() < minimo) {
				minimo = negociacao.getPreco();
			}
		}
		
		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size()-1).getPreco();
		minimo = negociacoes.isEmpty() ? 0 : minimo;
		
		return new CandleBuilder()
				.comAbertura(abertura)
				.comFechamento(fechamento)
				.comMinimo(minimo)
				.comMaximo(maximo)
				.comVolume(volume)
				.comData(data)
				.geraCandle();
	}

	public List<Candlestick> constroiCandleParaData(List<Negociacao> todasNegociacoes) {
		Collections.sort(todasNegociacoes);
		List<Candlestick> candles = new ArrayList<>();
		List<Negociacao> negociacoesDoDia = new ArrayList<>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();
		
		for(Negociacao negociacao : todasNegociacoes){
			//se não for o memso dia, fecha candle e reinicia variáveis
			if(!negociacao.isMesmoDia(dataAtual)){
				Candlestick candleDodia = constroiCandleParaData(dataAtual, negociacoesDoDia);
				candles.add(candleDodia);
				negociacoesDoDia = new ArrayList<>();
				dataAtual = negociacao.getData();
			}
			negociacoesDoDia.add(negociacao);
		}
		
		// adiciona último candle
		Candlestick candleDodia = constroiCandleParaData(dataAtual, negociacoesDoDia);
		candles.add(candleDodia);
		
		return candles;
	}
}
