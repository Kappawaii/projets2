package modele.chemin;

import java.util.ArrayList;

import modele.coordonnee.Coordonnee;
import modele.plateau.Plateau;

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
        ArrayList<Node> all = new ArrayList<>();
        last.add(genNode(depart, map, false, null, 0, 0, visiter));
        Node victoire;
        
        do {
            ArrayList<Node> vide = new ArrayList<Node>();
            victoire = checkAround(last, vide, all, visiter, map, arrive);
            last = vide;
            
            
        } while(victoire == null && !last.isEmpty());
        
        ArrayList<Coordonnee> chemin = new ArrayList<>();
        if(last.isEmpty()) {
            Node nodeChemin = all.get(all.size()-1);
            
            chemin.add(nodeChemin.getCord());
            Node parent = nodeChemin;
            while(parent.getCord() != depart) {
                parent = nodeChemin.getAvant();
                chemin.add(parent.getCord());
            }
            
        }
            
        
        return chemin;
    }
    
    //check si on est à la case arrive
    public static Node checkAround(ArrayList<Node> dernier,ArrayList<Node> vide, ArrayList<Node> all, boolean[][] visite,Plateau map, Coordonnee arrive) {
        for (Node n : dernier) {
            Node t = genNode(n.cord, map, true, n, 1, 0, visite);
            if (t!=null) {
                if(t.cord.equals(arrive)) {
                    return t;
                }
                vide.add(t);
                all.add(t);
            }
            t= genNode(n.cord, map, true, n, -1, 0, visite);
            if (t!=null) {
                if(t.cord.equals(arrive))
                    return t;
                vide.add(t);
                all.add(t);
            }
            t= genNode(n.cord, map, true, n,0 , 1, visite);
            if (t!=null) {
                if(t.cord.equals(arrive))
                    return t;
                vide.add(t);
                all.add(t);
            }
            t= genNode(n.cord, map, true, n, 0, -1, visite);
            if (t!=null) {
                if(t.cord.equals(arrive))
                    return t;
                vide.add(t);
                all.add(t);
            }
        }
        
        
        return null;
    }
    
//    public static Node arrive(Coordonnee depart,int decalx, int decaly) {
//        
//    }
    
    /**
     * test true si l'on doit tester que l'on peut macher sur la case
     * @param cord
     * @param map
     * @param test
     * @return
     */
    @SuppressWarnings("unused")
    private static Node genNode(Coordonnee cord, Plateau map, boolean test, Node last, int decalx, int decaly, boolean[][] visite) {
        
        if (!test || (true && cord.getX()>=0 && cord.getY()>=0 && cord.getX()< map.getPlateau().length && cord.getX()< map.getPlateau()[0].length )) {//collision à mettre à la place du troueeeee!
            visite[cord.getX()/16+decalx][cord.getY()/16+decaly]=false;
            return new Node(last, new Coordonnee(cord.getX()+decalx,cord.getY()+decaly));
            
        }
        return null;
    }
}

