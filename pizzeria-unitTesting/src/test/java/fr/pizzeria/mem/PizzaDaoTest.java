package fr.pizzeria.mem;

import java.util.ArrayList;

import org.junit.*;

import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoTest {
	
	@Test
	public void findAllPizzas() {
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();

		PizzaDao pizzadb = new PizzaDao();
		pizzaArray.add(new Pizza("PEP", "Pépéroni", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("MAR", "Margherita", 14.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("REIN", "La Reine", 11.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("FRO", "La 3 fromage", 12.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("CAN", "La cannibale", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("SAV", "La savoyarde", 13.0, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("ORI", "L'orientale", 13.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("IND", "L'indienne", 14.0, CategoryPizza.VIANDE));

		ArrayList<Pizza> resultArray = pizzadb.findAllPizzas();
		Assert.assertArrayEquals(resultArray.toArray(), pizzaArray.toArray());
	}
	
	@Test
	public void deletePizzaTest1() {
		PizzaDao pizzadb = new PizzaDao();
		PizzaDao pizzadbTest = new PizzaDao();

		pizzadb.deletePizza("ARO");
		
		Assert.assertArrayEquals(pizzadb.findAllPizzas().toArray(),pizzadbTest.findAllPizzas().toArray());	
	}

	@Test
	public void deletePizzaTest2() {
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
		pizzaArray.add(new Pizza("PEP", "Pépéroni", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("MAR", "Margherita", 14.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("REIN", "La Reine", 11.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("FRO", "La 3 fromage", 12.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("CAN", "La cannibale", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("SAV", "La savoyarde", 13.0, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("IND", "L'indienne", 14.0, CategoryPizza.VIANDE));

		
		PizzaDao pizzadb = new PizzaDao();
		PizzaDao pizzadbTest = new PizzaDao();

		pizzadb.deletePizza("ORI");
		pizzadbTest.setPizzaArray(pizzaArray);
		Assert.assertArrayEquals(pizzadb.findAllPizzas().toArray(),pizzadbTest.findAllPizzas().toArray());	
	}
	
	@Test
	public void pizzaExists1() {
		PizzaDao pizzadb = new PizzaDao();

		boolean res = pizzadb.pizzaExists("ARI");
		Assert.assertEquals(res, false);
	}
	
	@Test
	public void pizzaExists2() {
		PizzaDao pizzadb = new PizzaDao();

		boolean res = pizzadb.pizzaExists("ORI");
		Assert.assertEquals(res, true);
	}
	
	@Test
	public void saveNewPizzaTest1() {
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
		pizzaArray.add(new Pizza("PEP", "Pépéroni", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("MAR", "Margherita", 14.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("REIN", "La Reine", 11.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("FRO", "La 3 fromage", 12.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("CAN", "La cannibale", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("SAV", "La savoyarde", 13.0, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("ORI", "L'orientale", 13.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("IND", "L'indienne", 14.0, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("TRA", "tralooolo", 12.0, CategoryPizza.POISSON));

		Pizza newPizza = new Pizza("TRA","tralooolo",12.0,CategoryPizza.POISSON);
		
		PizzaDao pizzadb = new PizzaDao();
		PizzaDao pizzadbTest = new PizzaDao();
		pizzadbTest.setPizzaArray(pizzaArray);
		pizzadb.saveNewPizza(newPizza);
		
		Assert.assertArrayEquals(pizzadb.findAllPizzas().toArray(),pizzadbTest.findAllPizzas().toArray());
	}
	
	@Test
	public void saveNewPizzaTest2() {
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
		pizzaArray.add(new Pizza("PEP", "Pépéroni", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("MAR", "Margherita", 14.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("REIN", "La Reine", 11.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("FRO", "La 3 fromage", 12.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("CAN", "La cannibale", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("SAV", "La savoyarde", 13.0, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("ORI", "L'orientale", 13.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("IND", "L'indienne", 14.0, CategoryPizza.VIANDE));

		Pizza newPizza = new Pizza("ORI","tralooolo",12.0,CategoryPizza.POISSON);
		
		PizzaDao pizzadb = new PizzaDao();
		PizzaDao pizzadbTest = new PizzaDao();
		pizzadbTest.setPizzaArray(pizzaArray);
		pizzadb.saveNewPizza(newPizza);
		
		Assert.assertArrayEquals(pizzadb.findAllPizzas().toArray(),pizzadbTest.findAllPizzas().toArray());
	}
	
	//update test in classical case. result : update the pizza
	@Test
	public void updatePizzaTest1() {
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
		pizzaArray.add(new Pizza("PEP", "Pépéroni", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("MAR", "Margherita", 14.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("REIN", "La Reine", 11.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("FRO", "La 3 fromage", 12.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("CAN", "La cannibale", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("SAV", "La savoyarde", 13.0, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("ORI", "L'orientale", 13.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("TRA", "tralooolo", 12.0, CategoryPizza.POISSON));

		Pizza newPizza = new Pizza("TRA","tralooolo",12.0,CategoryPizza.POISSON);
		
		PizzaDao pizzadb = new PizzaDao();
		PizzaDao pizzadbTest = new PizzaDao();
		pizzadbTest.setPizzaArray(pizzaArray);
		pizzadb.updatePizza("IND",newPizza);
		
		Assert.assertArrayEquals(pizzadb.findAllPizzas().toArray(),pizzadbTest.findAllPizzas().toArray());

	}
	//update test when the code to find does not exists. result : does nothing
	@Test
	public void updatePizzaTest2() {
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
		pizzaArray.add(new Pizza("PEP", "Pépéroni", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("MAR", "Margherita", 14.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("REIN", "La Reine", 11.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("FRO", "La 3 fromage", 12.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("CAN", "La cannibale", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("SAV", "La savoyarde", 13.0, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("ORI", "L'orientale", 13.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("IND", "L'indienne", 14.0, CategoryPizza.VIANDE));

		Pizza newPizza = new Pizza("TRA","tralooolo",12.0,CategoryPizza.POISSON);
		
		PizzaDao pizzadb = new PizzaDao();
		PizzaDao pizzadbTest = new PizzaDao();
		pizzadbTest.setPizzaArray(pizzaArray);
		pizzadb.updatePizza("AND",newPizza);
		
		Assert.assertArrayEquals(pizzadb.findAllPizzas().toArray(),pizzadbTest.findAllPizzas().toArray());

	}
	
	//update test while keeping the same code. result : update the pizza
	@Test
	public void updatePizzaTest3() {
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
		pizzaArray.add(new Pizza("PEP", "Pépéroni", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("MAR", "Margherita", 14.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("REIN", "La Reine", 11.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("FRO", "La 3 fromage", 12.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("CAN", "La cannibale", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("SAV", "La savoyarde", 13.0, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("ORI", "L'orientale", 13.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("IND","tralooolo",12.0,CategoryPizza.POISSON));

		Pizza newPizza = new Pizza("IND","tralooolo",12.0,CategoryPizza.POISSON);
		
		PizzaDao pizzadb = new PizzaDao();
		PizzaDao pizzadbTest = new PizzaDao();
		pizzadbTest.setPizzaArray(pizzaArray);
		pizzadb.updatePizza("IND",newPizza);
		
		Assert.assertArrayEquals(pizzadb.findAllPizzas().toArray(),pizzadbTest.findAllPizzas().toArray());

	}

	//update test with the new code already existing for another pizza. result : does nothing
	@Test
	public void updatePizzaTest4() {
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
		pizzaArray.add(new Pizza("PEP", "Pépéroni", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("MAR", "Margherita", 14.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("REIN", "La Reine", 11.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("FRO", "La 3 fromage", 12.0, CategoryPizza.SANS_VIANDE));
		pizzaArray.add(new Pizza("CAN", "La cannibale", 12.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("SAV", "La savoyarde", 13.0, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("ORI", "L'orientale", 13.5, CategoryPizza.VIANDE));
		pizzaArray.add(new Pizza("IND", "L'indienne", 14.0, CategoryPizza.VIANDE));

		Pizza newPizza = new Pizza("ORI","tralooolo",12.0,CategoryPizza.POISSON);
		
		PizzaDao pizzadb = new PizzaDao();
		PizzaDao pizzadbTest = new PizzaDao();
		pizzadbTest.setPizzaArray(pizzaArray);
		pizzadb.updatePizza("IND",newPizza);
		
		Assert.assertArrayEquals(pizzadb.findAllPizzas().toArray(),pizzadbTest.findAllPizzas().toArray());
	}
	
	//find pizza with existing code. result : return the pizza of given code
	@Test
	public void findPizzaByCodeTest1() {
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
		Pizza newPizza = new Pizza("ORI", "L'orientale", 13.5, CategoryPizza.VIANDE);
		PizzaDao pizzadb = new PizzaDao();
		Pizza resPizza = pizzadb.findPizzaByCode("ORI");
		Assert.assertEquals(resPizza, newPizza);
	}
	
	//find pizza with non-existing code. result : return null
	@Test
	public void findPizzaByCodeTest2() {
		ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
		PizzaDao pizzadb = new PizzaDao();
		Pizza resPizza = pizzadb.findPizzaByCode("ARI");
		Assert.assertNull(resPizza);
	}
}
