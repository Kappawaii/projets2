package modele.chemin;

import java.util.ArrayList;

import modele.cellule.Cellule;
import modele.coordonnee.Coordonnee;
import modele.plateau.Plateau;
import java.util.TreeSet;

public class PathFinding {


    public final static ArrayList<Cellule> chemin(Plateau map, Cellule depart, Cellule arrive ) {
        boolean[][] visiter = new boolean[map.getPlateau().length][map.getPlateau()[0].length];
        if(depart.equals(arrive)) { //overid l'equal
            return null;
        }
        System.out.println("depart : " + depart.getPos().toString());
        System.out.println("arrive : " + arrive.getPos().toString());
        visiter[depart.getPos().getX()/16][depart.getPos().getY()/16] = false;
        ArrayList<Node> last = new ArrayList<>();
//        ArrayList<Node> all = new ArrayList<>();
        last.add(genNode(depart, map, false, null, 0, 0, visiter));
        Node victoire;
        
        do {
            ArrayList<Node> vide = new ArrayList<Node>();
            victoire = checkAround(last, vide, visiter, map, arrive);
            last = vide;
            
            
        } while(victoire == null && !last.isEmpty());
        
        
        ArrayList<Cellule> chemin = new ArrayList<Cellule>();
        if(victoire!=null) {
            
            while(victoire.getAvant()!=null) {
                chemin.add(victoire.getCord());
                victoire = victoire.getAvant();
            }
            
        }
        ArrayList<Cellule> path = new ArrayList<Cellule>();
        for (int i = 0; i < chemin.size(); i++) {
            int nb = chemin.size()-i-1;
            path.add(chemin.get(nb));
        }
        
        return path;
    }
    
    //check si on est Ã  la case arrive
    public static Node checkAround(ArrayList<Node> dernier,ArrayList<Node> vide, boolean[][] visite,Plateau map, Cellule arrive) {
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
    @SuppressWarnings("unused")
    private static Node genNode(Cellule cord, Plateau map, boolean test, Node last, int decalx, int decaly, boolean[][] visite) {
        //System.out.println(map.getPlateau().length);
        if (!test || (true && cord.getPos().getX()/16>=0 && cord.getPos().getY()/16>=0 && cord.getPos().getX()/16< map.getPlateau().length && cord.getPos().getY()/16< map.getPlateau()[0].length )) {//collision Ã  mettre Ã  la place du troueeeee!

           // System.out.println("test");
            visite[cord.getPos().getX()/16+decalx][cord.getPos().getY()/16+decaly]=false;
            return new Node(last, map.getCellule(cord.getPos().getX()/16+decalx, cord.getPos().getY()/16+decaly));
            
        }
        return null;
    }
}