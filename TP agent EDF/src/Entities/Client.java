package Entities;

import java.util.ArrayList;

public class Client
{
    private int idClient;
    private String nomClient;
    private ArrayList<Releve> lesReleves;

    public Client(int idClient, String nomClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        lesReleves = new ArrayList<>();
    }

    public int getIdClient() {
        return idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public ArrayList<Releve> getLesReleves() {
        return lesReleves;
    }

    public void AjouterUnReleve(Releve unReleve){
        lesReleves.add(unReleve);
    }
    public int calculerConso(){
        int conso = 0;
        if (lesReleves.size()==1){
            conso = lesReleves.get(0).getValeurReleve();
        }
        else if(lesReleves.size()>1){
            conso = lesReleves.get(lesReleves.size()-1).getValeurReleve() - lesReleves.get(lesReleves.size()-2).getValeurReleve();
        }
        return conso;

    }
}


