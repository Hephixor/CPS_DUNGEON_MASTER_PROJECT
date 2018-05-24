package impl;

import java.util.ArrayList;

import services.EntityService;
import services.EnvironmentService;
import utils.*;
import contracts.EditMapContract;
import contracts.EngineContract;
import contracts.EntityContract;
import contracts.EnvironmentContract;
import contracts.PlayerContract;
import contracts.CowContract;
import services.CowService;

public class DungeonMaster {
	int heigth ;
	int width ;
	EngineContract enginec;
	public DungeonMaster() {
	}

	public void init() {
		//initialisation d'une partie avec une carte vide de taille 10x10, 1 joueur, 2 monstres et 3 vaches




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

		EntityService[] mobsc = new EntityService[2];
		mobsc[0] = new EntityContract(new EntityImpl());
		mobsc[1] = new EntityContract(new EntityImpl());

		CowService[] cowsc = new CowService[3];
		cowsc[0] = new CowContract(new CowImpl());
		cowsc[1] = new CowContract(new CowImpl());
		cowsc[2] = new CowContract(new CowImpl());


		//Place entities
		ArrayList<Node> emp = Tools.getEmp(envc);

		for (CowService cowContract : cowsc) {
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

		for (EntityService entityContract : mobsc) {
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
		for (CowService cowContract : cowsc) {
			enginec.addEntity(cowContract);
		}

		//Mobs
		for (EntityService entityContract : mobsc) {
			enginec.addEntity(entityContract);
		}

//		System.out.println("Hello welcome to dungeon master"); 
//		System.out.println("you're playing on a " + mapc.getHeight()+" x " + mapc.getWidth() + " map with");
//		System.out.println(mobsc.length+" mobs and "+cowsc.length+" cows.");
//		System.out.println();

		Tools.printEnv(envc);


	}
	
	public EnvironmentService getEnv(){
		return enginec.envi();
	}
	
	public void setPlayerCommand(Command com){
		((PlayerContract) enginec.getEntity(0)).setLastCom(com);
	}

	public void step(){
		enginec.step();
		Tools.printEnv(enginec.envi());
	}
	
	public Cell getCellNature(int x, int y) {
		return enginec.envi().getCellNature(x, y);
	}
	
	public Node getPlayerPos() {
		return new Node(enginec.getEntity(0).getCol(), enginec.getEntity(0).getRow());
	}
	

}


