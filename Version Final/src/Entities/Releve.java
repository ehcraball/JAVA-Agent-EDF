package Entities;

public class Releve
{
    private String dateReleve;
    private int valeurReleve;

    public Releve(String dateReleve, int valeurReleve) {
        this.dateReleve = dateReleve;
        this.valeurReleve = valeurReleve;
    }
    public String getDateReleve() {
        return dateReleve;
    }
    public int getValeurReleve() {
        return valeurReleve;
    }
}


