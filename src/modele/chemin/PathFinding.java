package modele.chemin;

import java.util.ArrayList;

import modele.cellule.Cellule;
import modele.plateau.Plateau;

public class PathFinding {
	
	Plateau map;
	Node depart;
//	Node arrive;
	Cellule arrive;
	ArrayList <Node> aVisiter;
	ArrayList <Node> dejaVisiter;
	ArrayList<Cellule> chemin;
	ArrayList<Cellule> path; //chemin donné dans l'ordre
	int[] coordDepart;
	int[] coordArrive;
	int coordArriveX;
	int coordArriveY;
	int currentX;
	int currentY;
	Node current;
	
	public PathFinding(Plateau map, Cellule depart, Cellule arrive) {
		
		if (depart.getCollider().isTrigger() && arrive.getCollider().isTrigger()) {
			this.map = map;
			this.coordDepart = map.getCellulePositionInPlateau(depart);
			this.depart = new Node(null, map.getCellule(coordDepart[0], coordDepart[1]));
			this.coordArrive = map.getCellulePositionInPlateau(arrive);
//			this.arrive = arrive;
			this.coordArriveX = coordArrive[0];
			this.coordArriveY = coordArrive[1];
			this.aVisiter = new ArrayList<>();
			this.chemin  = new ArrayList<>();
			this.dejaVisiter = new ArrayList<>();
			this.path = new ArrayList<Cellule>();
			System.out.println("le constructeur marche!");
			System.out.println("la case depart est marchable : "+depart.getCollider().isTrigger());
			System.out.println("la case arrive est marchable : "+arrive.getCollider().isTrigger());
		}
		else {
			System.out.println("chemin impossible");
			System.out.println(depart.getCollider().isTrigger());
			System.out.println(arrive.getCollider().isTrigger());
		}
		
		
	}
	
	public ArrayList<Cellule> chemin() {
		
		if(coordDepart == coordArrive) {
			return null;
		}
		aVisiter.add(depart);
		Node victoire = null;
		System.out.println("ça marche1");
		do {
				System.out.println();
				System.out.println("aVisiter contient : "+aVisiter);
				System.out.println();
				current = aVisiter.get(0);
				current.setVisite();
				dejaVisiter.add(current);
				aVisiter.remove(0);
				
				
				victoire = checkAroundArrive(current);
				
				if(map.getCellulePositionInPlateau(current.getCord()) != null) {
					currentX = map.getCellulePositionInPlateau(current.getCord())[0];
					currentY = map.getCellulePositionInPlateau(current.getCord())[1];
					
					Node voisinD = new Node(current, map.getCellule(currentX+1, currentY));                 //voisin Droite de Current
					System.out.println("voisinD : " + voisinD);
					System.out.println("voisinD is trigger : " + voisinD.getCord().getCollider().isTrigger());
					System.out.println("voisinD position : " + voisinD.getCord().getPos());
					System.out.println();
					if(voisinD!=null 
							&& voisinD.getCord().getCollider().isTrigger() 
							&& !voisinD.getDejaVisité() && !aVisiterContient(voisinD) 
							&& !dejaVisiterContient(voisinD)){ 
								
							aVisiter.add(voisinD);
					}
					
					Node voisinG = new Node(current, map.getCellule(currentX-1, currentY));                 //voisin Gauche de Current
					System.out.println("voisinG : " + voisinG);
					System.out.println("voisinG is trigger : " + voisinG.getCord().getCollider().isTrigger());
					System.out.println("voisinG position : " + voisinG.getCord().getPos());
					System.out.println();
					if(voisinG!=null 
							&& voisinG.getCord().getCollider().isTrigger() 
							&& !voisinG.getDejaVisité() && !aVisiterContient(voisinG) 
							&& !dejaVisiterContient(voisinG)){ 
								
							aVisiter.add(voisinG);
					}
					
					Node voisinH = new Node(current, map.getCellule(currentX, currentY-1));                 //voisin Haut de Current
					System.out.println("voisinH : " + voisinH);
					System.out.println("voisinH is trigger : " + voisinH.getCord().getCollider().isTrigger());
					System.out.println("voisinH position : " + voisinH.getCord().getPos());
					System.out.println();
					if(voisinH!=null 
							&& voisinH.getCord().getCollider().isTrigger() 
							&& !voisinH.getDejaVisité() && !aVisiterContient(voisinD) 
							&& !dejaVisiterContient(voisinD)){ 
								
							aVisiter.add(voisinD);
					}
					
					Node voisinB = new Node(current, map.getCellule(currentX, currentY+1));                //voisin Bas de Current
					System.out.println("voisinB : " + voisinB);
					System.out.println("voisinB is trigger : " + voisinB.getCord().getCollider().isTrigger());
					System.out.println("voisinB position : " + voisinB.getCord().getPos());
					System.out.println();
					if(voisinD!=null 
							&& voisinB.getCord().getCollider().isTrigger() 
							&& !voisinB.getDejaVisité() && !aVisiterContient(voisinB) 
							&& !dejaVisiterContient(voisinB)){ 
								
							aVisiter.add(voisinB);
					}
					
					
					System.out.println("aVisiter est vide : " + aVisiter.isEmpty() );
					System.out.println();
							
							

				}
			
			
		} while (victoire == null && !aVisiter.isEmpty());
		
		System.out.println("case victoire : " + victoire);
		
	  if(victoire!=null) {
		  while(victoire.getAvant()!=null) {
             chemin.add(victoire.getCord());
              victoire = victoire.getAvant();
          }
      }
	  
	  
	  //remets l'arraylist dans le bon ordre
	  for (int i = 0; i < chemin.size(); i++) {
		  int cell = chemin.size()-i-1;
		  path.add(chemin.get(cell));
	  }
	  
				
		return chemin;
	}
	
