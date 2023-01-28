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
        this.lesReleves = new ArrayList<>();
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
    public void AjouterUnReleve(Releve unReleve)
    {
        this.lesReleves.add(unReleve);
    }
    public int CalculerConsommation()
    {
        if(lesReleves.size() >= 2)
        {
            return lesReleves.get(lesReleves.size()-1).getValeurReleve() - lesReleves.get(lesReleves.size() - 2).getValeurReleve();
        }
        else if(lesReleves.size() == 1)
        {
            return lesReleves.get(lesReleves.size()-1).getValeurReleve();
        }
        else
        {
            return 0;
        }
    }
    public boolean VerifierValeurNouveauReleve(int nouvelleValeur)
    {
        if(lesReleves.size() > 1)
        {
            return nouvelleValeur >= lesReleves.get(lesReleves.size() - 1).getValeurReleve();
        }
        else
        {
            return true;
        }
    }
}


