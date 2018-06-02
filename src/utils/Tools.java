package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import services.EnvironmentService;
import services.MapService;
import contracts.CowContract;
import contracts.EntityContract;
import contracts.PlayerContract;

public class Tools {

	public static void printEnv(EnvironmentService env){

		for(int y = env.getHeight()-1 ; y >= 0 ; y-- ){
			for(int x = 0 ; x < env.getWidth() ; x++){
				switch(env.getCellNature(x,y)){
				
				case WLL:
					System.out.print("##.");
					break;
					
				case IN:
					System.out.print("IN.");
					break;
					
				case OUT:
					System.out.print("OUT");
					break;
				
				case DNC:
					System.out.print("--.");
					break;
					
				case DWC:
					System.out.print(" |.");
					break;
					
				case EMP:
					if(env.getCellContent(x,y) == null) {
						System.out.print("  .");

					}
					else if(env.getCellContent(x,y) instanceof CowContract) {
						System.out.print("VV.");

					}
					else if(env.getCellContent(x,y) instanceof PlayerContract) {
						System.out.print("PP.");

					}
					else if(env.getCellContent(x,y) instanceof EntityContract) {
						System.out.print("EE.");

					}
					else{
						//Impossible
					}
					break;
					
				default:
					break;
					
				}
			}
			System.out.println();
		}
	}

	public static void printMap(MapService map){

		for(int y = map.getHeight()-1 ; y >= 0 ; y-- ){
			for(int x = 0 ; x < map.getWidth() ; x++){
				switch(map.getCellNature(x,y)){
				
				case WLL:
					System.out.print("##.");
					break;
					
				case IN:
					System.out.print("IN.");
					break;
					
				case OUT:
					System.out.print("OUT");
					break;
					
				case EMP:
					System.out.print("  .");	
					break;
					
				case DNC:
					System.out.print("[n]");
					break;
					
				case DWC:
					System.out.print("[w]");
					break;
					
				case DNO:
					System.out.print("]n[");
					break;
					
				case DWO:
					System.out.print("]w[");
					break;
					
				
				default:
					System.out.print("ERR");
					break;
					
				}
			}
			System.out.println();
		}
	}

	public static void printMap(Cell[][] map){

		for(int y = map.length-1 ; y >=0 ; y++ ){
			for(int x = 0 ; x < map[0].length ; x++){
				switch(map[y][x]){
				
				case WLL:
					System.out.print("##.");
					break;
					
				case IN:
					System.out.print("IN.");
					break;
					
				case OUT:
					System.out.print("OUT");
					break;
					
				case EMP:
					System.out.print("  .");	
					break;
					
				case DNC:
					System.out.print("[n]");
					break;
					
				case DWC:
					System.out.print("[w]");
					break;
					
				case DNO:
					System.out.print("]n[");
					break;
					
				case DWO:
					System.out.print("]w[");
					break;
					
				default:
					System.out.println("ERREUR AFFICHAGE ["+map[y][x]+"] NON GERE :CAS DEFAUT");
					break;
				}
			}
			System.out.println();
		}
	}
	
	public static void printPathMap(int[][] map) {
    	for(int i = map.length-1 ; i >=0; i++) {
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
	
	public static Node getIn(MapService map){
		Node in = null;
		for(int y = 0 ; y < map.getHeight(); y++ ){
			for(int x = 0 ; x < map.getWidth() ; x++){
				if(map.getCellNature(x,y) == Cell.IN){
					in = new Node(x,y);
				}
			}
		}
		return in;
	}

	public static Node getOut(MapService map){
		Node out = null;
		for(int y = 0 ; y < map.getHeight(); y++ ){
			for(int x = 0 ; x < map.getWidth() ; x++){
				if(map.getCellNature(x,y) == Cell.OUT){
					out = new Node(x,y);
				}
			}
		}
		return out;
	}
	

	
	public static ArrayList<Node> getEmp(EnvironmentService env){
		ArrayList<Node> emp = new ArrayList<Node>();
		for(int y = env.getHeight()-1 ; y >= 0; y-- ){
			for(int x = 0 ; x < env.getWidth() ; x++){
				if(env.getCellNature(x,y) == Cell.EMP){
					emp.add(new Node(x,y));
				}
			}
		}
		return emp;
	}
	
	public static ArrayList<Node> getEmp(Cell[][] map){
		ArrayList<Node> emp = new ArrayList<Node>();
		for(int y = map.length-1 ; y >=0 ; y--){
			for(int x = 0 ; x < map[0].length; x++){
				if(map[y][x] == Cell.EMP){
					emp.add(new Node(x,y));
				}
			}
		}
		return emp;
	}
	
	public static <T> T randomElement(T[] elements) {
		Random numberGenerator = new Random();
		return elements[numberGenerator.nextInt(elements.length)];
	}
	
	public static <T> T randomElement(List<T> elements) {
		Random numberGenerator = new Random();
		return elements.get(numberGenerator.nextInt(elements.size()));
	}
	
}
