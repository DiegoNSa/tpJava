package fr.itemize.model;

public class FactoryItem {
	public static int currentId = 0;
	public int id;
	public String code;
	public FactoryObject itemArguments;
	
	public FactoryItem() {
		this.id = -1;
	}
	
	public FactoryItem(String code, FactoryObject newItem) {
		this.id = currentId++;
		this.code = code;
		this.itemArguments = newItem;
	}
	public FactoryItem(int id,String code, FactoryObject newItem) {
		this.id = id;
		this.code = code;
		this.itemArguments = newItem;
;
	}
}
