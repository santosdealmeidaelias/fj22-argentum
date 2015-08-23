package br.com.caelum.argentum.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Candlestick {
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;
	
	public Candlestick(double abertura, double fechamento, double minimo, double maximo, double volume, Calendar data) {
		if(maximo < minimo){
			throw new IllegalArgumentException("O valor maxímo não deve ser menor que o mínimo!");
		}
		
		if(data == null){
			throw new IllegalArgumentException("A data do Candlestick não deve ser null!");
		}
		
		if(abertura < 0 || fechamento < 0 || minimo < 0 || maximo < 0 || volume < 0){
			throw new IllegalArgumentException("O Candlestick não pode ser criados com valores negativos!");
		}
			
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}
	
	public boolean isAlta() {
		return this.abertura <= this.fechamento;
	}
	
	public boolean isBaixa() {
		return this.abertura > this.fechamento;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[Abertura ").append(abertura);
		s.append(", Fechamento ").append(fechamento);
		s.append(", Mínima ").append(minimo);
		s.append(", Maxíma ").append(maximo);
		s.append(", Volume ").append(volume);		
		s.append(", Data ").append(new SimpleDateFormat("dd/MM/yyyy").format(data.getTime()));
		s.append("]");
		
		return s.toString();
	}
}
