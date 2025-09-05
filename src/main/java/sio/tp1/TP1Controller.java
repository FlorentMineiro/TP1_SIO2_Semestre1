package sio.tp1;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sio.tp1.Model.Message;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TP1Controller implements Initializable {

    @FXML
    private Button cmdEnvoyer;
    @FXML
    private Button cmdRecevoir;
    @FXML
    private AnchorPane apEnvoyer;
    @FXML
    private AnchorPane apRecevoir;
    @FXML
    private Label lblTitre;
    @FXML
    private ListView lstExpediteurs;
    @FXML
    private ListView lstDestinataires;
    @FXML
    private TextField txtMessage;
    @FXML
    private Button cmdEnvoyerMessage;

    private HashMap<String, ArrayList<Message>> maMessagerie;
    @FXML
    private ComboBox cboDestinataires;
    @FXML
    private TreeView tvMessages;

    @FXML
    public void menuClicked(Event event) {
        if(event.getSource() == cmdEnvoyer)
        {
            lblTitre.setText("TP1 : Messagerie / Envoyer");
            apEnvoyer.toFront();
        }
        else//if(event.getSource() == cmdRecevoir)
        {
            lblTitre.setText("TP1 : Messagerie / Recevoir");
            apRecevoir.toFront();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTitre.setText("TP1 : Messagerie / Envoyer");
        apEnvoyer.toFront();
        lstExpediteurs.getItems().addAll("Enzo","Noa","Lilou","Milo");
        lstDestinataires.getItems().addAll("Enzo","Noa","Lilou","Milo");
        cboDestinataires.getItems().addAll("Enzo","Noa","Lilou","Milo");
        cboDestinataires.getSelectionModel().selectFirst();
        maMessagerie = new HashMap<>();
    }

    @FXML
    public void cmdEnvoyerMessageClicked(Event event)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Erreur !");
        a.setHeaderText("azerty");
       if(lstExpediteurs.getSelectionModel().getSelectedItems() == null){


           a.setContentText("Veuillez selectionner l'expediteur");
           a.showAndWait();
       } else if (txtMessage.getText().compareTo("") == 0) {
           a.setContentText("Veuillez ecrire un message");
           a.showAndWait();


       } else if (lstDestinataires.getSelectionModel().getSelectedItems() == null) {


               a.setContentText("Veuillez selectionner le destinataire");
               a.showAndWait();


       } else {
           if (maMessagerie.containsKey(lstDestinataires.getSelectionModel().getSelectedItems().toString()))
           {
               //La clé n'est pas contenu dans notre sélection
               //Pas de message pour le destinataire
               ArrayList<Message> messages = new ArrayList<>();
               maMessagerie.put(lstDestinataires.getSelectionModel().getSelectedItem().toString(), messages);
               Message monMessage = new Message(
                       lstExpediteurs.getSelectionModel().getSelectedItem().toString(),
                       lstDestinataires.getSelectionModel().getSelectedItem().toString(),
                       txtMessage.getText()
               );
               maMessagerie.get(lstExpediteurs.getSelectionModel().getSelectedItem().toString()).add(monMessage);
           }

       }


    }

    @FXML
    public void cboDestinatairesClicked(Event event) {
        TreeItem racine = new TreeItem("Tous les messages");

        // Parcourir la liste des messages du destinataire choisi dans la ComboBox
        int nbMessages = 1 ;
        for (Message message : maMessagerie.get(lstDestinataires.getSelectionModel().getSelectedItem().toString()))
        {
            TreeItem noeudMessage = new TreeItem("Message n°" + nbMessages);
            TreeItem noeudDe = new TreeItem(message.getExpediteur());
            TreeItem noeudContenu = new TreeItem<>(message.getContenuDuMessage());
            noeudMessage.getChildren().add(noeudDe);
            noeudMessage.getChildren().add(noeudContenu);
            noeudMessage.setExpanded(true);
            racine.getChildren().add(noeudMessage);
        }



    }
}