package agenda.app.view.controller;

import agenda.database.DatabaseHandler;
import agenda.database.annotation.AutoInject;
import agenda.app.models.Contact;
import agenda.app.repositories.ContactRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController {

    //Implementar o injetor
    @AutoInject                                     //Simula a injeção automática
    private ContactRepository contactRepository = DatabaseHandler.getDatabaseManager().getRepository(Contact.class);

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

    @FXML
    public void saveContact(ActionEvent actionEvent) {

        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();

        Contact contact = new Contact(nome, email, telefone);

        //contactRepository.save(contact);

    }


}
