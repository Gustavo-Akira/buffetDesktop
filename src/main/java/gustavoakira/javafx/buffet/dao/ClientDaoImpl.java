package gustavoakira.javafx.buffet.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import gustavoakira.javafx.buffet.model.Client;
import gustavoakira.javafx.buffet.util.HibernateUtil;

public class ClientDaoImpl implements ClientDao {

	@Override
	public void add(Client client) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(client);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public Client getOne(Long id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Client client = (Client) s.createQuery("from Client as client where client.id ="+id).getSingleResult();
		s.getTransaction().commit();
		s.close();
		return client;
	}

	@Override
	public List<Client> getAll() {
		Session s = HibernateUtil.getSessionFactory().openSession();
		List<Client> clients = new ArrayList<Client>();
		s.beginTransaction();
		clients = s.createQuery("from Client").getResultList();
		s.getTransaction().commit();
		s.close();
		return clients;
	}

	@Override
	public void remove(Long id) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Client client = s.load(Client.class, id);
		s.delete(client);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public Client update(Client client) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.saveOrUpdate(client);
		s.getTransaction().commit();
		s.close();
		return null;
	}

}
