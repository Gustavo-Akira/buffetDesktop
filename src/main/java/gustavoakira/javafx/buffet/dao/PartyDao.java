package gustavoakira.javafx.buffet.dao;

import java.util.List;

import gustavoakira.javafx.buffet.model.Party;

public interface PartyDao {
	public List<Party> getAll();
	public Party getOne(Long id);
	public void updateParty(Party party);
	public void add(Party party);
}
