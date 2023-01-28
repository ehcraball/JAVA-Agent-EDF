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

    //déclaration des Objets
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

    ModelJTable mdlAgents ;
    ModelJTable mdlClients;
    ModelJTable mdlReleves;

    Agent agtSelectionne;

    Client cltSelectionne;
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


        //Jeu d'essais
        remplirData();

        mdlAgents = new ModelJTable();

        mdlAgents.loadDataAgent(mesAgents);

        tblAgents.setModel(mdlAgents);




        tblAgents.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int idAgent = Integer.parseInt( tblAgents.getValueAt(tblAgents.getSelectedRow(),0).toString());

                mdlClients = new ModelJTable();

                for (Agent agt:mesAgents) {
                    if (agt.getIdAgent()== idAgent){
                        mdlClients.LoadDatasClients(agt.getLesClients());
                        agtSelectionne = agt;
                        break;
                    }
                }

                tblClients.setModel(mdlClients);

            }
        });

        tblClients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int conso = 0;
                int idClient = Integer.parseInt(tblClients.getValueAt(tblClients.getSelectedRow(), 0).toString());
                for (Client clt : agtSelectionne.getLesClients()) {
                    if (clt.getIdClient() == idClient) {
                        mdlReleves = new ModelJTable();
                        mdlReleves.LoadDatasReleve(clt.getLesReleves());
                        tblReleves.setModel(mdlReleves);
                        cltSelectionne = clt;

                        /*if (clt.getLesReleves().size() == 1){
                            conso = clt.getLesReleves().get(0).getValeurReleve();

                        }
                        else if (clt.getLesReleves().size() > 1){
                            conso = clt.getLesReleves().get(clt.getLesReleves().size()-1).getValeurReleve() - clt.getLesReleves().get(clt.getLesReleves().size()-2).getValeurReleve();
                        }*/
                        txtConsommation.setText(String.valueOf(clt.calculerConso()));
                        break;
                    }

                }
            }

        });

        btnInserer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tblClients.getSelectedRow() == 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un client.", "Choix du client", JOptionPane.WARNING_MESSAGE);
                } else if (txtNouveauReleve.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir un nouveau relevé.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                } else if (dteReleve.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Veuillez choisir une date.", "Choix de la date", JOptionPane.WARNING_MESSAGE);
                } else if (cltSelectionne.getLesReleves().size() == 1) {
                    if (Integer.parseInt(txtNouveauReleve.getText()) < cltSelectionne.getLesReleves().get(0).getValeurReleve()) {
                        JOptionPane.showMessageDialog(null, "La nouvelle valeur ne peut pas être inférieur au dernier relevé", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);

                    }
                    else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String uneDate = sdf.format(dteReleve.getDate());
                        Releve nouveauReleve = new Releve(uneDate, Integer.parseInt(txtNouveauReleve.getText()));
                        cltSelectionne.AjouterUnReleve(nouveauReleve);

                        txtNouveauReleve.setText("");
                        txtConsommation.setText(String.valueOf(cltSelectionne.calculerConso()));
                        mdlReleves = new ModelJTable();
                        mdlReleves.LoadDatasReleve(cltSelectionne.getLesReleves());
                        tblReleves.setModel(mdlReleves);


                    }
                } else if (cltSelectionne.getLesReleves().size() > 1) {
                    if (Integer.parseInt(txtNouveauReleve.getText()) < cltSelectionne.getLesReleves().get(cltSelectionne.getLesReleves().size() - 1).getValeurReleve()) {
                        JOptionPane.showMessageDialog(null, "La nouvelle valeur ne peut pas être inférieur au dernier relevé", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);

                    }
                    else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String uneDate = sdf.format(dteReleve.getDate());
                        Releve nouveauReleve = new Releve(uneDate, Integer.parseInt(txtNouveauReleve.getText()));
                        cltSelectionne.AjouterUnReleve(nouveauReleve);

                        txtNouveauReleve.setText("");
                        txtConsommation.setText(String.valueOf(cltSelectionne.calculerConso()));
                        mdlReleves = new ModelJTable();
                        mdlReleves.LoadDatasReleve(cltSelectionne.getLesReleves());
                        tblReleves.setModel(mdlReleves);


                    }

                }
            }
        });
    }

    public void remplirData(){
        Agent agt1 = new Agent(1, "Erol");
        Agent agt2 = new Agent(2, "Vincent");
        Agent agt3 = new Agent(3, "Wassim");

        Client clt1 = new Client(1, "Malonga");
        Client clt2 = new Client(2, "Lambert");
        Client clt3 = new Client(3, "Bacha");
        Client clt4 = new Client(4, "Pereira");
        Client clt5 = new Client(5, "Turpin");
        Client clt6 = new Client(6, "Ekitike");

        Releve rlv1 = new Releve("24/02/2022", 100);
        Releve rlv2 = new Releve("10/04/2022", 876);

        Releve rlv3 = new Releve("27/05/2022", 153);

        Releve rlv4 = new Releve("25/04/2022", 248);
        Releve rlv5 = new Releve("23/10/2022", 762);

        Releve rlv6 = new Releve("11/02/2022", 510);
        Releve rlv7 = new Releve("23/02/2022", 629);

        Releve rlv8 = new Releve("01/01/2022", 297);
        Releve rlv9 = new Releve("27/07/2022", 729);
        Releve rlv10 = new Releve("12/12/2022", 869);

        // Ajouter des relevés aux clients
        // clt1.getLesReleves().add(rlv1);
        // clt1.getLesReleves().add(rlv2);
        clt1.AjouterUnReleve(rlv1);
        clt1.AjouterUnReleve(rlv2);

        clt2.AjouterUnReleve(rlv3);

        clt3.AjouterUnReleve(rlv4);
        clt3.AjouterUnReleve(rlv5);

        clt4.AjouterUnReleve(rlv6);
        clt4.AjouterUnReleve(rlv7);

        clt5.AjouterUnReleve(rlv8);
        clt5.AjouterUnReleve(rlv9);
        clt5.AjouterUnReleve(rlv10);

        agt1.AjouterUnClient(clt1);
        agt1.AjouterUnClient(clt2);
        agt1.AjouterUnClient(clt3);

        agt2.AjouterUnClient(clt4);

        agt3.AjouterUnClient(clt5);
        agt3.AjouterUnClient(clt6);


        mesAgents.add(agt1);
        mesAgents.add(agt2);
        mesAgents.add(agt3);
    }

}
