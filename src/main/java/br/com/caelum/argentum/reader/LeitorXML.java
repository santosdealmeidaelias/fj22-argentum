package br.com.caelum.argentum.reader;

import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXML {

	public List<Negociacao> carrega(InputStream inputStream) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("negociacao", Negociacao.class);
		
		// Verificar uma maneira melhor de checar se os dados s�o validos
		List<Negociacao> negociacoes = (List<Negociacao>)stream.fromXML(inputStream);
		for(Negociacao n : negociacoes){
			if(n.getPreco() <= 0)
				throw new IllegalArgumentException("O pre�o n�o deve ser menor ou igual a zero!");
			if(n.getQuantidade() <= 0)
				throw new IllegalArgumentException("A quantidade n�o deve ser menor ou igual a zero");
			if(n.getData() == null)
				throw new IllegalArgumentException("A data n�o deve ser nula!");
		}
		return negociacoes;
	}
}
