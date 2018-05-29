package impl;

import java.util.ArrayList;


import utils.*;
import contracts.*;
import services.*;

public class DungeonMaster {
	int heigth ;
	int width ;
	EngineService enginec;
	public DungeonMaster() {
	}

	public void init() {
		//initialisation d'une partie avec une carte vide de taille 10x10, 1 joueur, 2 monstres et 3 vaches

		heigth = 20;
		width = 10;

		//Map & Env
		EditMapService maps = new EditMapContract(new  EditMapImpl());
		maps.init(width, heigth);

		EnvironmentService envs = new EnvironmentContract(new EnvironmentImpl());
		envs.init(maps);

		//Entities
		PlayerService players = new PlayerContract(new PlayerImpl());

		players.init(envs, Tools.getIn(envs).x, Tools.getIn(envs).y, Dir.N, 5);

		EntityService[] mobss = new EntityService[2];
		mobss[0] = new EntityContract(new EntityImpl());
		mobss[1] = new EntityContract(new EntityImpl());

		CowService[] cowss = new CowService[3];
		cowss[0] = new CowContract(new CowImpl());
		cowss[1] = new CowContract(new CowImpl());
		cowss[2] = new CowContract(new CowImpl());


		//Place entities
		ArrayList<Node> emp = Tools.getEmp(envs);

		for (CowService cowservice : cowss) {
			Dir randDir = Tools.randomElement(Dir.values());
			boolean placed = false;

			do{
				Node randNode = Tools.randomElement(emp);
				if(envs.getCellContent(randNode.x, randNode.y)==null){
					cowservice.init(envs, randNode.x, randNode.y, randDir);
					placed=true;
				}
			}while(!placed);
		}

		for (EntityService entityservice : mobss) {
			Dir randDir = Tools.randomElement(Dir.values());
			boolean placed = false;

			do{
				Node randNode = Tools.randomElement(emp);
				if(envs.getCellContent(randNode.x, randNode.y)==null){
					entityservice.init(envs, randNode.x, randNode.y, randDir);
					placed=true;
				}
			}while(!placed);
		}


		enginec = new EngineContract(new EngineImpl());
		enginec.init(envs);

		//Add entities
		
		//Player
		enginec.addEntity(players);

		//Cows
		for (CowService cowservice : cowss) {
			enginec.addEntity(cowservice);
		}

		//Mobs
		for (EntityService entityservice : mobss) {
			enginec.addEntity(entityservice);
		}

		getPlayer();
		Tools.printEnv(envs);

	}
	
	public EnvironmentService getEnv(){
		return enginec.envi();
	}
	
	public PlayerContract getPlayer() {
		if(enginec.getEntity(0) instanceof PlayerContract) {
			return (PlayerContract) enginec.getEntity(0);
		}
		return null;
	}
	
	public void setPlayerCommand(Command com){
		if(enginec.getEntity(0) instanceof PlayerContract) {
			((PlayerContract) enginec.getEntity(0)).setLastCom(com);
		//	System.out.println("Set last com " + com .toString() + " --" +  enginec.getEntity(0).toString());
		}
	}

	public void step(){
		enginec.step();
		if(enginec.getEntity(0).getCol()==Tools.getOut(enginec.envi()).x 
				&& enginec.getEntity(0).getRow()==Tools.getOut(enginec.envi()).y) {
			this.init();
		}
		//Tools.printEnv(enginec.envi());
	}
	
	public Cell getCellNature(int x, int y) {
		return enginec.envi().getCellNature(x, y);
	}
	
	public Node getPlayerPos() {
		return new Node(enginec.getEntity(0).getCol(), enginec.getEntity(0).getRow());
	}
	

}


