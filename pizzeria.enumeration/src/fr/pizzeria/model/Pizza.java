package fr.pizzeria.model;

public class Pizza {
	public static int currentId = 0;
	public int id;
	public String code;
	public String libelle;
	public double prix;
	public CategoryPizza category;

	public Pizza() {
		this.id = -1;
	}
	public Pizza(String code, String libelle, double prix, CategoryPizza category) {
		this.id = currentId++;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.category = category;
	}
	public Pizza(int id,String code, String libelle, double prix, CategoryPizza category) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.category = category;

	}
	
	public String toString() {
		String description = "pizza n°" + this.id + " : " + this.code + " -> " + this.libelle + "(" + this.prix + "€)";
		switch(this.category){
			case VIANDE: 
				return description + " Viande";
			case POISSON:
				return description + " Poisson";
			case SANS_VIANDE:
				return description + " Sans Viande";
		}
		return description;
	}
}