	// retourne le node si l'un des voisins est la case d'arrivé
	public Node checkAroundArrive(Node current) {
		if(map.getCellulePositionInPlateau(current.getCord()) != null) {
			int currentX = map.getCellulePositionInPlateau(current.getCord())[0];
			int currentY = map.getCellulePositionInPlateau(current.getCord())[1];
			
			if (currentX+1 == coordArriveX &&  currentY == coordArriveY) {              //droite
				return new Node(current, map.getCellule(currentX+1,currentY));		  
			}
			if (currentX-1 == coordArriveX &&  currentY == coordArriveY) {			  //gauche
				return new Node(current, map.getCellule(currentX-1,currentY));
			}
			if (currentX == coordArriveX &&  currentY-1 == coordArriveY) {			 //haut
				return new Node(current, map.getCellule(currentX,currentY-1));
			}
			if (currentX == coordArriveX &&  currentY+1 == coordArriveY) {			 //bas
				return new Node(current, map.getCellule(currentX,currentY+1));
			}
		}
			
		return null;
	}
	
	public boolean aVisiterContient(Node node) {
		for (int i = 0; i < aVisiter.size(); i++) {
			if(node.getCord().getPos().getX() == aVisiter.get(i).getCord().getPos().getX() && 
			   node.getCord().getPos().getY() == aVisiter.get(i).getCord().getPos().getY()) {
				return true;
			}
			
		}
		return false;
	}
	
	public boolean dejaVisiterContient(Node node) {
		for (int i = 0; i < dejaVisiter.size(); i++) {
			if(node.getCord().getPos().getX() == dejaVisiter.get(i).getCord().getPos().getX() && 
			   node.getCord().getPos().getY() == dejaVisiter.get(i).getCord().getPos().getY()) {
				return true;
			}
			
		}
		return false;
	}
	
	
}











//package modele.chemin;
//
//
//
//public class PathFinding {
//	
//	public PathFinding() {
//		
//		
//	}

