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
		//convert the strings accordingly to the annotation
		
		StringUtils.convertString(this);
	}
	public Pizza(int id,String code, String libelle, double prix, CategoryPizza category)  {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.category = category;
		StringUtils.convertString(this);

	}
	
	public String toString() {
		String description = "pizza n°" + this.id + " : ";
		StringUtils.convertString(this,"code");
		StringUtils.convertString(this,"libelle");

		description += this.code + " -> ";
		description += this.libelle + " (";
		description +=  this.prix + "€) ";
		description += this.category.getDescription();
		return description;
	}
}
