package gustavoakira.javafx.buffet.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gustavoakira.javafx.buffet.dao.ClientDao;
import gustavoakira.javafx.buffet.dao.ClientDaoImpl;
import gustavoakira.javafx.buffet.model.Client;
import gustavoakira.javafx.buffet.model.ClientTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientAddController implements Initializable {
	private ClientTable client = null;

	private ClientDao clientDao = new ClientDaoImpl();
	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private DatePicker birthday;

	@FXML
	private DatePicker dayOfEnroll;

	@FXML
	private TextField telephone;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	public void saveClient(ActionEvent event) {
		try {
			Client client = new Client();
			if (this.client != null) {
				client.setId(this.client.getId());
			}
			client.setFirstName(firstName.getText());
			client.setLastName(lastName.getText());
			client.setBirthday(birthday.getValue());
			client.setDayOfEnroll(dayOfEnroll.getValue());
			client.setTelephone(telephone.getText());
			if (client.getId() == null) {
				clientDao.add(client);
			} else {
				clientDao.update(client);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		close();
	}

	@FXML
	public void close() {
		Stage stage = (Stage) firstName.getScene().getWindow();
		stage.close();
	}

	public void setClient(ClientTable client) {
		this.client = client;
		this.firstName.setText(client.getFirstName());
		this.lastName.setText(client.getLastName());
		this.birthday.setValue(client.getBirthday());
		this.dayOfEnroll.setValue(client.getDayOfEnroll());
		this.telephone.setText(client.getTelephone());
	}
}
