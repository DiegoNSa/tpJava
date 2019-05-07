package fr.pizzeria.model;


public enum CategoryPizza {
	VIANDE ("Viande"),
	POISSON ("Poisson"),
	SANS_VIANDE ("Sans viande");
	
	public String description;
	private CategoryPizza(String name) {
		this.description = name;
	}
	
	public static void enumerate() {
		int i = 1;
		for (CategoryPizza cat : CategoryPizza.values()) {
			System.out.println(i + ". " + cat.description);
			i++;
		}
	}
	
	public String getDescription() {
		return description;
	}
	
	public static CategoryPizza getCategory(String desc) {
		switch(desc) {
		case "Viande":
			return VIANDE;
		case "Poisson":
			return POISSON;
		case "Sans Viande":
			return SANS_VIANDE;
		}
		return null;
	}
}
