package utils;
import java.util.ArrayList;
import java.util.List;

import services.MapService;

public class Pathfinder {
	
	/*attributs internes au fonctionnement du pathfinder*/
	
	private MapService map;
	//etat cell, 0 : invisitable, 1:vide, 2:en cours, 3:deja visite, 4:chemin final
	private int etat[][];
	private Node in;
	private Node out;
	
	/*attributs internes et externes (getters dessus) du pathfinder*/
	private boolean fini;
	private List<Node> chemin;
	
	public Pathfinder(MapService map, int inx, int iny, int outx, int outy) {
		this.fini = false;
		this.chemin = new ArrayList<Node>();
		this.in = new Node(inx,iny);
		this.out = new Node(outx,outy);
		
		this.map = map; 
		this.etat = new int[map.getHeight()][map.getWidth()];
		
		this.path();
	}
	
	
	private List<Node> path(){
		/*on initialise les cases visitables ou non par 0 ou 1*/
		for(int y=0; y<map.getHeight(); y++){
			for(int x=0; x<map.getWidth(); x++){
				if(map.getCellNature(x, y)==Cell.WLL)
					etat[y][x] = 0;
				else
					etat[y][x] = 1;
			}
		}
		
		/*on initialise le chemin IN->OUT a vide*/
		chemin = new ArrayList<Node>();
		
		
		/*on lance l'algo récursif sur la case depart*/
		rec(in.x, in.y);
		
		//System.out.println("\n======= Path in map =======");
		/*on affiche le chemin*/
		//Tools.printPathMap(etat);
		
		/*on retourne le chemin trouve*/
		return chemin;
	}
	
	
	private boolean rec(int x, int y){
		//System.out.println("\nrec(("+x+","+y+"))...");
		
		/*noeud "invisitable"*/
		if(x>=map.getWidth() || y>=map.getHeight() || x<0 || y<0 || etat[y][x] == 0 ){
		//	System.out.println("case invisitable");
			return false;
		}
		
		/*noeud "deja visite"*/
		if(etat[y][x] == 3 ){
		//	System.out.println("case deja visitee");
			return false;
		}
		
		/*on marque le noeud "en cours" pour faire joli dans l'affichage*/
		etat[y][x] = 2;
		//printMap(etat);
		
		/*on marque le noeud comme "deja visite"*/
		etat[y][x] = 3;
		
		/*si le noeud est la SORTIE*/
		if(x==out.x && y==out.y) {
			//System.out.println("Found path !");
			chemin.add(new Node(x,y));
			fini = true;
			/*on marque le noeud comme "chemin trouve"*/
			etat[y][x] = 4;
			return true;
		}
		
		/*sinon*/
		if( fini==false && rec(x+1,y)==true ) {
			chemin.add(new Node(x,y));
			/*on marque le noeud comme "chemin trouve"*/
			etat[y][x] = 4;
			return true;
		}
		if( fini==false && rec(x,y+1)==true ) {
			chemin.add(new Node(x,y));
			/*on marque le noeud comme "chemin trouve"*/
			etat[y][x] = 4;
			return true;
		}
		if( fini==false && rec(x-1,y)==true ) {
			chemin.add(new Node(x,y));
			/*on marque le noeud comme "chemin trouve"*/
			etat[y][x] = 4;
			return true;
		}
		if( fini==false && rec(x,y-1)==true ) {
			chemin.add(new Node(x,y));
			/*on marque le noeud comme "chemin trouve"*/
			etat[y][x] = 4;
			return true;
		}
		
		//System.out.println("IMPASSE");
		
		return false;
	}
	
	public List<Node> getPath(){
		return chemin;
	}
	
	public boolean hasPath() {
		return fini;
	}
}
