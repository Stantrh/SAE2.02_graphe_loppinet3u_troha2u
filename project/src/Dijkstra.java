import java.util.ArrayList;
import java.util.List;

public class Dijkstra implements Algorithme {
    /**
     * calcule les plus courts chemins vers les autres nœuds du graphe avec l’algorithme de Dijkstra.
     * @param g le graphe à étudier
     * @param depart le nom du noeud de départ
     * @return Valeur représentant une TreeMap
     */
    @Override
    public Valeur resoudre(Graphe g, String depart) {


        // Initialisation
        Valeur valeur = new Valeur();
        // Q <- {} // utilisation d’une liste de noeuds à traiter
        ArrayList<String> Q = new ArrayList<String>();
        // Liste des sommets du graphe G
        List<String> noeuds = g.listeNoeuds();
        // Pour chaque sommet v de G faire
        for(String noeud : noeuds){
            // v.distance <- Infini
            valeur.setValeur(noeud, Double.MAX_VALUE);
            // v.parent <- Indéfini
            valeur.setParent(noeud, null);
            // Q <- Q U {v} (ajouter le sommet v à la liste Q)
            Q.add(noeud);
        }

        // A.distance <- 0
        valeur.setValeur(depart, 0);

        // Tant que Q est un ensemble non vide faire
        while(!Q.isEmpty()){
            // On recherche le sommet de Q tel que sa distance soit la plus petite et on garde son indice
            // On initialise la distanceMin et l'indiceMin au premier Element et on va le comparer au reste de la liste
            double distanceMin = valeur.getValeur(Q.get(0));
            int indiceMin = 0;
            // Parcours de la liste à partir du deuxième élément (car on a initialisé au premier élément la distanceMin
            for(int i=1;i<Q.size();i++){
                // Si un élément a une distance plus petite alors on change la valeur de distanceMin et l'indiceMin pour l'indice de cet élément
                if(valeur.getValeur(Q.get(i))<distanceMin){
                    distanceMin = valeur.getValeur(Q.get(i));
                    indiceMin = i;
                }
            }

            // u <- un sommet de Q telle que u.distance est minimale
            String u = Q.get(indiceMin);
            // Q <- Q \ {u} (enlever le sommet u de la liste Q)
            Q.remove(indiceMin);

            // On stocke tous les arcs du noeud u. (La destination de chaque arc est un noeud adjacent à u)
            List<Arc> noeudsAdjacentsU = g.suivants(u);
            // Pour chaque sommet v de Q tel que l’arc (u,v) existe faire
            for(Arc a : noeudsAdjacentsU){
                String v = a.getDest();
                // D <- u.distance + poids(u,v)
                double distanceNouvelle = valeur.getValeur(u) + a.getCout();
                // Si D < v.distance
                if(distanceNouvelle < valeur.getValeur(v)){
                    // Alors v.distance <- D
                    valeur.setValeur(v,distanceNouvelle);
                    // v.parent <- u
                    valeur.setParent(v,u);
                }
            }

        }
        return valeur;
    }
}
