import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * Cette classe permet :
 * La lecture des graphes à partir de fichiers texte
 * Le calcul des chemins les plus courts pour des noeuds donnés
 * L'affichage des chemins pour des noeuds donnés
 * Les noeuds donnés sont choisis aléatoirement pour chaque graphe, compris dans les noeuds du graphe.
 * Et le résultat est dans la console sous forme d'affichage par graphe dont le nom du fichier est spécifié.
 */
public class MainDijkstra {
    public static void main(String[] args){
            // Pour accéder à la liste de tous les fichiers du dossier Graphes
            File dossier = new File("./ressources/Graphes");
            File[] liste = dossier.listFiles();

            Dijkstra d = new Dijkstra();

            // Parcours du tableau qui contient les fichiers
            for(File fichier : liste){
                if (fichier.isFile()) { // On vérifie que c'est bien un fichier au
                    // cas où il y ait des dossiers dans Graphes ou d'autres erreurs...
                    System.out.println(fichier.getName() + " : ");

                    // On crée un graphe à partir du chemin du fichier courant
                    GrapheListe g = new GrapheListe(fichier.getPath());

                    // On stocke dans taille le nombre de noeuds contenu dans le grahe du fichier correspondant
                    int taille = g.listeNoeuds().size();


                    /* Puis le noeud de départ et d'arrivée sont choisis aléatoirement entre les noeuds qui existent du graphe.
                       Bien évidemment on exclu le fait qu'un des noeuds soit le noeud 0, d'où le + 1 à la fin
                       La méthode nextInt permet de définir des bornes tandis que Math.random() aurait pu renvoyer 0 (mais le noeud 0
                        n'existant pas, cela aurait posé problème)*/
                    Random random = new Random();
                    int depart = random.nextInt(taille) + 1;
                    int fin = random.nextInt(taille) + 1;

                    while(fin == depart){ // S'assurer que fin et depart ne soient pas le même noeud
                        depart = random.nextInt(taille) + 1;
                    }

                    // Puis on les convertit en string pour les utiliser dans les méthodes resoudre et calculerChemin
                    String noeudDepart = "" + depart;
                    String noeudFin = "" + fin;

                    System.out.print("Noeud de départ : " + noeudDepart + " || Noeud de fin : " + noeudFin + "\nChemin le plus court : ");

                    // Calcul des chemins les plus courts pour des noeuds donnés
                    Valeur v = d.resoudre(g, noeudDepart);

                    List<String> chemin = v.calculerChemin(noeudFin);
                    // Puis on affiche le chemin obtenu
                    for(int k = 0; k < chemin.size(); k++){
                        // Si c'est le dernier noeud on ne rajoute pas de flêche à la fin
                        System.out.print(chemin.get(k));
                        if(k != chemin.size() -1){
                            System.out.print(" -> ");
                        }
                    }
                    System.out.println("\n");

                }
            }
    }
}
