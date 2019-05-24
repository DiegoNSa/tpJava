package fr.pizzeria.controler;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzaria.exception.PizzaException;
import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaServiceTest {
	@Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	
    @Test
    public void executerTest1() {
    	systemInMock.provideLines("PEP", "peperoni","12","Viande");

    	PizzaDao testDao = new PizzaDao();
    	
    	testDao.setPizzaArray(new ArrayList<Pizza>());
    	AjouterPizzaService newService = new AjouterPizzaService();
    	
    	
		try {
			newService.executerUC(testDao);
		} catch (PizzaException e) {}

		Assert.assertEquals(1,testDao.findAllPizzas().size());
    	Assert.assertEquals("PEP", testDao.findAllPizzas().get(0).getCode());
    	Assert.assertEquals("peperoni", testDao.findAllPizzas().get(0).getLibelle());
    	Assert.assertEquals(12.0, testDao.findAllPizzas().get(0).getPrix(),0);
    	Assert.assertEquals(CategoryPizza.VIANDE, testDao.findAllPizzas().get(0).getCategory());
    	
    	
    }
    
    //test adding pizza with already existing code
    @Test(expected = PizzaException.class)
    public void executerTest2() throws PizzaException{
    	
    	systemInMock.provideLines("PEP", "peperoni","12","Viande");

    	PizzaDao testDao = new PizzaDao();
    	AjouterPizzaService newService = new AjouterPizzaService();
    	
		newService.executerUC(testDao);
    }
    
    //test adding pizza with invalid price
    @Test(expected = PizzaException.class)
    public void executerTest3() throws PizzaException{
    	
    	systemInMock.provideLines("PEP", "peperoni","1z2","Viande");

    	PizzaDao testDao = new PizzaDao();
    	AjouterPizzaService newService = new AjouterPizzaService();
    	
		newService.executerUC(testDao);
    }
    
    //test adding pizza with invalid category
    @Test(expected = PizzaException.class)
    public void executerTest4() throws PizzaException{
    	
    	systemInMock.provideLines("PEP", "peperoni","12","Viand");

    	PizzaDao testDao = new PizzaDao();
    	AjouterPizzaService newService = new AjouterPizzaService();
    	
		newService.executerUC(testDao);
    }
}
