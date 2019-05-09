package converter.storage;

import java.util.ArrayList;

public class StorageDataBase {
	public ArrayList<StorageElement> datas;
	
	public StorageDataBase() {
		datas = new ArrayList<StorageElement>();
	}
	
	public void addNewData(StorageElement newData) {
		datas.add(newData);
	}
}
