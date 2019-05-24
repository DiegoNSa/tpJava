package fr.pizzeria.controler;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.ArrayList;

import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;

import fr.pizzaria.exception.*;
import fr.pizzeria.mem.*;
import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;


public class ModifierPizzaServiceTest {
	@Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	
    @Test(expected = UpdatePizzaException.class)
    public void executerTestWhenDaoNull() throws PizzaException {
    	systemInMock.provideLines("PEP", "peperoni","12","Viande");

    	IPizzaDao mockDao = Mockito.mock(IPizzaDao.class);
    	Mockito.when(mockDao.findAllPizzas()).thenReturn(null);
    	
    	ModifierPizzaService newService = new ModifierPizzaService();
		newService.executerUC(mockDao);
    }
    
    @Test(expected = UpdatePizzaException.class)
    public void executerTestWhenIncoherentDao() throws PizzaException {
    	systemInMock.provideLines("PEP", "peperoni","12","Viande");

    	Pizza secondPizza = new Pizza(null,"oriental",12.5,CategoryPizza.VIANDE);
    	ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
    	pizzaList.add(null);
    	pizzaList.add(secondPizza);
    	
    	IPizzaDao mockDao = Mockito.mock(IPizzaDao.class);
    	Mockito.when(mockDao.findAllPizzas()).thenReturn(pizzaList);
    	
    	ModifierPizzaService newService = new ModifierPizzaService();
		newService.executerUC(mockDao);

    }
    
    
}
