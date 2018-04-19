package tests;

import java.util.Vector;


public class Dijkstra {
	
	 
	// creation du graphe
	public static final int INFINITE = 1000;//Integer.MAX_VALUE;
	public final static int ALPHA_NOTDEF = -999 ;// on met final psk c'est une constante
	private int x0;
	private int [] S;//ensemble de sommets dont les distances les plus courtes à la source sont connues au départ seulement Source
	private int [] R;//ensemble des prédécesseur des sommets de 0 à N-1;
	private Graphe g0;
	private int [] D;//tableau des valeurs du meilleur raccourci pour se rendre à chaque sommet
	// rajout
	private boolean [] noeudsMarqués;
	private static int dimensionDeLaMatrice;//je rajoute ça pour simplifier le code.
	
	public Dijkstra( int x, Graphe g){	
		x0 = x;
		g0 = g;
		dimensionDeLaMatrice = g0.nbSommet();
		S = new int [dimensionDeLaMatrice];//sommets atteints
		D = new int [dimensionDeLaMatrice];//distances
		noeudsMarqués = new boolean[dimensionDeLaMatrice];
		R = new int [dimensionDeLaMatrice];
		calculePlusCourtChemin();
	}
	
	private void calculePlusCourtChemin(){ 
	    int n =0;
		for (int a = 0; a < dimensionDeLaMatrice; a++){
			noeudsMarqués[a] =false;
			S[a]=-1; //en supposant qu'on numérote les sommets de 0 ou de 1 à n.
			R[a]=-1; // pour les noeuds qui n'ont pas de prédecésseur
		}
		
		S[n]=x0;
		D[x0]=0;
		R[x0]=x0;
		initDistMin();
		for (int i = 0; i< dimensionDeLaMatrice ;i++){	
			if (!contains(S,i)){// à revoir
				int t = choixSommet();
				noeudsMarqués[t] = true;
				n++;
				S[n]=t;
				majDistMin(t);
			} //end if
		}//end for
//		for (int i=0; i<dimensionDeLaMatrice;i++){
//			System.out.print(" S[i]"+S[i]);
//		}
//		for (int i=0; i<dimensionDeLaMatrice;i++){
//			System.out.print(" R["+i+"]"+R[i]);
//		}
//		System.out.println();
	}
	
	//implémentation de initDistMin
	private void initDistMin(){
		noeudsMarqués[x0]=true;
		for (int i=0; i<dimensionDeLaMatrice;i++){
			if(g0.existeArc(x0,i)){
				D[i] = g0.getU()[x0][i];
				R[i] = x0;
			}
			else {
				if (x0 != i)
				D[i] =- g0.ALPHA_NOTDEF+1;
			}
		}
	}
	
	private void majDistMin(int n){
		for (int i = 0; i < dimensionDeLaMatrice; i++){			
				if (!contains(S,i)){
					//D[i] = min(D[i], D[n] + distanceDsGraphe(n,i));
					if (D[n] + distanceDsGraphe(n,i)<D[i]){
						D[i]=D[n] + distanceDsGraphe(n,i);
						R[i]=n;
					}
				}
		}
	}
	private int distanceDsGraphe (int t, int s){
		if (g0.existeArc(t, s)){		
			return g0.getU()[t][s];
		}
		else {
			return INFINITE;
		}
	}

	public int choixSommet(){
		int valeurMin = INFINITE;
		int min = x0;
		for (int i=0; i<dimensionDeLaMatrice ;i++){
			if (!noeudsMarqués[i])
				if (D[i]<=valeurMin){
					min = i;
					valeurMin = D[i];
				}
		}
		return min;
	}
	
	
    //fonction à définir :S.contains(i)
	private boolean contains(int[] S, int i){
		return noeudsMarqués[i];
	}
	
	public int longueurChemin (int i){
		return D[i];
	}
	//fonction à définir min
	private int min (int i, int j){
		if (i<=j)
			return i;
		else return j;
	}
	public void afficheChemin(int i){
		int source = x0;
		int antécédent = i;
		Vector <Integer> lesNoeudsIntermediaires = new Vector<Integer>();;
		System.out.println("Chemin de "+x0+" à "+ i+":");
		while (antécédent!=source){
			lesNoeudsIntermediaires.add(antécédent);
			antécédent = R[antécédent];
			
		}
		lesNoeudsIntermediaires.add(source);
		for (int j= lesNoeudsIntermediaires.size()-1; j>0;j--){
			System.out.print("-->"+lesNoeudsIntermediaires.get(j));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int N = Graphe.ALPHA_NOTDEF ;
		int [][] matDuree={
		                   {N,4,N,11,N,N,3,N,N,10,N},
		                   {N,N,N,N,N,N,N,N,N,N,N},
			               {N,N,N,N,N,N,N,N,N,N,N},
				           {N,N,N,N,N,N,N,N,N,2,N},
					       {N,N,N,2,N,N,N,2,5,N,N},
					  	   {N,N,N,N,N,N,N,N,N,N,N},
						   {N,N,N,N,5,N,N,N,N,N,5},
						   {N,N,N,N,2,N,N,N,N,N,6},
						   {N,N,N,N,N,6,N,N,N,N,N},		
						   {N,N,4,N,N,N,N,N,5,N,N},
						   {N,N,N,N,N,N,N,7,N,N,N},
		                  };
		//création du graphe			
		Graphe g0 = new Graphe(matDuree);
		//LA SUITE		
		
		
		// creation d'une instance de l'algorithme avec le graphe g0
		Dijkstra beaulieuAutresStations= new Dijkstra(0,g0);
		
		
		
		// Calcul du plus court chemin avec l'algorithme de Dijkstra 
		beaulieuAutresStations.calculePlusCourtChemin();
		

		
		// Affichage du temps de trajet
		int duree = beaulieuAutresStations.longueurChemin(3);
		System.out.println("Le temps mini pour aller de beaulieu a Clemenceau est :"+duree);
		int duree2 = beaulieuAutresStations.longueurChemin(8);
		System.out.println("Le temps mini pour aller de beaulieu au Stade est :"+duree2);
		//Faire d'autres tests
		int dureeVillejean = beaulieuAutresStations.longueurChemin(10);
		System.out.println("Le temps mini pour aller de beaulieu à VilleJean est :"+dureeVillejean);
		
		// Pour afficher le chemin le plus rapide pour aller de beaulieu a Clemenceau
		beaulieuAutresStations.afficheChemin(3); // pour ça il faut un tableau de prédécesseur, il nous faudra un tableau de routage
		//il nous faudrait créer un tableau R des prédecesseurs.
		
		
		//Creation d'une nouvelle instance de l'algorithme avec un départ de Republique
		Dijkstra republiqueAutresStations= new Dijkstra(7,g0);
		republiqueAutresStations.calculePlusCourtChemin();
		int duree3 = republiqueAutresStations.longueurChemin(3);
		System.out.println("Le temps mini pour aller de republique a Clemenceau est :"+duree3);
	}

}