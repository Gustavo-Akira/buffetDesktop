package gustavoakira.javafx.buffet.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClientTable {
	private final SimpleLongProperty id;
	private final SimpleStringProperty firstName;
	private final SimpleStringProperty lastName;
	private final ObjectProperty<LocalDate> dayOfEnroll;
	private final SimpleStringProperty telephone;
	private final ObjectProperty<LocalDate> birthday;
	
	public ClientTable(Long id,String firstName, String lastName, LocalDate dayOfEnroll, String telephone, LocalDate birthday) {
		super();
		this.id = new SimpleLongProperty(id);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.dayOfEnroll = new SimpleObjectProperty<LocalDate>(dayOfEnroll);
		this.telephone = new SimpleStringProperty(telephone);
		this.birthday = new SimpleObjectProperty<LocalDate>(birthday);
	}
	
	public Long getId() {
		return id.get();
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public LocalDate getDayOfEnroll() {
		return dayOfEnroll.get();
	}
	
	public String getTelephone() {
		return telephone.get();
	}
	
	public LocalDate getBirthday() {
		return birthday.get();
	}
}
