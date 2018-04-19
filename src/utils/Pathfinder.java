package utils;

import java.util.ArrayList;
import java.util.List;

public class Pathfinder{
  Cell[][] map ;
  List<Node> path;
  
  	public Pathfinder(){
  		path = new ArrayList<Node>();
  	}
  
  
    public boolean pathExists(Cell[][] map, int xi, int yi, int xo, int yo) {
    	this.map = map;
    	List<Node> queue = new ArrayList<Node>();
        boolean pathExists = false;
        int cpt = 0;
        Node entr = new Node(xi,yi);
        
    	queue.add(entr);
        
//        System.out.println("queue vide " + queue.isEmpty());
        while(!queue.isEmpty()) {
        	
        	System.out.print("Node "+cpt++);
        	System.out.print(" ["+ queue.get(0).x);
        	System.out.println(","+queue.get(0).y+"]");
//        	
            Node current = queue.remove(0);
            if(current!=null){
            	path.add(current);
            }
//          System.out.print("Current "+cpt++);
//        	System.out.print(" ["+ current.x);
//        	System.out.println(","+current.y+"]");
            
            if(current.x == xo && current.y == yo) {
                pathExists = true;
                break;
            }
            
            map[current.x][current.y] = Cell.WLL; // mark as visited
            
            List<Node> neighbors = getNeighbors(map, current);
            queue.addAll(neighbors);
            for (Node node : neighbors) {
				map[node.x][node.y]=Cell.WLL;
			}
          
           // printMap();
            
            
        }
        
        return pathExists;
    }
    
   public static List<Node> getNeighbors(Cell[][] map, Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        
        if(isValidPoint(map, node.x - 1, node.y)) {
            neighbors.add(new Node(node.x - 1, node.y));
        }
        
        if(isValidPoint(map, node.x + 1, node.y)) {
            neighbors.add(new Node(node.x + 1, node.y));
        }
        
        if(isValidPoint(map, node.x, node.y - 1)) {
            neighbors.add(new Node(node.x, node.y - 1));
        }
        
        if(isValidPoint(map, node.x, node.y + 1)) {
            neighbors.add(new Node(node.x, node.y + 1));
        }
        
        return neighbors;
    }
    
    public static boolean isValidPoint(Cell[][] map, int x, int y) {
    	boolean valid = !(x < 0 || x >= map.length || y < 0 || y >= map.length) && (map[x][y] != Cell.WLL);
        if(valid){
      //  	System.out.println(" type : " + map[x][y].toString());
        	return true;
        }
        else {
       // System.out.println(" invalid");
        return false;
        }
    }
    
    private void printMap() {
    	for(int i = 0 ; i < map.length; i++) {
        	for(int j = 0 ; j < map.length; j++) {
        		System.out.print(map[i][j].toString() + " ");
        	}
        	System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    
    public List<Node> getPath(){
    	return path;
    }
}


