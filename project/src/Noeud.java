import java.util.ArrayList;
import java.util.List;

/**
 * classe Noeud représentant les nœuds du graphe. Un objet Noeud est décrit par deux attributs privés
 */
public class Noeud {

    /**
     * Attribut correspondant au nom du nœud qui permet de l’identifier ;
     */
    private String nom;

    /**
     * liste des arcs reliant ce nœud à ses nœuds adjacents.
     */
    private List<Arc> adj;

    /**
     * construit un Noeud avec un nom n
     * @param n le nom du noeud
     */
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
     * Compare les deux noms d'un noeud, s'ils sont égaux alors le noeud est le même
     * @param o Le noeud dont on veut comparer le nom
     * @return true si les noms sont les mêmes, false sinon.
     */
    @Override
    public boolean equals(Object o){
        Noeud n = (Noeud)o;
        return (this.nom.compareTo(n.nom)) == 0;
    }

    /**
     * Ajoute un arc allant vers le nœud destination avec un coût de cout à la liste adj.
     * @param destination noeud auquel on va ajouter un arc
     * @param cout coût de l'arc à ajouter
     */
    public void ajouterArc(String destination, double cout){
        this.adj.add(new Arc(destination,cout));
    }


    /**
    Renvoie la liste des arcs d'un noeud
     */
    public List<Arc> getArcs(){
        return this.adj;
    }

}
