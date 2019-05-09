package converter.storage;

import java.util.ArrayList;

public class StorageContainer extends StorageElement {
	public ArrayList<StorageElement> propertyList;
	public boolean isEnum = false;
	
	public StorageContainer() {
		this.level = 0;
		propertyList = new ArrayList<StorageElement>();
	}
	
	public StorageContainer(boolean isEnum) {
		this.level = 0;
		propertyList = new ArrayList<StorageElement>();
		this.isEnum = isEnum;
	}
	
	public void setLevel(int level) {
		this.level = level;
		for(StorageElement el :propertyList) {
			el.setLevel(this.level + 1);
		}
	}
	
	public void addNewElement(StorageElement newEl) {
		if(this.isEnum) {
			newEl.setLevel(this.level);

		}else {
			newEl.setLevel(this.level+1);

		}
		propertyList.add(newEl);
	}
}
