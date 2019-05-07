package fr.itemize.model;
import java.util.List;

public class FactoryType {
	public String typeName;
	public List<String> variables;
	
	public FactoryType(String name) {
		typeName = name;
	}
	
	public FactoryType(String name,List<String> variables) {
		this.variables = variables;
		typeName = name;
	}
}
