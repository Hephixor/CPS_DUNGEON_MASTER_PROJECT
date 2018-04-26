package impl;

import jdk.nashorn.internal.runtime.ECMAException;

import org.hamcrest.core.IsInstanceOf;

import contracts.CowContract;
import contracts.EditMapContract;
import contracts.EngineContract;
import contracts.EnvironmentContract;
import contracts.MobContract;
import contracts.PlayerContract;
import utils.Cell;
import utils.Dir;

public class DungeonMaster {

	public static void main(String[] args) {

		//initialisation d'une partie avec une carte vide de taille 10x10, 1 joueur, 2 monstres et 3 vaches


		int heigth ;
		int width ;
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Heigth : ");
//		heigth = sc.nextInt();
//		System.out.print("Width : ");
//		width = sc.nextInt();
//		sc.close();

		heigth = 20;
		width = 20;
		


		EditMapImpl mapimpl = new  EditMapImpl();
		//mapimpl.init(width, heigth);
		EditMapContract mapc = new EditMapContract(mapimpl);
		mapc.init(width, heigth);
		
		EnvironmentImpl env = new EnvironmentImpl();
		EnvironmentContract envc = new EnvironmentContract(env);
		envc.init(mapc);
		
		EngineImpl engine = new EngineImpl();
		EngineContract enginec = new EngineContract(engine);

		PlayerImpl player = new PlayerImpl();		
		PlayerContract playerc = new PlayerContract(player);
		
		MobContract[] mobsc = new MobContract[2];
		mobsc[0] = new MobContract(new MobImpl());
		mobsc[1] = new MobContract(new MobImpl());
		
		CowContract[] cowsc = new CowContract[3];
		cowsc[0] = new CowContract(new CowImpl());
		cowsc[1] = new CowContract(new CowImpl());
		cowsc[2] = new CowContract(new CowImpl());

		
		
		//ajouter les entities
		mapc.setNature(1, 1, Cell.EMP);
		playerc.init(envc, 1, 1, Dir.N, 10);
		
		mapc.setNature(8, 8, Cell.EMP);
		mobsc[0].init(envc, 8, 8, Dir.S);
		mapc.setNature(5, 5, Cell.EMP);
		mobsc[1].init(envc, 5, 5, Dir.S);
		mapc.setNature(2, 2, Cell.EMP);
		cowsc[0].init(envc, 2, 2, Dir.E, 4);
		mapc.setNature(3, 3, Cell.EMP);
		cowsc[1].init(envc, 3, 3, Dir.S, 4);
		mapc.setNature(4, 4, Cell.EMP);
		cowsc[2].init(envc, 4, 4, Dir.W, 4);

		
		for(int y = 0 ; y < mapc.getHeight(); y++ ){
			for(int x = 0 ; x < mapc.getWidth() ; x++){
				switch(envc.getCellNature(x,y)){
        		case WLL:
            		System.out.print("##.");
            		break;
        		case EMP:
        			if(envc.getCellContent(x,y) == null) {
        				System.out.print("  .");
						
					}
        			else if(envc.getCellContent(x,y) instanceof CowImpl) {
        				System.out.print("[].");
						
					}
        			else if(envc.getCellContent(x,y) instanceof PlayerImpl) {
        				System.out.print("OO.");
						
					}
        			else if(envc.getCellContent(x,y) instanceof MobImpl) {
        				System.out.print("XX.");
						
					}
        			else{
        				throw new ECMAException(null, null);
        			}
				}
			}
		System.out.println();
    }
		

	enginec.init(envc);
	enginec.addEntity(playerc);
	
	for(int y = 0 ; y < envc.getHeight(); y++ ){
		for(int x = 0 ; x < envc.getWidth() ; x++){
		System.out.print(envc.getCellContent(x, y) + " ");
		}
		System.out.println();
}
	
	System.out.println("Hello welcome to dungeon master"); 
	System.out.println("you're playing on a " + mapc.getHeight()+" x " + mapc.getWidth() + " map with");
	System.out.println(mobsc.length+" mobs and "+cowsc.length+" cows.");





		


	


	}

}
