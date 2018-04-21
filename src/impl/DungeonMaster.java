package impl;

import java.util.List;

import contracts.CowContract;
import contracts.EditMapContract;
import contracts.EngineContract;
import contracts.EnvironmentContract;
import contracts.MobContract;
import contracts.PlayerContract;
import utils.Cell;
import utils.Dir;
import utils.Node;
import utils.Pathfinder;
import utils.mapGenerator;

public class DungeonMaster {

	public static void main(String[] args) {

		//initialisation d'une partie avec une carte vide de taille 10x10, 1 joueur, 2 monstres et 3 vaches

		int heigth = 20;
		int width = 20;
		//		
		//		EditMapImpl mapimpl = new  EditMapImpl();
		//		EnvironmentImpl env = new EnvironmentImpl();
		//		EngineImpl engine = new EngineImpl();
		//		PlayerImpl player = new PlayerImpl();
		//		
		//		EditMapContract mapc = new EditMapContract(mapimpl);
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
		//		//ajouter les entities
		//		//enginec.init(envc);
		//		playerc.init(envc, 1, 1, Dir.N, 10);
		////		mobsc[0].init(envc, 8, 8, Dir.S);
		////		mobsc[1].init(envc, 5, 5, Dir.S);
		//		cowsc[0].init(envc, 2, 2, Dir.E, 4);
		//		cowsc[1].init(envc, 3, 3, Dir.S, 4);
		//		cowsc[2].init(envc, 4, 4, Dir.W, 4);
		//		
		//		System.out.println("Hello welcome to dungeon master"); 
		//		System.out.println("you're playing on a " + mapc.getHeight()+" x " + mapc.getWidth() + " map with");
		//		System.out.println(mobsc.length+" mobs and "+cowsc.length+" cows.");
		//
		//		
		//		
		//	    Cell[][] map = {
		//	            {Cell.WLL, Cell.OUT, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.IN},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL},
		//	            {Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL}
		//	            };
		//		
		//	    Cell[][] mapbug = {
		//	            {Cell.WLL, Cell.OUT, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL},
		//	            {Cell.WLL, Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.EMP, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.EMP, Cell.IN},
		//	            {Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.EMP, Cell.WLL, Cell.WLL},
		//	            {Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL, Cell.WLL}
		//	            };
		////	    
		////	    System.out.println();
		////
		////       
		////	    	int inx = 0;
		////	    	int iny = 0;
		////	    	int outx = 0;
		////	    	int outy = 0;
		////	    	
		////	    	for(int x=0; x<map[0].length; x++){
		////	    		for(int y=0; y<map.length; y++){
		////	    			if(map[y][x] == Cell.IN){
		////	    				inx = x;
		////	    				iny = y;
		////	    				System.out.println("IN = ("+inx+","+iny+")");
		////	    			}
		////	    			if(map[y][x] == Cell.OUT){
		////	    				outx = x;
		////	    				outy = y;
		////	    				System.out.println("OUT = ("+outx+","+outy+")");
		////	    			}
		////	    		}
		////	    	}
		////	    	
		////	    	System.out.println();
		//	    	
		////	    	int inxb = 0;
		////	    	int inyb = 0;
		////	    	int outxb = 0;
		////	    	int outyb = 0;
		////	    	
		////	    	for(int x=0; x<mapbug[0].length; x++){
		////	    		for(int y=0; y<mapbug.length; y++){
		////	    			if(mapbug[y][x] == Cell.IN){
		////	    				inxb = x;
		////	    				inyb = y;
		////	    				System.out.println("IN = ("+inxb+","+inyb+")");
		////	    			}
		////	    			if(mapbug[y][x] == Cell.OUT){
		////	    				outxb = x;
		////	    				outyb = y;
		////	    				System.out.println("OUT = ("+outxb+","+outyb+")");
		////	    			}
		////	    		}
		////	    	}
		////	    	
		//
		////	    
		////	        Pathfinder pf = new Pathfinder(map,inx,iny,outx,outy);
		////	        List<Node> path = pf.path();
		//	        //Pathfinder pfbug = new Pathfinder(mapbug,inxb,inyb,outxb,outyb);
		//	        //List<Node> pathbug = pfbug.path();
		//	        
		////	        int cpt = 0;
		////	        System.out.println("taille: "+path.size());
		////	        for (Node node : path) {
		////	        	System.out.print("Node "+cpt++);
		////	        	System.out.print(" ["+ node.x);
		////	        	System.out.println(","+node.y+"]");
		////			}
		////	        
		////	        int cptb = 0;
		////	        System.out.println("taille: "+path.size());
		////	        for (Node node : path) {
		////	        	System.out.print("Node "+cptb++);
		////	        	System.out.print(" ["+ node.x);
		////	        	System.out.println(","+node.y+"]");
		////			}


		mapGenerator mgen = new mapGenerator(width,heigth,200,10);

		Cell[][] mapgen = mgen.getMap();
		for(int i = 0 ; i < width ; i++) {
			for(int j = 0 ; j < heigth ; j++) {
				if(mapgen[i][j]==Cell.WLL) {
					System.out.print("XX  ");
				}
				else if(mapgen[i][j]==Cell.IN) {
					System.out.print("IN  ");
				}
				else if(mapgen[i][j]==Cell.OUT) {
					System.out.print("OUT ");
				}
				else {
					System.out.print("    ");
				}
			}
			System.out.println();
		}

		
	int inx = 0;
	    	int iny = 0;
	    	int outx = 0;
	    	int outy = 0;
	    	
	    	for(int x=0; x<mapgen[0].length; x++){
	    		for(int y=0; y<mapgen.length; y++){
	    			if(mapgen[x][y] == Cell.IN){
	    				inx = x;
	    				iny = y;
	    				System.out.println("IN = ("+inx+","+iny+")");
	    			}
	    			if(mapgen[x][y] == Cell.OUT){
	    				outx = x;
	    				outy = y;
	    				System.out.println("OUT = ("+outx+","+outy+")");
	    			}
	    		}
	    	}
	    	
	    	System.out.println();
		
		
		Pathfinder pf = new Pathfinder(mapgen,inx,iny,outx,outy);
		List<Node> path = pf.path();


	}

}
