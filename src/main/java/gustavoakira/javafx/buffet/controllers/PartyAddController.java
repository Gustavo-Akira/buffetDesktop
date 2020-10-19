package gustavoakira.javafx.buffet.controllers;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTimePicker;

import gustavoakira.javafx.buffet.dao.ClientDao;
import gustavoakira.javafx.buffet.dao.ClientDaoImpl;
import gustavoakira.javafx.buffet.dao.PartyDao;
import gustavoakira.javafx.buffet.dao.PartyDaoImpl;
import gustavoakira.javafx.buffet.model.Client;
import gustavoakira.javafx.buffet.model.Party;
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
	private Party party = null;
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
		if(party == null) {
			party = new Party();
			party.setAddress(this.address.getText());
			party.setClient(clientBox.getValue());
			party.setTheme(theme.getText());
			party.setDate(date.getValue());
			party.setInitHour(Time.valueOf(initHour.getValue()));
			party.setFinalHour(Time.valueOf(endHour.getValue()));
			this.partyDao.add(party);
		}
		close();
	}
	@FXML
	public void close() {
		Stage stage = (Stage) address.getScene().getWindow();
		stage.close();
	}
}
