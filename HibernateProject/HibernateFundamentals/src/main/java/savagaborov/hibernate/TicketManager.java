package savagaborov.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class TicketManager {
	
	protected SessionFactory sessionFactory;
	
	protected void setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures																					// hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	protected void exit() {
		sessionFactory.close();
	}
	
	//create operation
	protected void create() {
		
		Ticket ticket = new Ticket();
		
		ticket.setEvent("Venice Biennial");
		ticket.setType("Art festival");
		ticket.setPrice(120.22f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(ticket);

		session.getTransaction().commit();
		session.close();
	}
	
	// read operation
	protected void read() {

		Session session = sessionFactory.openSession();
		
		long ticketId = 1;
		Ticket ticket = session.get(Ticket.class, ticketId); // prvi argument je klasa,
															// a drugi je ID objekta
		if (ticket != null) {
			System.out.println("Event " + ticket.getEvent());
			System.out.println("Type " + ticket.getType());
			System.out.println("Price " + ticket.getPrice());
		} else {
			System.out.println("Ticket could not be found");
		}

		session.close();	
		}
	
	// update operation
	protected void update() {
		Ticket ticket = new Ticket();
		ticket.setId(8);
		ticket.setEvent("FIBA Eurobasket 2017");
		ticket.setType("Sport - basketball");
		ticket.setPrice(135.54f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(ticket);

		session.getTransaction().commit();
		session.close();
		}
	
	// delete operation
	protected void delete() {
			
		Ticket ticket = new Ticket();
			
		ticket.setId(9);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(ticket);

		session.getTransaction().commit();
		session.close();
		}


	public static void main(String[] args) {
		
		TicketManager manager = new TicketManager();
		
		manager.setup();
		
		//manager.create();
		//manager.read();
		//manager.update();
		//manager.delete();
		
		manager.exit();

	}

}
