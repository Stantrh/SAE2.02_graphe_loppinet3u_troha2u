import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainDijkstra {
    public static void main(String[] args){




            // Pour accèder à la liste de tous les fichiers du dossier Graphe
            File dos = new File("./ressources/Graphes");
            File[] fichiers = dos.listFiles();


            // Notre liste qui contient les graphes
            ArrayList<Graphe> graphes = new ArrayList<Graphe>();

            // On parcourt notre tableau contenant les fichiers
            // Puis pour chaque fichier on va créer un graphe en utilisant
            // le constructeur de GrapheListe qui crée un graphe à partir d'un nom de fichier
            int i = 0; // pour retrouver le grapeh qu'on vient d'ajouter à notre liste de graphes
            System.out.println("coucou");
            for(File fich : fichiers){
                if (fich.isFile()) { // Vérifier si le fichier existe bien
                    String nomFich = fich.getName();
                    graphes.add(new GrapheListe(nomFich));
                    BellmanFord;
                    System.out.println("Pour le fichier : " + nomFich + " le chemin le plus court pour les noeuds " + );
                    graphes.get(i).;
                    i++;
                }
            }





    }
}
