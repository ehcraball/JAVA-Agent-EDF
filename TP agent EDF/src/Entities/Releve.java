package Entities;

public class Releve
{
    //membres privés
    private String dateReleve;
    private int valeurReleve;

    //Constructeur
    public Releve(String uneDate, int uneValeur){
        dateReleve = uneDate;
        valeurReleve = uneValeur;
    }
    // GETTER ET SETTER
    public String getDateReleve(){
        return dateReleve;
}
    public int getValeurReleve(){
        return valeurReleve;
    }
}


