package fr.pizzeria.model;
import fr.pizzeria.utils.*;
import java.lang.reflect.*;

public class Pizza {
	public static int currentId = 0;
	public int id;
	@ToString(uppercase = true)
	public String code;
	@ToString
	public String libelle;
	@Rule(min=10)
	public double prix;
	public CategoryPizza category;

	public Pizza() {
		this.id = -1;
	}
	public Pizza(String code, String libelle, double prix, CategoryPizza category)  {
		this.id = currentId++;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.category = category;
		StringUtils.convertString(this,"code");
		StringUtils.convertString(this,"libelle");
	}
	public Pizza(int id,String code, String libelle, double prix, CategoryPizza category)  {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.category = category;
		StringUtils.convertString(this,"code");
		StringUtils.convertString(this,"libelle");

	}
	
	public String toString() {
		String description = "pizza n°" + this.id + " : ";
		StringUtils.convertString(this,"code");
		StringUtils.convertString(this,"libelle");

		description += this.code + " -> ";
		description += this.libelle + "(";
		description +=  this.prix + "€)";
		
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
