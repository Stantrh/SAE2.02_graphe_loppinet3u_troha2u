public class Arc {
    /*
    Nom du noeud de destination de l'arc
     */
    private String dest;

    /*
    Cout/Poids de l'arc
     */
    private double cout;

    /*
    Constructeur d'un objet Arc à partir d'un nom et d'un réel
    Vérifie que le nom du noeud de destination ne soit pas null et le cout bien strictement
    positif
     */
    public Arc(String n, double c){
        if(n == null){
            throw new IllegalArgumentException("Le nom ne doit pas être nul");
        }else if(c <= 0){
            throw new IllegalArgumentException("Le cout de l'arc doit être strictement positif");
        }else{
            this.dest = n;
            this.cout = c;
        }
    }


    public String getDest(){
        return this.dest;
    }

    public double getCout(){
        return this.cout;
    }
}
