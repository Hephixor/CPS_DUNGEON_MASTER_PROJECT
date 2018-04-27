package impl;

import services.EntityService;
import services.EnvironmentService;
import services.MapService;
import utils.Cell;
import utils.Dir;
import contracts.*;

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
		width = 10;


		EditMapContract mapc = new EditMapContract(new  EditMapImpl());
		mapc.init(width, heigth);
	//	System.out.println("AAAAAAA");
	//	printMap(mapc);

		EnvironmentContract envc = new EnvironmentContract(new EnvironmentImpl());
		
		envc.init(mapc);

		

		PlayerContract playerc = new PlayerContract(new PlayerImpl());

		EntityContract[] mobsc = new EntityContract[2];
		mobsc[0] = new EntityContract(new EntityImpl());
		mobsc[1] = new EntityContract(new EntityImpl());

		CowContract[] cowsc = new CowContract[3];
		cowsc[0] = new CowContract(new CowImpl());
		cowsc[1] = new CowContract(new CowImpl());
		cowsc[2] = new CowContract(new CowImpl());



		//ajouter les entities
		mapc.setNature(1, 1, Cell.EMP);
		

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

//		System.out.println("Affichage Envi ");
//		printEnv(envc);

	
		playerc.init(envc, 1, 1, Dir.N, 10);
		
		EngineContract enginec = new EngineContract(new EngineImpl());
		enginec.init(envc);
		System.out.println("ajout1");
		
		enginec.addEntity(mobsc[0]);
		System.out.println("DM:"+enginec.getEntities());
		System.out.println("ajout2");
		System.out.println("DM:"+enginec.getEntities());
		enginec.addEntity(playerc);
		System.out.println("DM:"+enginec.getEntities());
		System.out.println("ajout3");
		enginec.addEntity(cowsc[2]);
		enginec.addEntity(cowsc[0]);
		enginec.addEntity(mobsc[1]);
		enginec.addEntity(cowsc[1]);
		

		System.out.println("After Engine");
		printEnv(envc);
		/*for(int y = 0 ; y < envc.getHeight(); y++ ){
			for(int x = 0 ; x < envc.getWidth() ; x++){
				if(envc.getCellContent(x, y)==null){
					System.out.print("[ ]");
				}
				else if(envc.getCellContent(x, y) instanceof PlayerContract){
					System.out.print("[P]");
				}
				else if(envc.getCellContent(x, y) instanceof EntityContract){
					System.out.print("[M]");
				}
				else if(envc.getCellContent(x, y) instanceof CowContract){
					System.out.print("[V]");
				}
				else{
					System.out.print(envc.getCellContent(x, y) + " ");
				}
			}
			System.out.println();
		}*/

		System.out.println("Hello welcome to dungeon master"); 
		System.out.println("you're playing on a " + mapc.getHeight()+" x " + mapc.getWidth() + " map with");
		System.out.println(mobsc.length+" mobs and "+cowsc.length+" cows.");

	}






	public static void printEnv(EnvironmentService env){

		for(int y = 0 ; y < env.getHeight(); y++ ){
			for(int x = 0 ; x < env.getWidth() ; x++){
				switch(env.getCellNature(x,y)){
				case WLL:
					System.out.print("##.");
					break;
				case IN:
					System.out.print("IN.");
					break;
				case OUT:
					System.out.print("OUT");
					break;
				case EMP:
					if(env.getCellContent(x,y) == null) {
						System.out.print("  .");

					}
					else if(env.getCellContent(x,y) instanceof CowImpl) {
						System.out.print("VV.");

					}
					else if(env.getCellContent(x,y) instanceof PlayerImpl) {
						System.out.print("PP.");

					}
					else if(env.getCellContent(x,y) instanceof MobImpl) {
						System.out.print("MM.");

					}
					else{
						//Impossible
					}
				default:
					break;
				}
			}
			System.out.println();
		}
	}

	public static void printMap(MapService map){

		for(int y = 0 ; y < map.getHeight(); y++ ){
			for(int x = 0 ; x < map.getWidth() ; x++){
				switch(map.getCellNature(x,y)){
				case WLL:
					System.out.print("##.");
					break;
				case IN:
					System.out.print("IN.");
					break;
				case OUT:
					System.out.print("OUT");
					break;
				case EMP:
					System.out.print("  .");	
				default:
					break;
				}
			}
			System.out.println();
		}
	}

	public static void printMap(Cell[][] map){

		for(int y = 0 ; y < map.length; y++ ){
			for(int x = 0 ; x < map[0].length ; x++){
				switch(map[y][x]){
				case WLL:
					System.out.print("##.");
					break;
				case IN:
					System.out.print("IN.");
					break;
				case OUT:
					System.out.print("OUT");
					break;
				case EMP:
					System.out.print("  .");	
				default:
					break;
				}
			}
			System.out.println();
		}
	}

}

