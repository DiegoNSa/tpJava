package fr.itemize.mem;

import java.util.ArrayList;

import fr.itemize.model.*;

public interface IObjectDao {
	ArrayList<FactoryType> findAllType();
	void addNewType(FactoryType type);
	void deleteType(String codeType);
	boolean typeExists(String codeType);
}
