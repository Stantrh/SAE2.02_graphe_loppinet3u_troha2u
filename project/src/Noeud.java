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

    /*
    Redéfinition de la méthode equals. Si deux noeuds ont le même nom
    Alors ils sont égaux
     */
    public boolean equals(Object o){
        return (this.nom.compareTo(o.nom)) == 0;
    }
}
