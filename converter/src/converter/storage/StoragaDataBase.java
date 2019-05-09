package converter.storage;

import java.util.ArrayList;

public class StoragaDataBase {
	public ArrayList<StorageElement> datas;
	
	
	public void addNewData(StorageElement newData) {
		datas.add(newData);
	}
}
