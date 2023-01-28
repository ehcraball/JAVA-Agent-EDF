package Vues;

import Entities.Agent;
import Entities.Client;
import Entities.Releve;
import Tools.ModelJTable;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FrmTP2POO extends JFrame{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JTable tblAgents;
    private JTable tblClients;
    private JTable tblReleves;
    private JLabel lblReleve;
    private JLabel lblClients;
    private JLabel lblAgents;
    private JLabel lblConsommation;
    private JTextField txtConsommation;
    private JTextField txtNouveauReleve;
    private JLabel lblNouveauReleve;
    private JLabel lblDate;
    private JPanel pnlDate;
    private JButton btnInserer;

    private JDateChooser dteReleve;

    ArrayList<Agent> mesAgents;

    ModelJTable dtmAgents;
    ModelJTable dtmClients;
    ModelJTable dtmReleves;

    Agent agentSelectionne;
    Client clientSelectionne;

    public FrmTP2POO()
    {
        this.setTitle("TP2 - POO");
        this.setContentPane(pnlRoot);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        // Gestion de la date dans le JPANEL
        dteReleve = new JDateChooser();
        dteReleve.setDateFormatString("yyyy-MM-dd");
        pnlDate.add(dteReleve);

        mesAgents = new ArrayList<>();

        RemplirCollection();
        dtmAgents = new ModelJTable();
        dtmAgents.loadDatasAgents(mesAgents);
        tblAgents.setModel(dtmAgents);
        tblAgents.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                dtmClients = new ModelJTable();
                int numAgent = Integer.parseInt(tblAgents.getValueAt(tblAgents.getSelectedRow(), 0).toString());

                for (Agent agt : mesAgents)
                {
                    if(agt.getIdAgent() == numAgent)
                    {
                        dtmClients.loadDatasClients(agt.getLesClients());
                        tblClients.setModel(dtmClients);
                        agentSelectionne = agt;
                        break;
                    }
                }
            }
        });
        tblClients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                dtmReleves = new ModelJTable();
                int numClient = Integer.parseInt(tblClients.getValueAt(tblClients.getSelectedRow(), 0).toString());

                for(Client clt : agentSelectionne.getLesClients())
                {
                    if(clt.getIdClient() == numClient)
                    {
                        dtmReleves.loadDatasReleves(clt.getLesReleves());
                        tblReleves.setModel(dtmReleves);

                        txtConsommation.setText(String.valueOf(clt.CalculerConsommation()));
                        clientSelectionne = clt;
                        break;
                    }
                }
            }
        });

        btnInserer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(tblClients.getSelectedRowCount()==0)
                {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un client","Choix du client",JOptionPane.WARNING_MESSAGE);
                }
                else if(txtNouveauReleve.getText().compareTo("")==0)
                {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir votre nouveau relevé","Erreur de saisie",JOptionPane.WARNING_MESSAGE);
                }
                else if(dteReleve.getDate() == null)
                {
                    JOptionPane.showMessageDialog(null, "Veuillez choisir une date","Choix de la date",JOptionPane.WARNING_MESSAGE);
                }
                else if(!clientSelectionne.VerifierValeurNouveauReleve(Integer.parseInt(txtNouveauReleve.getText())))
                {
                    JOptionPane.showMessageDialog(null, "La nouvelle valeur ne peut \npas être inférieure à la dernière","Erreur de saisie",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    // Tout est OK : donc on peut insérer le nouveau relevé

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(dteReleve.getDate());

                    Releve nouveauReleve = new Releve(date,Integer.parseInt(txtNouveauReleve.getText()));
                    clientSelectionne.AjouterUnReleve(nouveauReleve);

                    // On remet à jour l'interface graphique
                    // afin de visualiser l'insertion du nouveau relevé
                    // On efface le JTABLE des relevés

                    dtmReleves = new ModelJTable();
                    dtmReleves.loadDatasReleves(clientSelectionne.getLesReleves());
                    tblReleves.setModel(dtmReleves);

                    txtConsommation.setText(String.valueOf(clientSelectionne.CalculerConsommation()));
                }
            }
        });
    }

    public void RemplirCollection()
    {
        // JEU D'ESSAIS
        Agent agent1 = new Agent(1, "Enzo");
        Agent agent2 = new Agent(2, "Noa");
        Agent agent3 = new Agent(3, "Lilou");

        Client client1 = new Client(1, "Martin");
        Client client2 = new Client(2, "Alison");
        Client client3 = new Client(3, "Gand");
        Client client4 = new Client(4, "Muller");
        Client client5 = new Client(5, "Fortin");
        Client client6 = new Client(6, "Garnier");
        Client client7 = new Client(7, "Cousinot");

        Releve releve1 = new Releve("2021-02-14", 345);
        Releve releve2 = new Releve("2021-07-21", 987);
        Releve releve3 = new Releve("2021-11-05", 1117);
        Releve releve4 = new Releve("2021-08-15", 546);
        Releve releve5 = new Releve("2021-11-05", 261);
        Releve releve6 = new Releve("2022-01-03", 783);
        Releve releve7 = new Releve("2021-04-13", 904);
        Releve releve8 = new Releve("2021-09-03", 1283);
        Releve releve9 = new Releve("2021-12-23", 1846);
        Releve releve10 = new Releve("2021-02-05", 371);
        Releve releve11 = new Releve("2021-06-11", 613);
        Releve releve12 = new Releve("2021-10-23", 1071);
        Releve releve13 = new Releve("2022-01-03", 14);

        // On ajoute nos relevés à nos clients
        client1.AjouterUnReleve(releve1);
        client1.AjouterUnReleve(releve2);
        client1.AjouterUnReleve(releve3);

        client2.AjouterUnReleve(releve4);

        client3.AjouterUnReleve(releve5);
        client3.AjouterUnReleve(releve6);

        client4.AjouterUnReleve(releve7);
        client4.AjouterUnReleve(releve8);
        client4.AjouterUnReleve(releve9);

        client5.AjouterUnReleve(releve10);
        client5.AjouterUnReleve(releve11);
        client5.AjouterUnReleve(releve12);

        client6.AjouterUnReleve(releve13);

        // On ajoute nos clients à nos agents
        agent1.AjouterUnClient(client1);
        agent1.AjouterUnClient(client2);
        agent1.AjouterUnClient(client3);

        agent2.AjouterUnClient(client4);

        agent3.AjouterUnClient(client5);
        agent3.AjouterUnClient(client6);
        agent3.AjouterUnClient(client7);

        // Ajout des agents dans la collection
        mesAgents.add(agent1);mesAgents.add(agent2);mesAgents.add(agent3);
    }
}
