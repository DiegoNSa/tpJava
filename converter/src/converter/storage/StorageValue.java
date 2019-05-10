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
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void print() {
		String printString = "";
		for(int i = 0; i < this.level; i++) {
			printString += "-";
		}
		System.out.println(printString + this.elementTag + "=" + this.getValue());
	}
}
