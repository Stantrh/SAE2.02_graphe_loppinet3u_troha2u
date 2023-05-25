import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class BellmanFord implements Algorithme {
    /**
     *  Retourne un objet valeur qui lui contient toutes les informations sur le graphe
     *  en utilisant l'algorithme de BellmanFord
     * @param g graphe dont on doit trouver le chemin le plus court
     * @param depart noeud de départ du graphe
     * @return un objet Valeur contenant les informations sur
     */
    public Valeur resoudre(Graphe g, String depart){
        // On crée d'abord les deux variables contenant la liste des noeuds (en String) et un objet Valeur
        List<String> noeuds = g.listeNoeuds();
        Valeur valeurs = new Valeur();

        // Initialisation des valeurs avec +∞ sauf pour le noeud de départ qu'on initialise à 0
        for (String noeud : noeuds) {
            valeurs.setParent(noeud, null);
            if (noeud.equals(depart))
                valeurs.setValeur(noeud, 0);
            else
                valeurs.setValeur(noeud, Double.MAX_VALUE);
        }
        boolean memeLigne = true;


        while(memeLigne) {
            memeLigne = false;

            // L'algorithme en lui même
            // Donc on parcourt la liste du premier noeud au dernier
            // comme de gauche à droite lorsqu'on le fait sur papier

            for (int i = 0; i < noeuds.size(); i++) {

                // On stocke la liste de tous les noeuds adjacents au noeud courant
                List<Arc> arcs = g.suivants(noeuds.get(i));

                // On la parcourt pour trouver le chemin le plus court parmi tous ces noeuds et le noeud actuel
                for (Arc a : arcs) {
                    // Noeud de destination
                    String destination = a.getDest();
                    double cout = a.getCout();
                    // Valeur du noeud noeud
                    double distanceNouvelle = valeurs.getValeur(noeuds.get(i)) + a.getCout();
                    // Valeur du noeud lors du parcours de la liste d'arc
                    double distanceDestination = valeurs.getValeur(destination);
                    // Mise à jour de la distance si une distance plus courte est trouvée
                    if (distanceDestination > distanceNouvelle) {
                        valeurs.setValeur(destination, distanceNouvelle);
                        valeurs.setParent(destination, noeuds.get(i));
                        memeLigne = true;
                    }
                }

            }
        }
        return valeurs;
    }
}
