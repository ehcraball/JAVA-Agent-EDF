package Tools;

import Entities.Agent;
import Entities.Client;
import Entities.Releve;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel {

    private String[] entete;
    private Object[][] lignes;

    @Override
    public int getRowCount() {
        return lignes.length;
    }

    @Override
    public int getColumnCount() {
        return entete.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return lignes[rowIndex][columnIndex];
    }
    @Override
    public String getColumnName(int column){
        return entete[column];
    }
    public void loadDataAgent(ArrayList<Agent> desAgents ){
        entete = new String[]{"Numéro", "Nom"};
        lignes = new Object[desAgents.size()][2];
        int i = 0;
        for (Agent agt : desAgents){
            lignes[i][0] = agt.getIdAgent();
            lignes[i][1] = agt.getNomAgents();
            i++;
        }
        fireTableChanged(null);
    }

    public void LoadDatasClients(ArrayList<Client> lesClients) {
        entete = new String[]{"Numéro", "Nom"};
        lignes = new Object[lesClients.size()][2];
        int i = 0;
        for (Client clt : lesClients){
            lignes[i][0] = clt.getIdClient();
            lignes[i][1] = clt.getNomClient();
            i++;
        }
        fireTableChanged(null);
    }
    public void LoadDatasReleve(ArrayList<Releve> lesReleve) {
        entete = new String[]{"Date", "Valeur"};
        lignes = new Object[lesReleve.size()][2];
        int i = 0;
        for (Releve rlv : lesReleve){
            lignes[i][0] = rlv.getDateReleve();
            lignes[i][1] = rlv.getValeurReleve();
            i++;
        }
        fireTableChanged(null);
    }
}

