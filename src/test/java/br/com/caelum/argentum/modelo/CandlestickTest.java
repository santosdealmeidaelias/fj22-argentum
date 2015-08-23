package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class CandlestickTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo(){
		Calendar hoje = Calendar.getInstance();
		new Candlestick(10, 20, 20, 10.0, 1000, hoje);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void dataDoCandleNaoPodeSerNull(){
		new Candlestick(10, 10, 10, 20, 1000, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void candleNaoPodeConterValoresNegativos(){
		new Candlestick(-10, -10, -20, -10, -1000, Calendar.getInstance());
	}
	
	@Test
	public void quandoAberturaIgualFechamentoEhAlta(){
		Calendar hoje = Calendar.getInstance();
		Candlestick candle = new Candlestick(40.0, 40.0, 40.0, 40.0, 4000, hoje);
		assertEquals(true, candle.isAlta());
	}
	
	@Test
	public void quandoAberturaMaiorFechamentoEhBaixa(){
		Calendar hoje = Calendar.getInstance();
		
		Candlestick candle = new CandleBuilder()
				.comAbertura(50.0)
				.comFechamento(40.0)
				.comMaximo(60.0)
				.comMinimo(30.0)
				.comVolume(584000)
				.comData(hoje)
				.geraCandle();
		
		assertEquals(true, candle.isBaixa());
	}
	
	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles(){
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);
		
		Calendar amanha = (Calendar)hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		Negociacao negociacao5 = new Negociacao(48.8, 100, amanha);
		Negociacao negociacao6 = new Negociacao(49.3, 100, amanha);
		
		Calendar depois = (Calendar)amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);
		
		Negociacao negociacao7 = new Negociacao(51.8, 100, depois);
		Negociacao negociacao8 = new Negociacao(52.3, 100, depois);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4, negociacao5
				,negociacao6, negociacao7, negociacao8);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candlestick> candles = fabrica.constroiCandleParaData(negociacoes);
		
		assertEquals(3, candles.size());
		assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
		assertEquals(42.3, candles.get(0).getFechamento(), 0.00001);
		
		assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		
		assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		assertEquals(52.3, candles.get(2).getFechamento(), 0.00001);
		
	}
	
	@Test
	public void construirCandlesComNegociacoesForaDeOrdem(){
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);
		
		Calendar amanha = (Calendar)hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		Negociacao negociacao5 = new Negociacao(48.8, 100, amanha);
		Negociacao negociacao6 = new Negociacao(49.3, 100, amanha);
		
		Calendar depois = (Calendar)amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);
		
		Negociacao negociacao7 = new Negociacao(51.8, 100, depois);
		Negociacao negociacao8 = new Negociacao(52.3, 100, depois);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao8, negociacao2, negociacao1, 
				negociacao7, negociacao5, negociacao6, negociacao4, negociacao3);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candlestick> candles = fabrica.constroiCandleParaData(negociacoes);
		
		assertEquals(3, candles.size());
		assertEquals(45.0, candles.get(0).getAbertura(), 0.00001);
		assertEquals(39.8, candles.get(0).getFechamento(), 0.00001);
		
		assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		
		assertEquals(52.3, candles.get(2).getAbertura(), 0.00001);
		assertEquals(51.8, candles.get(2).getFechamento(), 0.00001);
	}

}
