package gustavoakira.javafx.buffet.dao;

import java.util.List;

import gustavoakira.javafx.buffet.model.Client;

public interface ClientDao {
	public void add(Client client);
	public Client getOne(Long id);
	public List<Client> getAll();
	public void remove(Long id);
	public Client update(Client client);
}
