package fr.itemize.controller;
import fr.itemize.mem.*;
import fr.itemize.model.*;

public class ListerObjectService extends MenuService {

	public void executerUC(GlobalDao globalDataBase) {
		System.out.println("Liste des Pizza");
		for(FactoryItem currentItem : globalDataBase.myItemDao.findAllItem()) {
			System.out.print(currentItem.itemArguments.defineType.typeName + " n°");
			System.out.print(currentItem.id + " : ");
			System.out.print(currentItem.code + " -> ");
			System.out.print(currentItem.itemArguments.describe());
		}
	}

}
