package gustavoakira.javafx.buffet.model;

import java.sql.Time;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class PartyTable {
	private final SimpleStringProperty theme;
	private final SimpleStringProperty address;
	private final ObjectProperty<LocalDate> date;
	private final ObjectProperty<Time> initHour;
	private final ObjectProperty<Time> finalHour;
	private final SimpleLongProperty id;
	
	public PartyTable(String theme, String address, 
			LocalDate date, Time initHour, Time finalHour, Long id) {
		this.theme = new SimpleStringProperty(theme);
		this.address = new SimpleStringProperty(address);
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.initHour = new SimpleObjectProperty<Time>(initHour);
		this.finalHour = new SimpleObjectProperty<Time>(finalHour);
		this.id = new SimpleLongProperty(id);
	}
	
	public String getTheme() {
		return this.theme.get();
	}
	
	public String getAddress() {
		return this.address.get();
	}
	
	public LocalDate getDate() {
		return this.date.get();
	}
	
	public Time getInitHour() {
		return this.initHour.get();
	}
	
	public Time getFinalHour() {
		return this.finalHour.get();
	}
	
	public Long getId() {
		return this.id.get();
	}
}
