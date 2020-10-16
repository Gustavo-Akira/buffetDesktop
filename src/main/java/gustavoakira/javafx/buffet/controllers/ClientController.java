package gustavoakira.javafx.buffet.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import gustavoakira.javafx.buffet.dao.ClientDao;
import gustavoakira.javafx.buffet.dao.ClientDaoImpl;
import gustavoakira.javafx.buffet.model.Client;
import gustavoakira.javafx.buffet.model.ClientTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientController implements Initializable{
	
	
	@FXML
	private TableView<ClientTable> clientTable;
	
	@FXML
	private TableColumn<ClientTable, Long> tcid;
	
	@FXML
	private TableColumn<ClientTable, String> tcfirstNameColumn;
	
	@FXML
	private TableColumn<ClientTable, String> tclastNameColumn;
	
	@FXML
	private TableColumn<ClientTable, LocalDate> tcdayOfEnroll;
	
	@FXML
	private TableColumn<ClientTable, LocalDate> tcbirthday;
	
	@FXML
	private TableColumn<ClientTable, String> tctelephone;
	
	
	private ClientDao clientdao = new ClientDaoImpl();
	
	private List<Client> clients = clientdao.getAll();
	
	private ObservableList<ClientTable> list = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listClients();
	}
	
	@FXML
	public void addClient(ActionEvent event) throws IOException{
		
		Parent parent = FXMLLoader.load(getClass().getResource("../views/ClientAdd.fxml"));
		Scene scene = new Scene(parent);
		Stage cadastro = new Stage();
		cadastro.setScene(scene);
		cadastro.initModality(Modality.WINDOW_MODAL);
		cadastro.initOwner(((Node) event.getSource()).getScene().getWindow());
		cadastro.showAndWait();
	}
	
	public void listClients() {
		if(!list.isEmpty()) {
			list.clear();
		}
		
		for(Client client: clients) {
			ClientTable clientTable = new ClientTable(client.getId(), client.getFirstName(), client.getLastName(), client.getDayOfEnroll(), client.getTelephone(),client.getBirthday());
			list.add(clientTable);
		}
		
		tcid.setCellValueFactory(new PropertyValueFactory<ClientTable, Long>("Id"));
		tcfirstNameColumn.setCellValueFactory(new PropertyValueFactory<ClientTable, String>("FirstName"));
		tclastNameColumn.setCellValueFactory(new PropertyValueFactory<ClientTable, String>("LastName"));
		tcdayOfEnroll.setCellValueFactory(new PropertyValueFactory<ClientTable, LocalDate>("DayOfEnroll"));
		tcbirthday.setCellValueFactory(new PropertyValueFactory<ClientTable, LocalDate>("Birthday"));
		tctelephone.setCellValueFactory(new PropertyValueFactory<ClientTable, String>("Telephone"));
		
		clientTable.setItems(list);
	}
}
