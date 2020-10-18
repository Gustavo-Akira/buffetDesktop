package gustavoakira.javafx.buffet.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import gustavoakira.javafx.buffet.model.Party;
import gustavoakira.javafx.buffet.util.HibernateUtil;

public class PartyDaoImpl implements PartyDao {

	@Override
	public List<Party> getAll() {
		List<Party> list = new ArrayList<Party>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		list = s.createQuery("from Party").getResultList();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@Override
	public Party getOne(Long id) {
		Party party = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		party =(Party) s.createQuery("from Party party where party.id="+id).getSingleResult();
		s.getTransaction().commit();
		s.close();
		return party;
	}

	@Override
	public void updateParty(Party party) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(party);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void add(Party party) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(party);
		s.getTransaction().commit();
		s.close();
	}

}
