package fr.pizzeria.model;
import fr.pizzeria.utils.*;
import java.lang.reflect.*;

import javax.persistence.*;

@Entity
@Table(name="pizzas")
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="CODE_PIZZA",length=4,nullable=false)
	private String code;
	@Column(name="NOM_PIZZA",length=100,nullable=false)
	private String libelle;
	@Column(name="PRIX",length=42,nullable=false)
	private double prix;
	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORIE_PIZZA",length=30,nullable=false)
	private CategoryPizza category;

	public Pizza() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public CategoryPizza getCategory() {
		return category;
	}

	public void setCategory(CategoryPizza category) {
		this.category = category;
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
