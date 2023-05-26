import java.util.ArrayList;
import java.util.List;

public class GrapheLabyrinthe implements Graphe{


    /**
     * Objet Labyrinthe utile pour faire toutes les opérations ainsi que les parcours
     */
    private Labyrinthe laby;


    public GrapheLabyrinthe(Labyrinthe l){
        this.laby = l;
    }


    /**
     Retourne une liste de String qui correspond au noms des noeuds du graphe
     @return List<String> Liste de noms de noeuds
     */
    public List<String> listeNoeuds(){
        List<String> l = new ArrayList<>();

        int lignes = this.laby.getLengthY();
        int colonnes = this.laby.getLength();
        for(int ligne = 0; ligne < lignes; ligne++){
            for(int colonne = 0; colonne < colonnes; colonne++){
                if(!this.laby.getMur(colonne, ligne)){
                    String nomNoeud = "(" + colonne + "," + ligne + ")";
                    l.add(nomNoeud);
                }
            }
        }

        return l;
    }


    /**
     Retourne une liste qui contient tous les Arcs partant du noeud qui a pour nom
     n passé en paramètres
     @return List<String> Liste d'Arcs
     */
    public List<Arc> suivants(String n){

        List<Arc> l = new ArrayList<Arc>();

        // Dimensions du labyrinthe
        int lignes = this.laby.getLengthY();
        int colonnes = this.laby.getLength();

        // Extraire les coordonnées du nom du graphe actuel.
        int x, y;

        // Supprime les parenthèses et divise la chaîne en fonction de la virgule
        String[] coordinates = n.replaceAll("[()]", "").split(",");

        // Récupère les valeurs x et y converties en entiers
        x = Integer.parseInt(coordinates[0].trim()); // colonne
        y = Integer.parseInt(coordinates[1].trim()); // ligne

        String[] deplacements = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};

        for(int i = 0; i < deplacements.length; i++){
            int[] suivant = Labyrinthe.getSuivant(x, y, deplacements[i]);

            int col = suivant[0];
            int lig = suivant[1];
            if(!this.laby.getMur(col, lig) && (col < colonnes && lig < lignes) && (col >= 0 && lig >=0)){
                String dest = "(" + col + "," + lig + ")";
                l.add(new Arc(dest, 1));
            }
        }
        return l;
    }
}
