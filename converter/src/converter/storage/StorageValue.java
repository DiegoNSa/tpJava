package converter.storage;

public class StorageValue<T> extends StorageElement {
	private T elValue;
	
	public StorageValue(String name,T val){
		this.elementTag = name;
		this.elValue = val;
	}
	
	public T getValue() {
		return elValue;
	}
	public void setValue(T newVal) {
		elValue = newVal;
	}
}
