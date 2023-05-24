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
     * @param o Le nom (en String) du noeud dont on veut comparer le nom
     * @return true si les noms sont les mêmes, false sinon.
     */
    @Override
    public boolean equals(Object o){
        Noeud n = (Noeud)o;
        return (this.nom.compareTo(n.nom)) == 0;
    }

    /**
     * Ajoute un arc allant vers le nœud destination avec un coût de cout à la liste adj.
     * Si un noeud à déjà un arc avec une destination X, il n'est plus possible d'ajouter un autre Arc avec cette même destination X
     * @param destination noeud auquel on va ajouter un arc
     * @param cout coût de l'arc à ajouter
     */
    public void ajouterArc(String destination, double cout){
        // Boolean indiquant si un arc a déjà pour destination la même que celui que l'on veut rajouter
        boolean dejaPresent = false;
        // On parcourt la liste d'arcs pour vérifier si cet arc est déjà existant.
        for(Arc a : this.adj){
            // Si c'est le cas, on définit le boolean à true
            if(a.getDest().equals(destination))
                dejaPresent = true;
        }

        // Seulement si l'arc n'est pas déjà dans la liste d'arcs, on l'ajoute
        if(!dejaPresent)
            this.adj.add(new Arc(destination,cout));
    }


    /**
    Renvoie la liste des arcs d'un noeud
     */
    public List<Arc> getArcs(){
        return this.adj;
    }

}
