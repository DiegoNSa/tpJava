package fr.itemize.mem;

import fr.pizzeria.model.*;

public interface IItemDao {
	String[] findAllType();
	void addNewType(FactoryType type);
	void deleteType(String codeType);
	boolean typeExists(String codeType);
}
