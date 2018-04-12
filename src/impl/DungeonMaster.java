package impl;

import contracts.CowContract;
import contracts.EditMapContract;
import contracts.EngineContract;
import contracts.EnvironmentContract;
import contracts.MobContract;
import contracts.PlayerContract;
import utils.Dir;

public class DungeonMaster {

	public static void main(String[] args) {
		
		//initialisation d'une partie avec une carte de taille 10x10, 1 joueur, 2 monstres et 3 vaches
		
		int heigth = 10;
		int width = 10;
		
		EditMapImpl map = new  EditMapImpl();
		EnvironmentImpl env = new EnvironmentImpl();
		EngineImpl engine = new EngineImpl();
		PlayerImpl player = new PlayerImpl();
		
		EditMapContract mapc = new EditMapContract(map);
		EnvironmentContract envc = new EnvironmentContract(env);
		EngineContract enginec = new EngineContract(engine);
		PlayerContract playerc = new PlayerContract(player);
		MobContract[] mobsc = new MobContract[2];
		CowContract[] cowsc = new CowContract[3];
		
		mapc.init(width, heigth);
		envc.init(width,heigth);
		enginec.init(envc);
		playerc.init(envc, 1, 1, Dir.N, 10);
		mobsc[0].init(envc, 8, 8, Dir.S);
		mobsc[1].init(envc, 5, 5, Dir.S);
		cowsc[0].init(envc, 2, 2, Dir.E, 4);
		cowsc[1].init(envc, 3, 3, Dir.S, 4);
		cowsc[2].init(envc, 4, 4, Dir.W, 4);
		
		
	}

}
