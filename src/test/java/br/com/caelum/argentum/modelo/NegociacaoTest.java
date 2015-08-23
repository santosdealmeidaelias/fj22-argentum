package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;


public class NegociacaoTest {

	@Test
	public void dataDeNegociacaoEhImutavel(){
		// se criar um negocio no dia 15
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(10, 5, c);
		
		// ainda que eu tente mudar a data para 20
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		//ele continua no dia 15
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10, 5, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComPrecoNegativo(){
		new Negociacao(-10, 100, Calendar.getInstance());
	}
	
	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();
		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}
	
	@Test
	public void comHorariosDiferentesENoMesmoDia() {
		Calendar manha = new GregorianCalendar(2015, 07, 22, 8, 30);
		Calendar tarde = new GregorianCalendar(2015, 07, 22, 15, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
	}
	
	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia(){
		Calendar mesPassado = new GregorianCalendar(2015, 06, 22, 8, 30);
		Calendar esteMes = new GregorianCalendar(2015, 07, 22, 8, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, mesPassado);
		Assert.assertFalse(negociacao.isMesmoDia(esteMes));
	}
	
	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia(){
		Calendar anoPassado = new GregorianCalendar(2014, 07, 22, 8, 30);
		Calendar esteAno = new GregorianCalendar(2015, 07, 22, 8, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, anoPassado);
		Assert.assertFalse(negociacao.isMesmoDia(esteAno));
	}
	
}
