package fr.itemize.mem;

import java.util.ArrayList;

import fr.itemize.model.*;

public interface IItemDao {
	ArrayList<FactoryItem> findAllItem();
	public FactoryItem getItem(String codeItem);
	void addNewItem(FactoryItem item);
	void deleteItem(String codeItem);
	boolean ItemExists(String codeItem);
}
