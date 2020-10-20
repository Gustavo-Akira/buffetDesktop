package gustavoakira.javafx.buffet.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gustavoakira.javafx.buffet.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class RootController implements Initializable {
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void goToParty() {
		App.setStage("views/PartyList");
	}
	
	@FXML
	public void goToClients() {
		App.setStage("views/ClientList");
	}
}
