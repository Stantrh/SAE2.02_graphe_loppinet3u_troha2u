import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
     * Constructeur par défaut
     */
    public GrapheListe(){
        this.ensNom = new ArrayList<String>();
        this.ensNoeuds = new ArrayList<Noeud>();
    }


    /**
     * Getter qui retourne la liste des noeuds.
     * Utile pour les tests mais également lorsqu'on souhaite accéder
     * aux noeuds adjacents d'un noeud de la liste
     */
    public List<Noeud> getListeNoeuds(){
        return this.ensNoeuds;
    }


    /**
     * Retourne une liste de String comportant tous les noeuds du graphe (pas forcément dans l'ordre)
     * @return List<String>
     */
    public List<String> listeNoeuds(){
        return this.ensNom;
    }

    /**
    Retourne la liste des Arcs d'un noeud dont le nom est passé en paramètres
     Si le noeud dont le nom est passé en paramètres n'est pas trouvé dans le graphe
     Alors on retourne une liste vide grâce à la classe Collection
     @param n String qui correspond au nom du noeud dont on veut les arcs
     @return List<Arc> </Arc>
     */
    public List<Arc> suivants(String n){
        int indice = this.ensNoeuds.indexOf(n);
        if(indice != -1){
            return this.ensNoeuds.get(indice).getArcs();
        }else{
            return Collections.emptyList();
        }
    }

    /**
     * Cette méthode permet d'ajouter un arc entre le noeud de départ dont le nom est passé en paramètre
     * et le nom du noeud de destination dont le nom est également donné en paramètre. Ainsi que le cout
     * Si les noeuds n'existent pas alors on les crée
     * Puis on ajoute l'arc
     * @param depart
     * @param destination
     * @param cout
     */
    public void ajouterArc(String depart, String destination, double cout){
        // Vérifier d'abord si depart existe bien
        if(this.ensNom.indexOf(depart) != -1){
            // Mais destination n'existe pas
            if(this.ensNom.indexOf(destination) == -1){
                Noeud nDest = new Noeud(destination);
                this.ensNoeuds.add(nDest);
                this.ensNom.add(destination);
            }
            this.ensNoeuds.get(this.ensNom.indexOf(depart)).ajouterArc(destination, cout);
        }else{
            int indiceDestination = this.ensNom.indexOf(destination);
            Noeud nDepart = new Noeud(depart);
            this.ensNoeuds.add(nDepart);
            this.ensNom.add(depart);
            //Si le noeud de destination n'existe pas
            if(indiceDestination == -1 ){
                Noeud nDest = new Noeud(destination);
                this.ensNoeuds.add(nDest);
                this.ensNom.add(destination);
            }

            nDepart.ajouterArc(destination, cout);

        }
    }


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


    public String toGraphviz(){
        String aff = "digraph G{\n";
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




    public static void main(String[] args){

        GrapheListe gL = new GrapheListe();

        // Ajout des arcs entre les noeuds
        // Ce qui va donc aussi créer les noeuds
        /*
        gL.ajouterArc("A", "B", 12);
        gL.ajouterArc("A", "D", 87);

        gL.ajouterArc("B", "E", 11);

        gL.ajouterArc("C", "A", 19);

        gL.ajouterArc("D", "B", 23);
        gL.ajouterArc("D", "C", 10);

        gL.ajouterArc("E", "D", 43);
        */
        gL.ajouterArc("A", "B", 12);
        gL.ajouterArc("C", "A", 19);
        gL.ajouterArc("A", "D", 87);

        gL.ajouterArc("B", "E", 11);



        gL.ajouterArc("D", "B", 23);
        gL.ajouterArc("D", "C", 10);

        gL.ajouterArc("E", "D", 43);

        System.out.println(gL.toGraphviz());

    }
}
