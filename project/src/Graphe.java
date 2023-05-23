import java.util.List;

/**
 * Interface représentant un Graphe avec ses deux méthodes
 */
public interface Graphe {


    /**
    Retourne une liste de String qui correspond au noms des noeuds du graphe
    @return List<String> Liste de noms de noeuds
     */
    public List<String> listeNoeuds();

    /**
    Retourne une liste qui contient tous les Arcs partant du noeud qui a pour nom
    n passé en paramètres
    @return List<String> Liste d'Arcs
     */
    public List<Arc> suivants(String n);
}
