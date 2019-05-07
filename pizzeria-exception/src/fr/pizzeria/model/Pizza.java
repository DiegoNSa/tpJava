package fr.pizzeria.model;

public class Pizza {
	public static int currentId = 0;
	public int id;
	public String code;
	public String libelle;
	public double prix;

	public Pizza() {
		this.id = -1;
	}
	public Pizza(String code, String libelle, double prix) {
		this.id = currentId++;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
	}
	public Pizza(int id,String code, String libelle, double prix) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
	}
}
