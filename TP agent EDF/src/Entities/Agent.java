package Entities;


import java.util.ArrayList;

public class Agent
{
    private int idAgent;
    private String nomAgents;
    private ArrayList<Client> lesClients;

    public Agent(int idAgent, String nomAgents){
        this.idAgent = idAgent;
        this.nomAgents = nomAgents;
        lesClients = new ArrayList<>();
    }
    public int getIdAgent(){
        return idAgent;
}
    public String getNomAgents(){
        return nomAgents;
    }

    public ArrayList<Client> getLesClients() {
        return lesClients;
    }
    public void AjouterUnClient (Client unClient){
        lesClients.add(unClient);
    }
}


