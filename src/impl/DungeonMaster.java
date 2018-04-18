package impl;

import java.util.Scanner;

import contracts.CowContract;
import contracts.EditMapContract;
import contracts.EngineContract;
import contracts.EnvironmentContract;
import contracts.MobContract;
import contracts.PlayerContract;
import services.EnvironmentService;
import services.MobService;
import utils.Cell;
import utils.Dir;
import utils.Pathfinder;

public class DungeonMaster {

	public static void main(String[] args) {
		
		//initialisation d'une partie avec une carte vide de taille 10x10, 1 joueur, 2 monstres et 3 vaches
		
//		int heigth = 10;
//		int width = 10;
//		
//		EditMapImpl map = new  EditMapImpl();
//		EnvironmentImpl env = new EnvironmentImpl();
//		EngineImpl engine = new EngineImpl();
//		PlayerImpl player = new PlayerImpl();
//		
//		EditMapContract mapc = new EditMapContract(map);
//		EnvironmentContract envc = new EnvironmentContract(env);
//		EngineContract enginec = new EngineContract(engine);
//		PlayerContract playerc = new PlayerContract(player);
//		MobContract[] mobsc = new MobContract[2];
//		mobsc[0] = new MobContract(new MobImpl());
//		mobsc[1] = new MobContract(new MobImpl());
//		CowContract[] cowsc = new CowContract[3];
//		cowsc[0] = new CowContract(new CowImpl());
//		cowsc[1] = new CowContract(new CowImpl());
//		cowsc[2] = new CowContract(new CowImpl());
//		
//		mapc.init(width, heigth);
//		envc.init(width,heigth);
//		enginec.init(envc);
//		playerc.init(envc, 1, 1, Dir.N, 10);
//		mobsc[0].init(envc, 8, 8, Dir.S);
//		mobsc[1].init(envc, 5, 5, Dir.S);
//		cowsc[0].init(envc, 2, 2, Dir.E, 4);
//		cowsc[1].init(envc, 3, 3, Dir.S, 4);
//		cowsc[2].init(envc, 4, 4, Dir.W, 4);
//		
//		System.out.println("Hello welcome to dungeon master"); 
//		System.out.println("you're playing on a " + mapc.getHeight()+" x " + mapc.getWidth() + " map with");
//		System.out.println(mobsc.length+" mobs and "+cowsc.length+" cows.");

		
		Pathfinder pf = new Pathfinder();
		
	    Cell[][] map = {
	            {Cell.WLL, Cell.OUT, Cell.WLL, Cell.WLL, Cell.WLL},
	            {Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL},
	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.WLL},
	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.IN},
	            {Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL}
	        };
		
	    Cell[][] mapbug = {
	            {Cell.WLL, Cell.OUT, Cell.WLL, Cell.WLL, Cell.WLL},
	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.WLL},
	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.WLL},
	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.IN},
	            {Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL}
	        };
	    

	        boolean p = pf.pathExists(map, 3 ,4 ,0 ,1);
	        System.out.println("Chemin dans map");
	        System.out.println(p ? "YES" : "NO");
	        
	        boolean pb = pf.pathExists(mapbug,3 ,4 ,0 ,1);
	        System.out.println("Chemin dans mapbug");
	        System.out.println(pb ? "YES" : "NO");


}
	
}
