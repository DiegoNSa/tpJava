package fr.pizzeria.bdd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.pizzeria.model.*;
import fr.pizzaria.exception.ConnectionFailedException;
import fr.pizzeria.mem.*;


public class BddManager {
	public static Connection connection;
	public static PreparedStatement myStatement;
	public static ResultSet myResult;
	public static int updateResult;

	public static String dataBaseAddress;
	public static String userName;
	public static String password;

	public static Boolean successResult;

	
	public static void connect() {
		if(dataBaseAddress==null) {
			dataBaseAddress = ResourceBundle.getBundle("jdbc").getString("URL");
			userName = ResourceBundle.getBundle("jdbc").getString("User_Name");
			password = ResourceBundle.getBundle("jdbc").getString("Password");
		}
		try {
			connection = DriverManager.getConnection(dataBaseAddress, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("unable to connect.\n"
					+ "Check your connection or address and try again later.\n"
					+ "Aborting...");
		}
	}
	
	public static void reinitialiseDB() {
		destroyDB();
		try {
			initialiseDB();
		} catch (ConnectionFailedException e) {
			e.printStackTrace();
		}
	}		
	
	public static void initialiseDB() throws ConnectionFailedException{

		
		try {
			/*connection = DriverManager.getConnection(dataBaseAddress, userName, password);
			myStatement = connection
					.prepareStatement("CREATE DATABASE IF NOT EXISTS pizzeria;");
			successResult = myStatement.execute();
			close();*/
			connect();
			myStatement = connection
					.prepareStatement("CREATE TABLE IF NOT EXISTS pizzas (\r\n" + 
							"	CODE_PIZZA nvarchar(4) NOT NULL PRIMARY KEY,\r\n" + 
							"	NOM_PIZZA nvarchar(100)  NOT NULL,\r\n" + 
							"    PRIX double(40,2) NOT NULL,\r\n" + 
							"	CATEGORIE_PIZZA varchar(30) NOT NULL CHECK(VALUE IN ('Viande','Poisson','Sans Viande'))\r\n" + 
							");");
			successResult = myStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			throw new ConnectionFailedException();
		}
		finally {
			close();
		}

	}

	public static void openConnection() {
		try {
			connection = DriverManager.getConnection(dataBaseAddress, userName, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int getDBPizzaCount() {
		try {
			connect();
			myStatement = connection
					.prepareStatement("SELECT COUNT(*) AS count FROM pizzas");
			myResult = myStatement.executeQuery();
			int countResult = 0;
			while(myResult.next()) {
				countResult = myResult.getInt("count");
			}
			close();
			return countResult;
		} catch (SQLException e) {
			e.printStackTrace();
			close();
		}
		
		return 0;
	}
	
	public static ArrayList<Pizza> retrievePizzas() {
		 ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
		try {
			
			connect();
			myStatement = connection
					.prepareStatement("SELECT * FROM pizzas");
			int index = 0;
			myResult = myStatement.executeQuery();
			while(myResult.next()) {
				String codePizza = myResult.getString("CODE_PIZZA");
				String nomPizza = myResult.getString("NOM_PIZZA");
				double prixPizza = myResult.getDouble("PRIX");
				CategoryPizza categoriePizza = CategoryPizza.getCategory(myResult.getString("CATEGORIE_PIZZA"));
				Pizza newPizza = new Pizza(codePizza,nomPizza,prixPizza,categoriePizza);
				pizzaList.add(newPizza);
				index++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return pizzaList;
	}
	
	public static void addPizza(Pizza newPizza) {
		try {
			connect();
			myStatement = connection
					.prepareStatement("INSERT INTO pizzas"
							+ " VALUES(?,?,?,?);");
			myStatement.setString(1, newPizza.getCode());
			myStatement.setString(2, newPizza.getLibelle());
			myStatement.setDouble(3, newPizza.getPrix());
			myStatement.setString(4, newPizza.getCategory().getDescription());

			updateResult = myStatement.executeUpdate();
			System.out.println("Successfully added " + updateResult + " elements");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
	}

	public static void removePizza(String codePizza) {
		try {
			connect();
			myStatement = connection
					.prepareStatement("DELETE FROM pizzas"
							+ " WHERE CODE_PIZZA=?;");
			myStatement.setString(1, codePizza);

			updateResult = myStatement.executeUpdate();
			System.out.println("Successfully removed " + updateResult + " elements");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
	}
	
	public static boolean checkPizza(String codePizza) {
		boolean checkResult = false;

		try {
			connect();
			myStatement = connection
					.prepareStatement("SELECT COUNT(*)>0 AS pizzaExists\r\n" + 
							"FROM pizzas\r\n" + 
							"WHERE CODE_PIZZA=?;");
			myStatement.setString(1, codePizza);

			myResult = myStatement.executeQuery();
			while(myResult.next()) {
				checkResult = myResult.getInt("pizzaExists") == 1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return checkResult;
	}
	
	public static void updatePizza(String codePizza ,Pizza newPizza) {
		try {
			connect();
			myStatement = connection
					.prepareStatement("UPDATE pizzas"
							+ " SET CODE_PIZZA = ?,NOM_PIZZA = ?,PRIX = ?, CATEGORIE_PIZZA = ? WHERE CODE_PIZZA = ?;");
			myStatement.setString(1, newPizza.getCode());
			myStatement.setString(2, newPizza.getLibelle());
			myStatement.setDouble(3, newPizza.getPrix());
			myStatement.setString(4, newPizza.getCategory().getDescription());
			myStatement.setString(5, codePizza);

			updateResult = myStatement.executeUpdate();
			System.out.println("Successfully updated " + updateResult + " elements");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}

	}
	
	public static void importDB(PizzaDao pizzaDatas) {
		if(getDBPizzaCount() == 0) {
			for(Pizza pizza : pizzaDatas.findAllPizzas()) {
				addPizza(pizza);
			}
		}else {
			System.out.println("already imported");
		}
	}
	
	public static void resetDB() {		
		try {
			connect();
			myStatement = connection
					.prepareStatement("TRUNCATE TABLE pizzas");
			updateResult = myStatement.executeUpdate();
			System.out.println("Successfully updated " + updateResult + " elements");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eraseDB() {		
		try {
			connect();
			myStatement = connection
					.prepareStatement("DROP TABLE pizzas");
			updateResult = myStatement.executeUpdate();
			System.out.println("Successfully updated " + updateResult + " elements");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void clearDB() {		
		try {
			connect();
			myStatement = connection
					.prepareStatement("DROP DATABASE pizzeria");
			updateResult = myStatement.executeUpdate();
			System.out.println("Successfully updated " + updateResult + " elements");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void destroyDB() {		
		try {
			connect();
			myStatement = connection
					.prepareStatement("DROP DATABASE pizzeria");
			updateResult = myStatement.executeUpdate();
			System.out.println("Successfully updated " + updateResult + " elements");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void exportDBtoDao(PizzaDao newPizzaDatasDao) {		
		try {
			connect();
			myStatement = connection
					.prepareStatement("SELECT * FROM pizzas");
			myResult = myStatement.executeQuery();
			while(myResult.next()) {
				String codePizza = myResult.getString("CODE_PIZZA");
				String nomPizza = myResult.getString("NOM_PIZZA");
				double prixPizza = myResult.getDouble("PRIX");
				CategoryPizza categoriePizza = CategoryPizza.getCategory(myResult.getString("CATEGORIE_PIZZA"));
				Pizza newPizza = new Pizza(codePizza,nomPizza,prixPizza,categoriePizza);
				newPizzaDatasDao.saveNewPizza(newPizza);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Pizza findPizza(String codePizza) {
		Pizza newPizza = null;
		try {
			connect();
			myStatement = connection
					.prepareStatement("SELECT * FROM pizzas WHERE CODE_PIZZA=?");
			myStatement.setString(1, codePizza);
			myResult = myStatement.executeQuery();
			while(myResult.next()) {
				String nomPizza = myResult.getString("NOM_PIZZA");
				double prixPizza = myResult.getDouble("PRIX");
				CategoryPizza categoriePizza = CategoryPizza.getCategory(myResult.getString("CATEGORIE_PIZZA"));
				 newPizza = new Pizza(codePizza,nomPizza,prixPizza,categoriePizza);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return newPizza;
	}
	
	public static void close() {
		try {
			if(myResult != null) {
				myResult.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(myStatement != null) {
				myStatement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
