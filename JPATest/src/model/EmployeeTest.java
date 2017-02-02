package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class EmployeeTest {

	public static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	
	public Employee1Id test1insert() {
		EntityManagerFactory emf;
		EntityManager em;

		Employee1Id id = new Employee1Id();
		id.setFirstName("John");
		id.setLastName(format.format(new Date()));
		
		Employee1 employee = new Employee1();
		employee.setFirstName(id.getFirstName());
		employee.setLastName(id.getLastName());
		employee.setEmail("abc@a.b.c");
		
		emf = Persistence.createEntityManagerFactory("CoherenceHotCache");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
		em.close();
		
		return id;
	}

	public Employee1 test1select(Employee1Id id) {
		EntityManagerFactory emf;
		EntityManager em;

		emf = Persistence.createEntityManagerFactory("CoherenceHotCache");
		em = emf.createEntityManager();
		
		Employee1 employee = em.find(Employee1.class, id);
		System.out.println(employee);
		
		em.close();
		
		return employee;
	}
	
	public Employee2Id test2insert() {
		EntityManagerFactory emf;
		EntityManager em;

		Employee2Id id = new Employee2Id();
		id.setFirstName("John");
		id.setLastName(format.format(new Date()));
		
		Employee2 employee = new Employee2();
		employee.setId(id);
		employee.setEmail("abc@a.b.c");
		
		emf = Persistence.createEntityManagerFactory("CoherenceHotCache");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
		em.close();
		return id;
	}
	
	public Employee2 test2select(Employee2Id id) {
		EntityManagerFactory emf;
		EntityManager em;

		emf = Persistence.createEntityManagerFactory("CoherenceHotCache");
		em = emf.createEntityManager();
		
		Employee2 employee = em.find(Employee2.class, id);
		System.out.println(employee);
		
		em.close();
		
		return employee;
	}

	public static void main(String args[]) {
		
		// TEST 1
		EmployeeTest test = new EmployeeTest();
		System.out.println("---- db insert #1 ----");
		Employee1Id id1 = test.test1insert();
		System.out.println("---- db select #1 ----");
		Employee1 employee1 = test.test1select(id1);
		
		NamedCache cache1 = CacheFactory.getCache("cache1");
		System.out.println("---- coherence put #1 ----");
		cache1.put(id1, employee1);
		System.out.println("---- coherence get #1 ----");
		System.out.println(cache1.get(id1));		
		
		// TEST 2
		System.out.println("---- db insert #2 ----");
		Employee2Id id2 = test.test2insert();
		System.out.println("---- db select #2 ----");
		Employee2 employee2 = test.test2select(id2);

		NamedCache cache2 = CacheFactory.getCache("cache2");
		System.out.println("---- coherence put #2 ----");
		cache2.put(id2, employee2);
		System.out.println("---- coherence get #2 ----");
		System.out.println(cache2.get(id2));		
	}
}
