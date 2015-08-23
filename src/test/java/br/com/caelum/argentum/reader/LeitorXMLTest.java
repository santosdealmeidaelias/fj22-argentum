package br.com.caelum.argentum.reader;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		StringBuilder xmlDeTeste = new StringBuilder();
		xmlDeTeste
			.append("<list>")
				.append("<negociacao>")
					.append("<preco>43.5</preco>")
					.append("<quantidade>1000</quantidade>")
					.append("<data>")
						.append("<time>1322233344455</time>")
					.append("</data>")
				.append("</negociacao>")
		 	.append("</list>");
		 
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.toString().getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(1, negociacoes.size());
		assertEquals(43.5, negociacoes.get(0).getPreco(), 0.00001);
		assertEquals(1000, negociacoes.get(0).getQuantidade());
		assertEquals((43.5*1000), negociacoes.get(0).getVolume(),0.00001);
	}
	
	@Test
	public void carregaXmlSemNegociacoes(){
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream("<list></list>".getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		assertEquals(0, negociacoes.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void carregaXmlComNegociacaoSemPreco(){
		StringBuilder xmlDeTeste = new StringBuilder();
		xmlDeTeste
			.append("<list>")
				.append("<negociacao>")
					.append("<quantidade>1000</quantidade>")
					.append("<data>")
						.append("<time>1440282915896</time>")
					.append("</data>")
				.append("</negociacao>")
		 	.append("</list>");
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.toString().getBytes());
		leitor.carrega(xml);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void carregaXmlComNegociacaoSemQuantidade(){
		StringBuilder xmlDeTeste = new StringBuilder();
		xmlDeTeste
			.append("<list>")
				.append("<negociacao>")
					.append("<preco>43.5</preco>")
					.append("<data>")
						.append("<time>1440282915896</time>")
					.append("</data>")
				.append("</negociacao>")
		 	.append("</list>");
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.toString().getBytes());
		leitor.carrega(xml);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void carregaXmlComNegociacaoSemData(){
		StringBuilder xmlDeTeste = new StringBuilder();
		xmlDeTeste
			.append("<list>")
				.append("<negociacao>")
					.append("<preco>43.5</preco>")
					.append("<quantidade>1000</quantidade>")
				.append("</negociacao>")
		 	.append("</list>");
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.toString().getBytes());
		leitor.carrega(xml);
	}
	
	@Test
	public void carregaTresNegociacoes(){
		StringBuilder xmlDeTeste = new StringBuilder();
		xmlDeTeste
			.append("<list>")
				.append("<negociacao>")
					.append("<preco>43.5</preco>")
					.append("<quantidade>1000</quantidade>")
					.append("<data>")
						.append("<time>1322233344455</time>")
					.append("</data>")
				.append("</negociacao>")
				.append("<negociacao>")
					.append("<preco>45.0</preco>")
					.append("<quantidade>100</quantidade>")
					.append("<data>")
						.append("<time>1322233344455</time>")
					.append("</data>")
				.append("</negociacao>")
				.append("<negociacao>")
					.append("<preco>50.0</preco>")
					.append("<quantidade>100</quantidade>")
					.append("<data>")
						.append("<time>1322233344455</time>")
					.append("</data>")
				.append("</negociacao>")
			.append("</list>");
		 
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.toString().getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(3, negociacoes.size());
		assertEquals(43.5, negociacoes.get(0).getPreco(), 0.00001);
		assertEquals(1000, negociacoes.get(0).getQuantidade());
		assertEquals((43.5*1000), negociacoes.get(0).getVolume(),0.00001);
		
		assertEquals(45.0, negociacoes.get(1).getPreco(), 0.00001);
		assertEquals(100, negociacoes.get(1).getQuantidade());
		assertEquals((45.0*100), negociacoes.get(1).getVolume(),0.00001);
		
		assertEquals(50.0, negociacoes.get(2).getPreco(), 0.00001);
		assertEquals(100, negociacoes.get(2).getQuantidade());
		assertEquals((50.0*100), negociacoes.get(2).getVolume(),0.00001);
	}

}