//	
//}
//	
///*
//
////    public final static ArrayList<Cellule> chemin(Plateau map, Cellule depart, Cellule arrive ) {
////        boolean[][] visiter = new boolean[map.get().length][map.get()[0].length];
////        if(depart.equals(arrive)) { 
////            return null;
////        }
////        System.out.println("depart : " + depart.getPos().toString());
////        System.out.println("arrive : " + arrive.getPos().toString());
////        visiter[depart.getPos().getX()/16][depart.getPos().getY()/16] = false;
////        ArrayList<Node> last = new ArrayList<>();
//////        ArrayList<Node> all = new ArrayList<>();
////        last.add(genNode(depart, map, false, null, 0, 0, visiter));
////        
////        if(last.get(last.size()-1)==null) {
////        	
////        	return null;
////        }
////        Node victoire; //contiendra le node arrivé
////       
////        do {
////            ArrayList<Node> vide = new ArrayList<Node>();
////            victoire = checkAround(last, vide, visiter, map, arrive);
////            last = vide;
////         
////        } while(victoire == null && !last.isEmpty());
////        
////        System.out.println("qqchose");
////        
////        //récupère les nodes avant de chaque node 
////        ArrayList<Cellule> chemin = new ArrayList<Cellule>();
////        if(victoire!=null) {
////            
////            while(victoire.getAvant()!=null) {
////                chemin.add(victoire.getCord());
////                victoire = victoire.getAvant();
////            }
////            
////        }
////        //remets l'arraylist dans le bon ordre
////        ArrayList<Cellule> path = new ArrayList<Cellule>();
////        for (int i = 0; i < chemin.size(); i++) {
////            int nb = chemin.size()-i-1;
////            path.add(chemin.get(nb));
////        }
////        
////        return path;
//    }
//
////    //check si on est Ã  la case arrive
////    public static Node checkAround(ArrayList<Node> dernier,ArrayList<Node> vide, boolean[][] visite,Plateau map, Cellule arrive) {
////        for (Node n : dernier) {
////            Node t = genNode(n.cord, map, true, n, 1, 0, visite);
////               if (t!=null) {
////                if(t.cord.getPos().getX()/16 == arrive.getPos().getX()/16 && t.cord.getPos().getY()/16== arrive.getPos().getY()/16) {
////                	 System.out.println("arrivée : "+ arrive.getPos().toString() + "]"+ "[coordonneé :"+ t.cord.getPos().toString());
////                    return t;
////                }
////                vide.add(t);
////            }
////            t= genNode(n.cord, map, true, n, -1, 0, visite);
////            if (t!=null) {
////                if(t.cord.getPos().getX()/16 == arrive.getPos().getX()/16 && t.cord.getPos().getY()/16== arrive.getPos().getY()/16) {
////                    System.out.println("arrivée : "+ arrive.getPos().toString() + "]"+ "[coordonneé :"+ t.cord.getPos().toString());
////                    return t;
////                }
////                    
////                vide.add(t);
////            }
////            t= genNode(n.cord, map, true, n,0 , 1, visite);
////            if (t!=null) {
////                if(t.cor//    public final static ArrayList<Cellule> chemin(Plateau map, Cellule depart, Cellule arrive ) {
////boolean[][] visiter = new boolean[map.get().length][map.get()[0].length];
////if(depart.equals(arrive)) { 
////  return null;
////}
////System.out.println("depart : " + depart.getPos().toString());
////System.out.println("arrive : " + arrive.getPos().toString());
////visiter[depart.getPos().getX()/16][depart.getPos().getY()/16] = false;
////ArrayList<Node> last = new ArrayList<>();
//////ArrayList<Node> all = new ArrayList<>();
////last.add(genNode(depart, map, false, null, 0, 0, visiter));
////
////if(last.get(last.size()-1)==null) {
////	
////	return null;
////}
////Node victoire; //contiendra le node arrivé
////
////do {
////  ArrayList<Node> vide = new ArrayList<Node>();
////  victoire = checkAround(last, vide, visiter, map, arrive);
////  last = vide;
////
////} while(victoire == null && !last.isEmpty());
////
////System.out.println("qqchose");
////
//////récupère les nodes avant de chaque node 
////ArrayList<Cellule> chemin = new ArrayList<Cellule>();
////if(victoire!=null) {
////  
////  while(victoire.getAvant()!=null) {
////      chemin.add(victoire.getCord());
////      victoire = victoire.getAvant();
////  }
////  
////}
//////remets l'arraylist dans le bon ordre
////ArrayList<Cellule> path = new ArrayList<Cellule>();
////for (int i = 0; i < chemin.size(); i++) {
////  int nb = chemin.size()-i-1;
////  path.add(chemin.get(nb));
////}
////
////return path;d.getPos().getX()/16 == arrive.getPos().getX()/16 && t.cord.getPos().getY()/16== arrive.getPos().getY()/16) {
////                	 System.out.println("arrivée : "+ arrive.getPos().toString() + "]"+ "[coordonneé :"+ t.cord.getPos().toString());
////                	return t;
////                }
////                    
////                vide.add(t);
////            }
////            t= genNode(n.cord, map, true, n, 0, -1, visite);
////            if (t!=null) {
////                if(t.cord.getPos().getX()/16 == arrive.getPos().getX()/16 && t.cord.getPos().getY()/16== arrive.getPos().getY()/16) {
////                	System.out.println("arrivée : "+ arrive.getPos().toString() + "]"+ "[coordonneé :"+ t.cord.getPos().toString());
////                    return t;
////                }
////                vide.add(t); 
////                
////            }
////            
////        }
////        
////        
////        return null;
////    }
////    
////    
////    /**
////     * test true si l'on doit tester que l'on peut macher sur la case
////     * @param cord
////     * @param map
////     * @param test
////     * @return
////     */
////    private static Node genNode(Cellule cord, Plateau map, boolean test, Node last, int decalx, int decaly, boolean[][] visite) {
////        //System.out.println(map.getPlateau().length);
////        if (!test || (true && cord.getPos().getX()/16>=0 && cord.getPos().getY()/16>=0 && cord.getPos().getX()/16< map.get().length && cord.getPos().getY()/16< map.get()[0].length )) {//collision Ã  mettre Ã  la place du troueeeee!
////
////           // System.out.println("test");
////        	if (cord.getPos().getX()>16 && cord.getPos().getY()>16) {
////        		visite[cord.getPos().getX()/16+decalx][cord.getPos().getY()/16+decaly]=false;
////        		return new Node(last, map.getCellule(cord.getPos().getX()/16+decalx, cord.getPos().getY()/16+decaly));
////        	}
////        	
////        	return null;
////            
////        }
////        return null;
////    }
//	*/