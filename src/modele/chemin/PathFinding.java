package modele.chemin;

import java.util.ArrayList;

import modele.coordonnee.Coordonnee;
import modele.plateau.Plateau;
import java.util.TreeSet;

public class PathFinding {

    
    public static ArrayList<Coordonnee> chemin(Plateau map, Coordonnee depart, Coordonnee arrive ) {
        boolean[][] visiter = new boolean[map.getPlateau().length][map.getPlateau()[0].length];
        if(depart.equals(arrive)) { //overid l'equal
            return null;
        }
        System.out.println(depart.getX());
        System.out.println(depart.getY());
        visiter[depart.getX()/16][depart.getY()/16] = false;
        ArrayList<Node> last = new ArrayList<>();
<<<<<<< e826d886b60c5d550b55cefe3cfc211f6f09918e
//        ArrayList<Node> all = new ArrayList<>();
=======
>>>>>>> Animation finie
        last.add(genNode(depart, map, false, null, 0, 0, visiter));
        Node victoire;
        
        do {
            ArrayList<Node> vide = new ArrayList<Node>();
            victoire = checkAround(last, vide, visiter, map, arrive);
            last = vide;
            
            
        } while(victoire == null && !last.isEmpty());
        
        ArrayList<Coordonnee> chemin = new ArrayList<>();
        if(victoire!=null) {
        	
        	while(victoire.getAvant()!=null) {
        		chemin.add(victoire.getCord());
        		victoire = victoire.getAvant();
        	}
            
        }
        
        return chemin;
    }
    
    //check si on est Ã  la case arrive
    public static Node checkAround(ArrayList<Node> dernier,ArrayList<Node> vide, boolean[][] visite,Plateau map, Coordonnee arrive) {
        for (Node n : dernier) {
            Node t = genNode(n.cord, map, true, n, 1, 0, visite);
            if (t!=null) {
                if(t.cord.equals(arrive)) {
                    return t;
                }
                vide.add(t);
            }
            t= genNode(n.cord, map, true, n, -1, 0, visite);
            if (t!=null) {
                if(t.cord.equals(arrive))
                    return t;
                vide.add(t);
            }
            t= genNode(n.cord, map, true, n,0 , 1, visite);
            if (t!=null) {
                if(t.cord.equals(arrive))
                    return t;
                vide.add(t);
            }
            t= genNode(n.cord, map, true, n, 0, -1, visite);
            if (t!=null) {
                if(t.cord.equals(arrive))
                    return t;
                vide.add(t);
            }
        }
        
        
        return null;
    }
    
    
    /**
     * test true si l'on doit tester que l'on peut macher sur la case
     * @param cord
     * @param map
     * @param test
     * @return
     */
    private static Node genNode(Coordonnee cord, Plateau map, boolean test, Node last, int decalx, int decaly, boolean[][] visite) {
        
        if (!test || (true && cord.getX()>=0 && cord.getY()>=0 && cord.getX()< map.getPlateau().length && cord.getX()< map.getPlateau()[0].length )) {//collision Ã  mettre Ã  la place du troueeeee!
            visite[cord.getX()/16+decalx][cord.getY()/16+decaly]=false;
            return new Node(last, new Coordonnee(cord.getX()+decalx,cord.getY()+decaly));
            
        }
        return null;
    }
}

