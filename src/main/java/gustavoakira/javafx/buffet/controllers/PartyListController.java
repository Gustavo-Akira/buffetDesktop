package gustavoakira.javafx.buffet.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import gustavoakira.javafx.buffet.dao.PartyDao;
import gustavoakira.javafx.buffet.dao.PartyDaoImpl;
import gustavoakira.javafx.buffet.model.PartyTable;
import gustavoakira.javafx.buffet.model.Party;
import gustavoakira.javafx.buffet.model.PartyTable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PartyListController implements Initializable {
	
	@FXML
	private TableView<PartyTable> partyTable;
	
	@FXML
	private TableColumn<PartyTable, Long> tcId;
	
	@FXML
	private TableColumn<PartyTable, String> tcAddress;
	
	@FXML
	private TableColumn<PartyTable, String> tcTheme;
	
	@FXML
	private TableColumn<PartyTable, Date> tcDate;
	
	@FXML
	private TableColumn<PartyTable, Time> tcInitHour;
	
	@FXML
	private TableColumn<PartyTable, Time> tcFinalHour;
	
	@FXML
	private TableColumn<PartyTable, String> tcClientName;
	
	@FXML
	private TableColumn<PartyTable, PartyTable> tcEdit;
	
	@FXML
	private TableColumn<PartyTable, PartyTable> tcRemove;
	
	private PartyDao partyDao = new PartyDaoImpl();
	
	private List<Party> parties = new ArrayList<Party>();
	
	private ObservableList<PartyTable> list = FXCollections.observableArrayList();
	
	@FXML
	public void newAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PartyAdd.fxml"));
		Parent parent = loader.load();
		PartyAddController controller = loader.getController();
		controller.loadObjects();
		Stage d = new Stage();
		d.setScene(new Scene(parent));
		d.initModality(Modality.WINDOW_MODAL);
		d.initOwner(((Node) event.getSource()).getScene().getWindow());
		d.showAndWait();
		listPartys();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		listPartys();
	}
	
	private void removeParty(PartyTable party) {
		this.partyDao.removeParty(party.getId());
		listPartys();
	}
	
	private void listPartys() {
		parties = partyDao.getAll();
		if(!list.isEmpty()) {
			list.clear();
		}
		for(Party party : parties) {
			PartyTable partyTable = new PartyTable(party.getTheme(), party.getAddress(), party.getDate(), party.getInitHour(), party.getFinalHour(), party.getId(), party.getClient());
			list.add(partyTable);
		}
		
		tcId.setCellValueFactory(new PropertyValueFactory<PartyTable, Long>("Id"));
		tcTheme.setCellValueFactory(new PropertyValueFactory<PartyTable, String>("Theme"));
		tcAddress.setCellValueFactory(new PropertyValueFactory<PartyTable, String>("Address"));
		tcDate.setCellValueFactory(new PropertyValueFactory<PartyTable, Date>("Date"));
		tcInitHour.setCellValueFactory(new PropertyValueFactory<PartyTable, Time>("InitHour"));
		tcFinalHour.setCellValueFactory(new PropertyValueFactory<PartyTable, Time>("FinalHour"));
		tcClientName.setCellValueFactory(new PropertyValueFactory<PartyTable, String>("client"));
		tcRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<PartyTable>(param.getValue()));
		tcRemove.setCellFactory(param -> new TableCell<PartyTable, PartyTable>() {
			private final Button button = new Button();

			@Override
			protected void updateItem(PartyTable obj, boolean empty) {
				super.updateItem(obj, empty);

				if (obj == null) {
					setGraphic(null);
					return;
				}
				button.setText("Remove");
				setGraphic(button);
				button.setOnAction(event->removeParty(obj));
			}
		});
		tcEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tcEdit.setCellFactory(param -> new TableCell<PartyTable, PartyTable>() {
			private final Button button = new Button();

			@Override
			protected void updateItem(PartyTable obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				button.setText("Edit");
				setGraphic(button);
				button.setOnAction(event->{
					try {
						editParty(obj);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			}
		});
		partyTable.setItems(list);
	}
	private void editParty(PartyTable party) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PartyAdd.fxml"));
		Parent parent = loader.load();
		PartyAddController controller = loader.getController();
		controller.loadObjects();
		Party obj = new Party();
		controller.setParty(party);
		Stage d = new Stage();
		d.setScene(new Scene(parent));
		d.initModality(Modality.WINDOW_MODAL);
		d.initOwner(partyTable.getScene().getWindow());
		d.showAndWait();
		listPartys();
	}
}
