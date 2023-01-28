package Tools;

import Entities.Agent;
import Entities.Client;
import Entities.Releve;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel
{
    private String[] nomsColonnes;
    private Object[][] lignesDatas;
    @Override
    public int getRowCount() {
        return lignesDatas.length;
    }

    @Override
    public int getColumnCount() {
        return nomsColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return lignesDatas[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return nomsColonnes[column];
    }

    public void loadDatasAgents(ArrayList<Agent> lesAgents) {
        nomsColonnes = new String[]{"Numéro", "Nom"};
        lignesDatas = new Object[lesAgents.size()][3];
        int i = 0;
        for (Agent agt : lesAgents) {
            lignesDatas[i][0] = agt.getIdAgent();
            lignesDatas[i][1] = agt.getNomAgent();
            i++;
        }
        fireTableChanged(null);
    }

    public void loadDatasClients(ArrayList<Client> lesClients) {
        nomsColonnes = new String[]{"Numéro", "Nom"};
        lignesDatas = new Object[lesClients.size()][3];
        int i = 0;
        for (Client clt : lesClients) {
            lignesDatas[i][0] = clt.getIdClient();
            lignesDatas[i][1] = clt.getNomClient();
            i++;
        }
        fireTableChanged(null);
    }

    public void loadDatasReleves(ArrayList<Releve> lesReleves) {
        nomsColonnes = new String[]{"Date", "Valeur"};
        lignesDatas = new Object[lesReleves.size()][3];
        int i = 0;
        for (Releve rel : lesReleves) {
            lignesDatas[i][0] = rel.getDateReleve();
            lignesDatas[i][1] = rel.getValeurReleve();
            i++;
        }
        fireTableChanged(null);
    }
}
