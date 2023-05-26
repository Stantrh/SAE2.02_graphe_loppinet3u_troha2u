import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Cette classe permet de comparer le nombre d'arcs par graphe en fonction de son nombre de noeuds.
 * Cela oppose les graphes proposés dans le dossier Graphes situé dans ./ressources, à la méthode
 * genererGraphe(int taille) qui elle est dans GrapheListe.
 * Dans ce main, on crée des graphes de même taille (autant de noeuds) pour pouvoir avoir une réelle
 * estimation de la différence de connectivité entre les deux types de graphes.
 */
public class MainComparaisonConnectivite {

    public static void main(String[] args){
        // Dans ce main on va comparer la connectivité, c'est à dire le nombre d'arcs par graphe entre les graphes donnés sur Arche
        // Et ceux générés automatiquement


        // Pour avoir le nombre de noeuds, on regarde le .size de listeNoeuds d'un graphe
        // Dossier contenant les graphes que l'on va comparer
        File dossier = new File("./ressources/Graphes");
        File[] liste = dossier.listFiles();
        // Pour les comparaisons, et plus précisément sur le tableau des comparaisons algorithmiques.
        // Nous avons besoin que les graphes soient organisés de sorte à ce que la lecture se fasse
        // Des moins denses aux plus denses. On va donc trier le tableau qui en contient les fichiers
        // Pour ce faire, il faut redéfinir le comparateur utilisé par la méthode Array.sort pour que la comparaison
        // du nom de fichier ne se fasse pas de gauche à droite caractère par caractère mais en fonction
        // du numéro contenu dans le nom du fichier
        Arrays.sort(liste, (fichier1, fichier2) -> {
            String nom1 = fichier1.getName().replaceAll("\\D+", "");
            String nom2 = fichier2.getName().replaceAll("\\D+", "");

            int nb1 = Integer.parseInt(nom1);
            int nb2 = Integer.parseInt(nom2);

            return Integer.compare(nb1, nb2);
        });

        try{
            PrintWriter writer = new PrintWriter(new FileWriter("./ressources/comparatifConnectivite.csv"));

            // On écrit la première ligne du fichier pour les en têtes et noms de colonnes
            writer.println("Nombre de noeuds" + ";" + "Nombre d'arcs (Arche)"  + ";" + "Nombre d'arcs (Graphes générés)");

            long debut = System.nanoTime();
            // Pour chaque fichier de la liste faire
            for(File fichier : liste) {
                if (fichier.isFile()) { // Vérification que fichier ne soit pas autre chose qu'un fichier

                    // Création du graphe selon le fichier
                    GrapheListe g = new GrapheListe(fichier.getPath());



                    // Grâce à la taille du graphe récupéré depuis le fichier
                    GrapheListe g2 = new GrapheListe(g.listeNoeuds().size());


                    int arcsGraphes = 0;
                    int arcsGen = 0;

                    // On parcourt la liste des noeuds de chaque graphe pour avoir le nombre total d'arcs par graphe

                    for(int i = 0; i < g.listeNoeuds().size(); i++){

                        String noeudGr = g.listeNoeuds().get(i);
                        String noeudGen = g2.listeNoeuds().get(i);

                        // On veut la longueur de la liste que suivants() retourne pour l'ajouter à nos variables qui comptent

                        List<Arc> l1 = g.suivants(g.listeNoeuds().get(i));
                        List<Arc> l2 = g2.suivants(g2.listeNoeuds().get(i));

                        arcsGraphes += l1.size();
                        arcsGen += l2.size();

                    }
                    writer.print(g.listeNoeuds().size() + ";" + arcsGraphes + ";" + arcsGen); // Pour pouvoir comparer les algorithmes en fonction du nombre de noeuds
                    writer.println();
                }
            }
            long fin = System.nanoTime();
            long result = fin - debut;
            System.out.println("Le programme a pris : " + result / 100000000 + " secondes.");
            writer.close();
        } catch(IOException err){
            err.printStackTrace();
        }

    }
}
