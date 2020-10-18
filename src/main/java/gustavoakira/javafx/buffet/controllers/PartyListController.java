package gustavoakira.javafx.buffet.controllers;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import gustavoakira.javafx.buffet.dao.PartyDao;
import gustavoakira.javafx.buffet.dao.PartyDaoImpl;
import gustavoakira.javafx.buffet.model.Party;
import gustavoakira.javafx.buffet.model.PartyTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
	
	private PartyDao partyDao = new PartyDaoImpl();
	
	private List<Party> parties = new ArrayList<Party>();
	
	private ObservableList<PartyTable> list = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
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
		partyTable.setItems(list);
	}
}
