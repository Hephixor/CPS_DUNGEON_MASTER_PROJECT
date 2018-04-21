package impl;

import java.util.List;
import java.util.Scanner;

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


		int heigth ;
		int width ;
//		Scanner sc = new Scanner(System.in);
//		System.out.print("Heigth : ");
//		heigth = sc.nextInt();
//		System.out.print("Width : ");
//		width = sc.nextInt();
//		sc.close();

		heigth = 10;
		width = 10;

		EditMapImpl mapimpl = new  EditMapImpl();
		EnvironmentImpl env = new EnvironmentImpl();
		EngineImpl engine = new EngineImpl();
		PlayerImpl player = new PlayerImpl();
		env.init(width, heigth);

		EditMapContract mapc = new EditMapContract(mapimpl);
		EnvironmentContract envc = new EnvironmentContract(env);
		EngineContract enginec = new EngineContract(engine);
		PlayerContract playerc = new PlayerContract(player);
		MobContract[] mobsc = new MobContract[2];
		mobsc[0] = new MobContract(new MobImpl());
		mobsc[1] = new MobContract(new MobImpl());
		CowContract[] cowsc = new CowContract[3];
		cowsc[0] = new CowContract(new CowImpl());
		cowsc[1] = new CowContract(new CowImpl());
		cowsc[2] = new CowContract(new CowImpl());

		mapc.init(width, heigth);
		envc.init(width,heigth);
		//ajouter les entities
		
		playerc.init(envc, 1, 1, Dir.N, 10);
		mobsc[0].init(envc, 8, 8, Dir.S);
		mobsc[1].init(envc, 5, 5, Dir.S);
		cowsc[0].init(envc, 2, 2, Dir.E, 4);
		cowsc[1].init(envc, 3, 3, Dir.S, 4);
		cowsc[2].init(envc, 4, 4, Dir.W, 4);

		
		enginec.init(envc);
		System.out.println("Hello welcome to dungeon master"); 
		System.out.println("you're playing on a " + mapc.getHeight()+" x " + mapc.getWidth() + " map with");
		System.out.println(mobsc.length+" mobs and "+cowsc.length+" cows.");





		mapGenerator mgen = new mapGenerator(width,heigth,200,10);

		Cell[][] mapgen = mgen.getMap();


		int inx = 0;
		int iny = 0;
		int outx = 0;
		int outy = 0;

		for(int x=0; x<mapgen[0].length; x++){
			for(int y=0; y<mapgen.length; y++){
				if(mapgen[y][x] == Cell.IN){
					inx = x;
					iny = y;
					System.out.println("IN = ("+inx+","+iny+")");
				}
				if(mapgen[y][x] == Cell.OUT){
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
