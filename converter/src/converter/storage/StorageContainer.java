package converter.storage;

import java.util.ArrayList;

public class StorageContainer extends StorageElement {
	public ArrayList<StorageElement> propertyList;
	
	public StorageContainer() {
		this.level = 0;
		propertyList = new ArrayList<StorageElement>();
	}
	
	public void addNewElement(StorageElement newEl) {
		newEl.level = this.level+1;
		propertyList.add(newEl);
	}
}
