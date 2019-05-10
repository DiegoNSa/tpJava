package converter.writer;

import converter.storage.StorageDataBase;

public interface GlobalWriter {
	public String getSpecificFormat(StorageDataBase data);
	public void printSpecificFormat(StorageDataBase data);
	public void writeSpecificFormat(StorageDataBase data);
}
