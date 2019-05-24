package fr.pizzeria.controler;

import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzaria.exception.PizzaException;
import fr.pizzeria.mem.PizzaDao;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

public class ListerPizzaServiceTest {
	@Rule
    public final TextFromStandardInputStream systemInMock = 
        emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    
    @Test
    public void executerTest1() {
    	PizzaDao testDao = new PizzaDao();
    	ListerPizzaService newService = new ListerPizzaService();

    	try {
			newService.executerUC(testDao);
		} catch (PizzaException e) {
			e.printStackTrace();
		}
    	
    	Assert.assertEquals(systemOutRule.getLog(),"Liste des Pizza\r\n" + 
    			"pizza n°0 : PEP -> Pépéroni (12.5€) Viande\r\n" + 
    			"pizza n°0 : MAR -> Margherita (14.0€) Sans viande\r\n" + 
    			"pizza n°0 : REIN -> La Reine (11.5€) Viande\r\n" + 
    			"pizza n°0 : FRO -> La 3 fromage (12.0€) Sans viande\r\n" + 
    			"pizza n°0 : CAN -> La cannibale (12.5€) Viande\r\n" + 
    			"pizza n°0 : SAV -> La savoyarde (13.0€) Viande\r\n" + 
    			"pizza n°0 : ORI -> L'orientale (13.5€) Viande\r\n" + 
    			"pizza n°0 : IND -> L'indienne (14.0€) Viande\r\n" + 
    			"");
    }
}
