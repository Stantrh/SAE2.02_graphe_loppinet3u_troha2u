import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";


    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;

                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
    }


    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public int[] deplacerPerso(int i, int j,String action) {
        // case courante
        int[] courante = {i,j};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        if (!this.murs[suivante[0]][suivante[1]]) {
            // on met a jour personnage
            return suivante;
        }
        return courante;
    }


    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return false;
    }


    public Graphe genererGraphe(){
        GrapheListe g = new GrapheListe();
        // Parcours ligne par ligne et chaque colonne par ligne

        int nbLignes = this.murs[0].length;
        int nbColonnes = this.murs.length;
        for (int ligne = 0; ligne < nbLignes; ligne++) {
            for (int colonne = 0; colonne < nbColonnes; colonne++) {

                // Si murs[x][y] vaut false, c'est à dire qu'aux coordonnées x y il n'y a pas de murs, donc on peut créer un Noeud
                if(!murs[colonne][ligne]){
                    // D'abord créer un Noeud pour vérifier les arcs disponibles après
                    String nomNoeud = "(" + colonne + "," + ligne + ")";
                    // Vérifier les arcs à créer depuis le noeud actuel (les coordonnées actuelles)

                    // On stocke tous les déplacements possibles dans une liste d'entiers pour vérifier
                    // Par exemple, pour l'indice 0 de deplacementsX et Y, on va rester sur la meme colonne mais aller en haut
                    // Puisque colonne += 0 mais ligne += -1, ainsi cela monte les coordonnées.
                    int[] deplacementsX = {0, 0, -1, 1}; // Haut  Bas  Gauche  Droite
                    int[] deplacementsY = {-1, 1, 0, 0}; // Haut  Bas  Gauche  Droite

                    for (int i = 0; i < deplacementsX.length; i++) {
                        int newX = colonne + deplacementsX[i];
                        int newY = ligne + deplacementsY[i];

                        if (newX >= 0 && newX < nbColonnes && newY >= 0 && newY < nbLignes && !murs[newX][newY]) {
                            String nomNoeudDest = "(" + newX + "," + newY + ")";
                            g.ajouterArc(nomNoeud, nomNoeudDest, 1);
                        }
                    }
                }
            }
        }
        return g;

    }







    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }



    public static void main(String[] args) throws Exception{

        Labyrinthe l = new Labyrinthe("./ressources/labySimple/laby2.txt");
        Graphe g = l.genererGraphe();
        Dijkstra d = new Dijkstra();
        Valeur v = d.resoudre(g, "(1,1)");
        System.out.println(v.toString());
        List<String> chemin = v.calculerChemin("(17,12)");
        for(String s : chemin){
            System.out.print(s + " -> ");
        }
    }
}
