package br.com.caelum.argentum.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Negociacao implements Comparable<Negociacao>{
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negociacao(double preco, int quantidade, Calendar data) {
		if(data == null){
			throw new IllegalArgumentException("A data não pode ser nula");
		}
		if(preco < 0){
			throw new IllegalArgumentException("O preço da negociação não pode ser menor que zero");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		if(data==null)
			throw new IllegalArgumentException("A data não deve ser nula!");
		
		return (Calendar) data.clone();
	}
	
	public double getVolume() {
		return preco * quantidade;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder()
				.append("[Preço ").append(preco)
				.append(", Quantidade ").append(quantidade)
				.append(", Data ").append(new SimpleDateFormat("dd/MM/yyyy").format(data.getTime()))
				.append("]");
		
		return s.toString();
	}

	public boolean isMesmoDia(Calendar outraData) {
		boolean mesmoDia = this.data.get(Calendar.DAY_OF_MONTH) == outraData.get(Calendar.DAY_OF_MONTH);
		boolean mesmoMes = this.data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH);
		boolean mesmoAno = this.data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR);
		
		return (mesmoDia && mesmoMes && mesmoAno);
	}

	@Override
	public int compareTo(Negociacao negociacao) {
		if(this.data.before(negociacao.getData())){
			return -1;
		}
		if(this.data.after(negociacao.getData())){
			return 1;
		}
		return 0;
	}
	
	
}
