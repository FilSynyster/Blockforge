package agenda.app.view;

import agenda.app.models.Contact;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class ContactCell extends ListCell<Contact> {

    private final Label nameLabel = new Label();
    private final Label emailLabel = new Label();
    private final Label phoneLabel = new Label();

    private final VBox container = new VBox(3);

    public ContactCell() {

        nameLabel.getStyleClass().add("contact-name");
        emailLabel.getStyleClass().add("contact-email");
        phoneLabel.getStyleClass().add("contact-phone");

        container.setAlignment(Pos.CENTER_LEFT);

        container.getChildren().addAll(
                nameLabel,
                emailLabel,
                phoneLabel
        );

        container.getStyleClass().add("contact-cell");
    }

    @Override
    protected void updateItem(Contact contact, boolean empty) {

        super.updateItem(contact, empty);

        if (empty || contact == null) {

            setGraphic(null);
            setText(null);

        } else {

            nameLabel.setText(contact.getName());
            emailLabel.setText(contact.getEmail());
            phoneLabel.setText(contact.getPhone());

            setGraphic(container);
            setText(null);
        }
    }
}