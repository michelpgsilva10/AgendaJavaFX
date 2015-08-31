package address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final IntegerProperty postalCode;
	private final StringProperty city;
	private final ObjectProperty<LocalDate> birthday;
	
	public Person() {
		this(null, null);
	}
	
	/**
     * Construtor com alguns dados iniciais.
     * 
     * @param firstName Primeiro nome da Pessoa.
     * @param lastName Sobrenome da Pessoa.
     */
    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // Alguns dados de exemplo, apenas para testes.
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

	public StringProperty getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street.set(street);
	}

	public IntegerProperty getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(Integer postalCode) {
		this.postalCode.set(postalCode.intValue());
	}

	public StringProperty getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city.set(city);
	}

	public ObjectProperty<LocalDate> getBirthday() {
		return birthday;
	}   
	
	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}
	
}
