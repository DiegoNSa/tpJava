package fr.pizzaria.exception;

public class ConnectionFailedException extends PizzaException {
	public ConnectionFailedException(){
		super("unable to connect to server. Check your connection or address and start again.\n Aborting");
	}
	public ConnectionFailedException(String msg) {
		super(msg);
	}

}