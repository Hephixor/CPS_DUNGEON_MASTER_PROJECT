package tests;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.Cell;
import utils.Node;

public class Vinc2 {
	
	private Cell map[][];
	/*au debut une map de boolean, ce sera finalement une map d'etats :
	 * 0 : invisitable, 1:vide, 2:en cours, 3:deja visite, 4:chemin final
	 */
	private int etat[][];
	int largeur;
	int hauteur;
	private boolean fini;
	private List<Node> chemin;
	private Node in;
	private Node out;
	
	public Vinc2(Cell[][] map, Node in, Node out) {
		this.fini = false;
		this.chemin = new ArrayList<Node>();
		this.in = in;
		this.out = out;
		
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
		
		/*on initialise le chemin IN->OUT a vide*/
		chemin = new ArrayList<Node>();
		
		System.out.println("\n========== DEBUT REC ==========");
		/*on lance l'algo récursif sur la case depart*/
		rec(in.x, in.y);
		
		/*on affiche le chemin*/
		printMap(etat);
		/*on retourne le chemin trouve*/
		return chemin;
	}
	
	
	public boolean rec(int x, int y){
		System.out.println("\nrec(("+x+","+y+"))...");
		
		/*noeud "invisitable"*/
		if(x>=largeur || y>=hauteur || x<0 || y<0 || etat[y][x] == 0 ){
			System.out.println("case invisitable");
			return false;
		}
		
		/*noeud "deja visite"*/
		if(etat[y][x] == 3 ){
			System.out.println("case deja visitee");
			return false;
		}
		
		/*on marque le noeud "en cours" pour faire joli dans l'affichage*/
		etat[y][x] = 2;
		printMap(etat);
		
		/*on marque le noeud comme "deja visite"*/
		etat[y][x] = 3;
		
		/*si le noeud est la SORTIE*/
		if(x==out.x && y==out.y) {
			System.out.println("SORTIE TROUVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
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
		
		System.out.println("IMPASSE");
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
}
