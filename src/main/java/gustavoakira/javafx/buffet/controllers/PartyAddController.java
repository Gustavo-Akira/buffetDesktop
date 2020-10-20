package gustavoakira.javafx.buffet.controllers;

import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTimePicker;

import gustavoakira.javafx.buffet.dao.ClientDao;
import gustavoakira.javafx.buffet.dao.ClientDaoImpl;
import gustavoakira.javafx.buffet.dao.PartyDao;
import gustavoakira.javafx.buffet.dao.PartyDaoImpl;
import gustavoakira.javafx.buffet.model.Client;
import gustavoakira.javafx.buffet.model.Party;
import gustavoakira.javafx.buffet.model.PartyTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PartyAddController implements Initializable {
	private PartyTable party = null;
	private ObservableList<Client> obs;
	private ClientDao clientDao = new ClientDaoImpl();
	private PartyDao partyDao = new PartyDaoImpl();
	@FXML
	private  JFXTimePicker initHour;
	
	@FXML
	private JFXTimePicker endHour;
	
	@FXML
	private TextField theme;
	
	@FXML
	private TextField address;
	
	@FXML
	private ComboBox<Client> clientBox;
	
	@FXML
	private DatePicker date;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadObjects();
	}
	
	private void initComboBox() {
		Callback<ListView<Client>, ListCell<Client>> factory = lv-> new ListCell<Client>() {
			@Override
			protected void updateItem(Client item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getFirstName());
				setTextFill(getTextFill());
			};
		};
		clientBox.setCellFactory(factory);
		clientBox.setButtonCell(factory.call(null));
	}
	
	public void loadObjects() {
		obs = FXCollections.observableArrayList(this.clientDao.getAll()); 
		clientBox.setItems(obs);
		initComboBox();
	}
	
	@FXML
	public void saveClient() {
		Party obj = new Party();
		obj.setAddress(this.address.getText());
		obj.setClient(clientBox.getValue());
		obj.setTheme(theme.getText());
		obj.setDate(date.getValue());
		obj.setInitHour(Time.valueOf(initHour.getValue()));
		obj.setFinalHour(Time.valueOf(endHour.getValue()));
		if(party == null) {
			this.partyDao.add(obj);
		}else {
			obj.setId(this.party.getId());
			this.partyDao.updateParty(obj);
		}
		close();
	}
	@FXML
	public void close() {
		Stage stage = (Stage) address.getScene().getWindow();
		stage.close();
	}
	
	public void setParty(PartyTable party) {
		this.party = party;
		setInputs();
	}
	
	private void setInputs() {
		this.address.setText(this.party.getAddress());
		this.clientBox.setValue(clientDao.getByLastName(this.party.getClient()));
		this.date.setValue(this.party.getDate());
		this.initHour.setValue(this.party.getInitHour().toLocalTime());
		this.endHour.setValue(this.party.getFinalHour().toLocalTime());
		this.theme.setText(this.party.getTheme());
	}
}
