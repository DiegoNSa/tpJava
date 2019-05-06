package fr.itemize.mem;

import fr.pizzeria.model.FactoryType;
import java.util.Hashtable;

public class ObjectDao implements IObjectDao {
	
	Hashtable<String,FactoryType> typeTable = new Hashtable<String,FactoryType>();
	
	public String[] findAllType() {
		int tableSize = typeTable.keySet().size();
		String[] typeList = new String[tableSize];
		int i = 0;
		for(String key : typeTable.keySet()) {
			typeList[i++] = key;
		}
		return typeList;
	}
	public void addNewType(FactoryType type) {
		if(typeTable.get(type.typeName) == null) {
			typeTable.put(type.typeName, type);	
		}
		
	}
	public void deleteType(String codeType) {
		typeTable.remove(codeType);
	}

	public boolean typeExists(String codeType) {
		return typeTable.get(codeType) != null;
	}
}
