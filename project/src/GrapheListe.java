import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrapheListe implements Graphe{

    /**
    Correspond à une liste qui contient les noms des objets noeuds stockés
     */
    private List<String> ensNom;

    /**
     * Correspond à une liste qui contient les Noeuds du graphe
     */
    private List<Noeud> ensNoeuds;

    /**
     * Constructeur par défauts
     */
    public GrapheListe(){
        this.ensNom = new ArrayList<String>();
        this.ensNoeuds = new ArrayList<Noeud>();
    }

    /**
     * Constructeur d'un graphe à partir du nom d'un fichier. On lit le fichier ligne par ligne
     * Pour ensuite créer un arc avec chaque ligne puisqu'on a le cout, le noeud de départ et celui de destination
     * @param nomFichierSrc nom du fichier source dont on veut extraire les données du graphe
     */
    public GrapheListe(String nomFichierSrc){
        // Initialisation des listes dans tous les cas pour éviter de provoquer des erreurs à l'avenir (méthode add notamment)
        this.ensNom = new ArrayList<String>();
        this.ensNoeuds = new ArrayList<Noeud>();

        // On suggère qu'il n'y a pas d'erreur dans le fichier texte
        try {
            BufferedReader fichier = new BufferedReader(new FileReader(nomFichierSrc));
            String ligne = fichier.readLine();
            while(ligne != null){
                // On prend chaque élément séparé par une tabulation et on les mets dans un tableau de String
                String[] colonnes = ligne.split("\t");
                // On ajoute ensuite les arcs en utilisant la méthode faite pour
                ajouterArc(colonnes[0],colonnes[1], Integer.parseInt(colonnes[2]));
                ligne = fichier.readLine();
            }
        } catch (FileNotFoundException e) {
            // Si le fichier n'est pas trouvé, on l'indique
            System.err.println("Le fichier est introuvable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructeur d'un graphe d'une taille donnée
     * @param taille le nombre de noeuds du graphe au total
     */
    public GrapheListe(int taille){
        // Initialisation les attributs de l'objet à des listes vides
        this.ensNom = new ArrayList<String>();
        this.ensNoeuds = new ArrayList<Noeud>();

        int nbArcsMax = 10;
        int nbArcsMin = 3;

        // Pour le nombre de noeuds indiqué :
        for(int i=1;i<taille+1;i++){
            // On ajoute un arc entre chaque noeud et le noeud suivant afin d'obtenir une chemin qui relie tous les noeuds
            ajouterArc(Integer.toString(i), Integer.toString(i+1), (int)Math.ceil(Math.random()*100)); // Arrondit le coût à l'entier supérieur (pour éviter de tomber sur 0)

            // Pour chaque noeud du graphe, on ajoute un nombre aléatoire d'arcs reliant ce noeud et un autre noeud aléatoirement de la liste
            for(int j=0;j<(int)(Math.floor(Math.random()*nbArcsMax)+nbArcsMin);j++){
                ajouterArc(Integer.toString(i), Integer.toString((int)Math.ceil(Math.random()*taille)), (int)Math.ceil(Math.random()*100)); // Arrondit le coût à l'entier supérieur (pour éviter de tomber sur 0)
            }
        }

    }

    /**
     * Retourne la liste des noeuds en tant qu'objets Noeud
     * Utile pour les tests mais également lorsqu'on souhaite accéder
     * aux noeuds adjacents d'un noeud de la liste
     * @return ensNoeud liste des Noeuds (en Noeud)
     */
    public List<Noeud> getListeNoeuds(){
        return this.ensNoeuds;
    }


    /**
     * Retourne une liste de String comportant tous les noeuds du graphe (pas forcément dans l'ordre)
     * @return la liste de Noeuds (en chaines de caractères)
     */
    public List<String> listeNoeuds(){
        return this.ensNom;
    }

    /**
    Retourne la liste des Arcs d'un noeud dont le nom est passé en paramètres
     Si le noeud dont le nom est passé en paramètres n'est pas trouvé dans le graphe
     Alors on retourne une liste vide grâce à la classe Collection
     @param n nom du noeud dont on veut les arcs
     @return la liste des Arcs d'un noeud
     */
    public List<Arc> suivants(String n){

        int indice = this.ensNom.indexOf(n);
        if(indice != -1){
            return this.ensNoeuds.get(indice).getArcs();
        }else{
            return Collections.emptyList();
        }
        /*
        Noeud noeud = null;
        for(Noeud elt: this.ensNoeuds){
            if(elt.equals(new Noeud(n))){
                noeud = elt;
            }
        }
        return noeud.getArcs();
        */
    }

    /**
     * Cette méthode permet d'ajouter un arc entre le noeud de départ dont le nom est passé en paramètre
     * et le nom du noeud de destination dont le nom est également donné en paramètre. Ainsi que le cout
     * Si les noeuds n'existent pas alors on les crée
     * Puis on ajoute l'arc
     * @param depart noeud de départ
     * @param destination noeud de destination de l'arc
     * @param cout cout de l'arc reliant le noeud de départ à celui de destination
     */
    public void ajouterArc(String depart, String destination, double cout){
        // Vérifier d'abord si le noeud de depart existe bien
        if(this.ensNom.indexOf(depart) != -1){
            // Mais si jamais le noeud de destination n'existe pas
            if(this.ensNom.indexOf(destination) == -1){
                // Alors on l'ajoute aux deux listes
                Noeud nDest = new Noeud(destination);
                this.ensNoeuds.add(nDest);
                this.ensNom.add(destination);
            }
            // Puis dans tous les cas on ajoute l'arc au noeud de départ.
            this.ensNoeuds.get(this.ensNom.indexOf(depart)).ajouterArc(destination, cout);
        }else{
            // Nous sommes dans le cas où le noeud de départ n'existe pas
            // Donc on le crée et l'ajoute au deux listes
            Noeud nDepart = new Noeud(depart);
            this.ensNoeuds.add(nDepart);
            this.ensNom.add(depart);
            //Si le noeud de destination n'existe pas
            if(this.ensNom.indexOf(destination) == -1 ){
                // On l'ajoute aux deux listes
                Noeud nDest = new Noeud(destination);
                this.ensNoeuds.add(nDest);
                this.ensNom.add(destination);
            }
            // Puis, que le noeud de destination existe ou pas, on peut ajouter l'arc entre les deux
            nDepart.ajouterArc(destination, cout);

        }
    }

    /**
     * Retourne une chaine de caractère qui correspond à la représentation du graphe en
     * parcourant sa liste de noeuds
     * @return affichage du graphe
     */
    public String toString(){
        String aff = "";
        for(int i = 0; i < this.ensNom.size(); i++){
            // Ligne pour vérifier que le noeud sur lequel on est actuellement
            // possède bien des noeuds adjacents, sinon inutile de l'écrire
            if(this.ensNoeuds.get(i).getArcs().size() != 0){
                String s = this.ensNom.get(i);
                aff += (s + " -> ");
                int indice = this.ensNom.indexOf(s);
                Noeud n = this.ensNoeuds.get(indice);
                for(Arc a : n.getArcs()){
                    aff += (a.getDest() + "(" + (int)a.getCout() + ") ");
                }
                aff += "\n";
            }

        }
        return aff;
    }


    /**
     * Retourne un String qui correspond à l'affichage pour visualiser le graphe (sur plantUML ou
     * https://dreampuf.github.io/GraphvizOnline/)
     * @return aff
     */
    public String toGraphviz(){
        String aff = "digraph G{\n";
        // On parcourt la liste de Noeuds (contenant leurs noms uniquement)
        // Puis on va ensuite parcourir la liste des noeuds adjaçants au noeud actuel et les afficher
        for(String s : this.ensNom){
            int indice = this.ensNom.indexOf(s);
            Noeud n = this.ensNoeuds.get(indice);
            for(Arc a : n.getArcs()){
                aff += (s + " -> " + a.getDest() + " [label = " + (int)a.getCout() + "]\n");
            }
        }
        aff += "}";
        return aff;
    }

    /**
     * Crée un nouveau fichier à partir d'un fichier sous forme de matrice représentant les relations entre
     * chaque noeud. Cela donne un fichier qui lui peut être interprété par le constructeur si on lui passe son
     * nom en paramètre.
     * On part du principe que le fichier contenant la matrice est écrit sans erreurs.
     * @param nomFichierSrc fichier source dont on veut extraire les arcs (représentés sous forme de matrice)
     * @param nomFichierDest fichier de destination qui comportera un affichage tel que (noeudDépart NoeudDestination Cout)
     */
    public void creerFichier(String nomFichierSrc, String nomFichierDest){
        try{
            // Pour pouvoir lire le fichier on crée un BufferedReader qui va le lire ligne par ligne
            BufferedReader fichier = new BufferedReader(new FileReader(nomFichierSrc));

            // Pour écrire dans le ficheir dont le nom est donné en paramètres on crée un BufferedWriter
            BufferedWriter fich = new BufferedWriter(new FileWriter(nomFichierDest));

            String ligne = fichier.readLine();
            // Première ligne du fichier pour se rappeler de quelle colonnes
            // correspond à quel noeud
            String[] ligneUn = ligne.split("\t");
            ligne = fichier.readLine();

            // On parcourt a partir de la 2eme ligne puisqu'on veut créer
            // les noeuds adjacents pour chaque noeuds (écrits à partir de la 2eme ligne)
            while(ligne != null){
                // Ici on a donc la ligne dans un tableau chaque
                // terme du tableau étant une colonne
                String[] colonnes = ligne.split("\t");

                // On parcourt la ligne qui contient les noeuds
                // adjacents au noeud
                for(int i = 1; i < colonnes.length; i++){
                    if(!colonnes[i].equals("0.")){
                        fich.write(colonnes[0] + " " + ligneUn[i] + " " + colonnes[i]);
                        fich.newLine();
                    }
                }
                ligne = fichier.readLine();
            }
            // On ferme les Buffers pour que la lecture et l'écriture de flux soient bien finis.
            fichier.close();
            fich.close();
        // On gère les exceptions soit si le fichier source n'est pas trouvé
            // Soit s'il y a une erreur de flux de lecture/écriture différente
        }catch(FileNotFoundException e){
            System.err.println("Le chemin vers le fichier est incorrect");
        }catch(IOException e){
            e.printStackTrace();
        }


    }


    public static void main(String[] args){


        // On crée notre objet GrapheListe
        GrapheListe gL = new GrapheListe();

        // Ajout des arcs entre les noeuds
        // Ce qui va donc aussi créer les noeuds
        gL.ajouterArc("A", "B", 12);
        gL.ajouterArc("C", "A", 19);
        gL.ajouterArc("A", "D", 87);

        gL.ajouterArc("B", "E", 11);



        gL.ajouterArc("D", "B", 23);
        gL.ajouterArc("D", "C", 10);

        gL.ajouterArc("E", "D", 43);


        System.out.println(gL.toGraphviz());

        gL.creerFichier("ressources/matrice.txt", "ressources/output.txt");

    }
}
