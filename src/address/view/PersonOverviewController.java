package address.view;


import address.MainApp;
import address.model.Person;
import address.util.DateUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class PersonOverviewController {

	@FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    
    private MainApp mainApp;

    /**
     * O construtor.
     * O construtor é chamado antes do método inicialize().
     */
    public PersonOverviewController() {
    	
    }

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        // Inicializa a tabela de pessoa com duas colunas.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
        
       showPersonDetails(null);
       
       personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
       @Override
		   public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
	    	   // TODO Auto-generated method stub
    	   		showPersonDetails(newValue);
		   }
       });
    }

    /**
     * É chamado pela aplicação principal para dar uma referência de volta a si mesmo.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Adiciona os dados da observable list na tabela
        personTable.setItems(mainApp.getPersonData());
    }
    
    public void showPersonDetails(Person person) {
    	if (person != null) {
    		firstNameLabel.setText(person.getFirstName().get());
    		lastNameLabel.setText(person.getLastName().get());
    		streetLabel.setText(person.getStreet().get());
    		postalCodeLabel.setText(String.valueOf(person.getPostalCode().get()));
    		cityLabel.setText(person.getCity().get());
    		birthdayLabel.setText(DateUtil.format(person.getBirthday().get()));
    	} else {
    		firstNameLabel.setText("");
    		lastNameLabel.setText("");
    		streetLabel.setText("");
    		postalCodeLabel.setText("");
    		cityLabel.setText("");
    		birthdayLabel.setText("");
    	}
    }
    
    @FXML
    public void handleDeletePerson() {
    	int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    	
    	if (selectedIndex != -1)     	
    		personTable.getItems().remove(selectedIndex);
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Nenhuma seleção");
    		alert.setHeaderText("Nenhuma pessoa selecionada!");
    		alert.setContentText("Por favor, selecione uma pessoa na tabela.");
    		
    		alert.showAndWait();
    	}
    }
    
    @FXML
    private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialgo(tempPerson);
		
		if (okClicked)
			mainApp.getPersonData().add(tempPerson);
	}
    
    @FXML
    private void handleEditPerson() {
    	Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
    	if (selectedPerson != null) {
    		boolean okClicked = mainApp.showPersonEditDialgo(selectedPerson);
    		
    		if (okClicked)
    			showPersonDetails(selectedPerson);
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Nenhuma seleção");
    		alert.setHeaderText("Nenhuma Pessoa Selecionada");
    		alert.setContentText("Por favor, selecione uma pessoa na tabela.");
    		
    		alert.showAndWait();
    	}
    }
	
	
}
