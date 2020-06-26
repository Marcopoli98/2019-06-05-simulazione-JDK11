package it.polito.tdp.crimes.model;

public class Adiacenza implements Comparable<Adiacenza> {

	private int id;
	private double peso;

	public Adiacenza(int id, double peso) {
		super();
		this.id = id;
		this.peso = peso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Adiacenza o) {
		if (this.peso > o.peso)
			return 1;
		else if (this.peso < o.peso)
			return -1;
		else
			return 0;
	}

}
