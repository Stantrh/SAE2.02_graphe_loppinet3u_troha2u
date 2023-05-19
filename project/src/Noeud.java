import java.util.ArrayList;
import java.util.List;

public class Noeud {

    private String nom;
    private List<Arc> adj;

    public Noeud(String n){
        if(n == null){
            throw new IllegalArgumentException("Le Noeud doit avoir un nom lors de l'initialisation");
        }
        else{
            this.nom = n;
            this.adj = new ArrayList<Arc>();
        }
    }

    /**
     * Redéfinition de la méthode equals. Si deux noeuds ont le même nom alors ils sont égaux
     * @param o Le noeud dont on veut comparer le nom
     * @return true si les noms sont les mêmes, false sinon.
     */
    public boolean equals(Object o){
        return (this.nom.compareTo((Noeud)o.nom)) == 0;
    }

    public void ajouterArc(String destination, double cout){
        this.adj.add(new Arc(destination,cout));
    }
}
