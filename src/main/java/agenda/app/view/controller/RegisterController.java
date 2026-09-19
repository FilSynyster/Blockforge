package agenda.app.view.controller;

import agenda.app.PersistenceHandler;
import agenda.app.models.Contact;
import agenda.app.repositories.ContactRepository;
import agenda.database.annotations.AutoInject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController {

    //Implementar o injetor
    @AutoInject                                     //Simula a injeção automática
    private ContactRepository contactRepository = PersistenceHandler.getRepository(Contact.class);

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

        Contact contact = new Contact(nome, email, telefone, 0);

        contactRepository.create(contact);

    }


}
