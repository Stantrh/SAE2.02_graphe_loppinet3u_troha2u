public class Dijkstra implements Algorithme {
    /*
            Entrées :
        G un graphe orienté avec une pondération (poids) positive des arcs
        A un sommet (d´epart) de G

        Début
        Q <- {} // utilisation d’une liste de noeuds à traiter
        Pour chaque sommet v de G faire
            v.distance <- Infini
            v.parent <- Indéfini
            Q <- Q U {v} // ajouter le sommet v à la liste Q
        Fin Pour

        A.distance <- 0
        Tant que Q est un ensemble non vide faire
            u <- un sommet de Q telle que u.distance est minimale
            Q <- Q \ {u} // enlever le sommet u de la liste Q
            Pour chaque sommet v de Q tel que l’arc (u,v) existe faire
                D <- u.distance + poids(u,v)
                Si D < v.distance
                    Alors v.distance <- D
                    v.parent <- u
                Fin Si
            Fin Pour
        Fin Tant que
        Fin
     */
    public Valeur resoudre(Graphe g, String depart){

    }
}
