import java.util.List;

public class GrapheListe implements Graphe{
    /*
    Correspond à une liste qui contient les noms des objets noeuds stockés
     */
    private List<String> ensNom;

    
    private List<Noeud> ensNoeuds;

    public List<String> listeNoeuds(){
        throw new Error("ldld");
    }

    public List<Arc> suivants(String n){
        throw new Error("ldld");
    }
}
