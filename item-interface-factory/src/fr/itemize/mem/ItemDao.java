package fr.itemize.mem;

import fr.itemize.model.FactoryItem;
import fr.itemize.model.FactoryType;

import java.util.ArrayList;
import java.util.Hashtable;

public class ItemDao implements IObjectDao {
	
	ArrayList<FactoryItem> itemList = new ArrayList<FactoryItem>();
	
	public ArrayList<FactoryItem> findAllItem() {
		return itemList;
	}
	
	public void addNewItem(FactoryItem type) {
		itemList.add(type);
	}
	public void deleteType(String codeType) {
		itemList.remove(this.getItem(codeType));
	}

	public FactoryItem getItem(String codeItem) {
		for(FactoryItem item : itemList) {
			if(item.code.equals(codeItem)) {
				return item;
			}
		}
		return null;
	}
	
	
	public boolean ItemExists(String codeItem) {
		for(FactoryItem item : itemList) {
			if(item.code.equals(codeItem)) {
				return true;
			}
		}
		return false;
	}
}
