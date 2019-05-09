package converter.writer;

import converter.storage.StoragaDataBase;

public interface GlobalWriter {
	public String getSpecificFormat(StoragaDataBase data);
	public void printSpecificFormat(StoragaDataBase data);

}
