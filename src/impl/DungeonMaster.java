package impl;

import java.util.ArrayList;

import contracts.CowContract;
import contracts.EditMapContract;
import contracts.EngineContract;
import contracts.EntityContract;
import contracts.EnvironmentContract;
import contracts.PlayerContract;
import utils.Cell;
import utils.Dir;
import utils.Node;
import utils.Tools;

public class DungeonMaster {
	EngineContract enginec;
	public DungeonMaster() {
	}

	public void init() {
		//initialisation d'une partie avec une carte vide de taille 10x10, 1 joueur, 2 monstres et 3 vaches


		int heigth ;
		int width ;

		heigth = 30;
		width = 20;

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

		for (EntityContract entityContract : mobsc) {
			Dir randDir = Tools.randomElement(Dir.values());
			boolean placed = false;

			do{
				Node randNode = Tools.randomElement(emp);
				if(envc.getCellContent(randNode.x, randNode.y)==null){
					entityContract.init(envc, randNode.x, randNode.y, randDir);
					placed=true;
				}
			}while(!placed);
		}


		enginec = new EngineContract(new EngineImpl());
		enginec.init(envc);

		//Add entities
		//Player
		enginec.addEntity(playerc);

		//Cows
		for (CowContract cowContract : cowsc) {
			enginec.addEntity(cowContract);
		}

		//Mobs
		for (EntityContract entityContract : mobsc) {
			enginec.addEntity(entityContract);
		}

//		System.out.println("Hello welcome to dungeon master"); 
//		System.out.println("you're playing on a " + mapc.getHeight()+" x " + mapc.getWidth() + " map with");
//		System.out.println(mobsc.length+" mobs and "+cowsc.length+" cows.");
//		System.out.println();

		Tools.printEnv(envc);


	}
	
	public Cell getCellNature(int x, int y) {
		return enginec.envi().getCellNature(x, y);
	}
	
	public Node getPlayerPos() {
		return new Node(enginec.getEntity(0).getCol(), enginec.getEntity(0).getRow());
	}
	

	public void test() {
		System.out.println("Test");
	}
}


