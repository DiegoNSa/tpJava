package fr.itemize.mem;
import java.util.ArrayList;
import fr.itemize.model.FactoryType;
import java.util.Hashtable;

public class ObjectDao implements IObjectDao {
	
	Hashtable<String,FactoryType> typeTable = new Hashtable<String,FactoryType>();
	
	public ArrayList<FactoryType> findAllType() {
		int tableSize = typeTable.keySet().size();
		ArrayList<FactoryType> typeList = new ArrayList<FactoryType>();
		int i = 0;
		for(String key : typeTable.keySet()) {
			typeList.add(typeTable.get(key));
		}
		return typeList;
	}
	
	public void addNewType(FactoryType type) {
		if(typeTable.get(type.typeName) == null) {
			typeTable.put(type.typeName, type);	
		}
	}
	
	public FactoryType getType(String typeName) {
		return typeTable.get(typeName);
	}
	
	
	public void deleteType(String codeType) {
		typeTable.remove(codeType);
	}

	public boolean typeExists(String codeType) {
		return typeTable.get(codeType) != null;
	}
}
