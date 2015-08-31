package address.view;

import address.model.Person;
import address.util.DateUtil;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {

	@FXML
	private TextField firstNameField;	
	@FXML
	private TextField lastNameField;	
	@FXML
	private TextField streetField;	
	@FXML
	private TextField postalCodeField;	
	@FXML
	private TextField cityField;	
	@FXML
	private TextField birthdayField;
	
	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setPerson(Person person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName().get());
		lastNameField.setText(person.getLastName().get());
		streetField.setText(person.getStreet().get());
		postalCodeField.setText(String.valueOf(person.getPostalCode().get()));
		cityField.setText(person.getCity().get());
		birthdayField.setText(DateUtil.format(person.getBirthday().get()));
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	public void handledOk() {
		if (isInputValid()) {
			person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
		}
	}
	
	@FXML
	public void handledCancel() {
		dialogStage.close();
	}
	
	/**
     * Valida a entrada do usu�rio nos campos de texto.
     * 
     * @return true se a entrada � v�lida
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Nome inv�lido!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Sobrenome inv�lido!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "Rua inv�lida!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "C�digo Postal inv�lido!\n"; 
        } else {
            // tenta converter o c�digo postal em um int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "C�digo Postal inv�lido (deve ser um inteiro)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "Cidade inv�lida!\n"; 
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Anivers�rio inv�lido!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "Anivers�rio inv�lido. Use o formato dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            Alert alertEditPerson = new Alert(AlertType.WARNING);
            alertEditPerson.setTitle("Campos Inv�lidos");
            alertEditPerson.setHeaderText("Por favor, corrija os campos inv�lidos!");
            alertEditPerson.setContentText(errorMessage);
            
            alertEditPerson.showAndWait();
            return false;
        }
    }
	
}
