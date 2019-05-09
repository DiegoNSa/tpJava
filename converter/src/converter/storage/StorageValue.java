package converter.storage;

public class StorageValue<T> extends StorageElement {
	private T elValue;
	
	public T getValue() {
		return elValue;
	}
	public void setValue(T newVal) {
		elValue = newVal;
	}
}
