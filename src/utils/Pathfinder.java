package utils;
import java.util.ArrayList;
import java.util.List;

import utils.Cell;
import utils.Node;

public class Pathfinder {
	
	private Cell map[][];
	/*au debut une map de boolean, ce sera finalement une map d'etats :
	 * 0 : invisitable, 1:vide, 2:en cours, 3:deja visite, 4:chemin final
	 */
	private int etat[][];
	int largeur;
	int hauteur;
	private boolean fini;
	private List<Node> chemin;
	private boolean hasPath;
	private Node in;
	private Node out;
	
	public Pathfinder(Cell[][] map, int inx, int iny, int outx, int outy) {
		this.fini = false;
		this.chemin = new ArrayList<Node>();
		this.in = new Node(inx,iny);
		this.out = new Node(outx,outy);
		
		this.map = map;
		/*notation map[y][x] et visitable[y][x]*/
		this.largeur = map[0].length;
		this.hauteur = map.length;
		this.etat = new int[hauteur][largeur];
		
	}
	
	
	public List<Node> path(){
		/*on initialise les cases visitables ou non par 0 ou 1*/
		for(int y=0; y<hauteur; y++){
			for(int x=0; x<largeur; x++){
				if(map[y][x]==Cell.WLL)
					etat[y][x] = 0;
				else
					etat[y][x] = 1;
			}
		}
		
		System.out.println("\n======= Pathfinding in map =======");
		for(int i = 0 ; i < map.length; i++) {
        	for(int j = 0 ; j < map[0].length; j++) {
        		switch(map[i][j]){
        		case WLL:
            		System.out.print("##.");
            		break;
        		case EMP:
            		System.out.print("  .");
            		break;
        		case IN:
            		System.out.print("IN.");
            		break;
        		case OUT:
            		System.out.print("OU.");
            		break;
				default:
					break;
            	
        		}
        	}
        	System.out.println();
        }
        System.out.println();
		/*on initialise le chemin IN->OUT a vide*/
		chemin = new ArrayList<Node>();
		
		
		/*on lance l'algo récursif sur la case depart*/
		rec(in.x, in.y);
		
		//System.out.println("\n======= Found path in map =======");
		/*on affiche le chemin*/
		printMap(etat);
		/*on retourne le chemin trouve*/
		return chemin;
	}
	
	
	public boolean rec(int x, int y){
		//System.out.println("\nrec(("+x+","+y+"))...");
		
		/*noeud "invisitable"*/
		if(x>=largeur || y>=hauteur || x<0 || y<0 || etat[y][x] == 0 ){
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
			System.out.println("Found path :");
			chemin.add(new Node(x,y));
			fini = true;
			hasPath=true;
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
	
	private void printMap(int[][] map) {
    	for(int i = 0 ; i < map.length; i++) {
        	for(int j = 0 ; j < map[0].length; j++) {
        		switch(map[i][j]){
        		case 0:
            		System.out.print("##.");
            		break;
        		case 1:
            		System.out.print("  .");
            		break;
        		case 2:
            		System.out.print("OO.");
            		break;
        		case 3:
            		System.out.print("XX.");
            		break;
        		case 4:
            		System.out.print("OK.");
            		break;
            	
        		}
        	}
        	System.out.println();
        }
        System.out.println();
    }
	
	public int[][] getPath(){
		return etat;
	}
	
	public boolean hasPath() {
		return hasPath;
	}
}
