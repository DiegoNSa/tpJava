package fr.itemize.controller;
import fr.itemize.mem.*;
import fr.itemize.model.*;

public class ListerTypeService extends MenuService {

	public void executerUC(GlobalDao globalDataBase) {
		System.out.println("Liste des Pizza");
		for(FactoryType currentType : globalDataBase.myObjectDao.findAllType()) {
			System.out.print(currentType.typeName + " (");
			for(String currentVariable : currentType.variables) {
				System.out.print(currentVariable + ",");
			}
			System.out.println(")");
		}
	}

}
