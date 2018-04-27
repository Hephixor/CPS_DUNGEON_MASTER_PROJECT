package impl;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Dir;
import utils.Node;
import utils.Tools;
import contracts.CowContract;
import contracts.EditMapContract;
import contracts.EngineContract;
import contracts.EntityContract;
import contracts.EnvironmentContract;
import contracts.PlayerContract;

public class DungeonMaster {

	public static void main(String[] args) {

		//initialisation d'une partie avec une carte vide de taille 10x10, 1 joueur, 2 monstres et 3 vaches

		
		int heigth ;
		int width ;
				Scanner sc = new Scanner(System.in);
				System.out.print("Heigth : ");
				heigth = sc.nextInt();
				System.out.print("Width : ");
				width = sc.nextInt();
				sc.close();

//		heigth = 20;
//		width = 25;
//		
		

		//Map & Env
		EditMapContract mapc = new EditMapContract(new  EditMapImpl());
		mapc.init(width, heigth);
		EnvironmentContract envc = new EnvironmentContract(new EnvironmentImpl());
		envc.init(mapc);

		//Entities
		PlayerContract playerc = new PlayerContract(new PlayerImpl());

		playerc.init(envc, Tools.getIn(envc).x, Tools.getIn(envc).y, Dir.N, 10);

		EntityContract[] mobsc = new EntityContract[2];
		mobsc[0] = new EntityContract(new EntityImpl());
		mobsc[1] = new EntityContract(new EntityImpl());

		CowContract[] cowsc = new CowContract[3];
		cowsc[0] = new CowContract(new CowImpl());
		cowsc[1] = new CowContract(new CowImpl());
		cowsc[2] = new CowContract(new CowImpl());

		
		//Place entities
		ArrayList<Node> emp = Tools.getEmp(envc);
		
		for (CowContract cowContract : cowsc) {
			Dir randDir = Tools.randomElement(Dir.values());
			boolean placed = false;
			
			do{
				Node randNode = Tools.randomElement(emp);
				if(envc.getCellContent(randNode.x, randNode.y)==null){
					cowContract.init(envc, randNode.x, randNode.y, randDir);
					placed=true;
				}
			}while(!placed);
		}

//
//		mobsc[0].init(envc, 8, 8, Dir.S);
//
//		mobsc[1].init(envc, 5, 5, Dir.S);


		//		System.out.println("Affichage Envi ");
		//		printEnv(envc);



		EngineContract enginec = new EngineContract(new EngineImpl());
		enginec.init(envc);

		
		enginec.addEntity(playerc);
		
		enginec.addEntity(cowsc[2]);
		
		enginec.addEntity(cowsc[0]);
		enginec.addEntity(cowsc[1]);

		System.out.println("Hello welcome to dungeon master"); 
		System.out.println("you're playing on a " + mapc.getHeight()+" x " + mapc.getWidth() + " map with");
		System.out.println(mobsc.length+" mobs and "+cowsc.length+" cows.");
		System.out.println();
		
		Tools.printEnv(envc);
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

		

	}

	
}

