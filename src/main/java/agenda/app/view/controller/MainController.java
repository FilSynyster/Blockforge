package agenda.app.view.controller;

import agenda.app.PersistenceHandler;
import agenda.database.annotations.AutoInject;
import agenda.app.models.Contact;
import agenda.app.repositories.ContactRepository;
import agenda.app.view.ContactCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainController {

    //Implementar injetor no core
    @AutoInject                             //Simula a injeção automática
    private ContactRepository repository = PersistenceHandler.getRepository(Contact.class);

    @FXML
    private ListView<Contact> listaContatos;

    @FXML
    private Label quantidadeDeContatos;

    private void refreshContactList() {
        List<Contact> contacts = repository.findAll();
        listaContatos.getItems().clear();
        listaContatos.getItems().addAll(contacts);
        quantidadeDeContatos.setText(String.valueOf(contacts.size()));
    }

    @FXML
    public void initialize(){

        listaContatos.setCellFactory(list ->
                new ContactCell()
        );

        refreshContactList();
    }



    @FXML
    public void openRegisterWindow(ActionEvent event){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Novo Contato");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
