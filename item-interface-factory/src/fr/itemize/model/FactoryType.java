package fr.itemize.model;
import java.util.List;

public class FactoryType {
	public String typeName;
	public List<String> variables;
	public String initValue;
	
	public FactoryType(String name, String value) {
		typeName = name;
		initValue = value;
	}
	
	public FactoryType(String name, String value,List<String> variables) {
		this.variables = variables;
		typeName = name;
		initValue = value;
	}
}
