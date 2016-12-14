package astrolabe31.runningfx.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class FxEntityManager {
	 private static FxEntityManager instance;

	 private static final String PERSISTENCE_UNIT_NAME = "MyAppfxPU";
	 private EntityManagerFactory emf;
	 private EntityManager em;

	    private FxEntityManager() {
	    	
	    	 System.out.println("***** FxEntityManager ********");
	    	//HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
	        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	        //emf = provider.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, null);
	        em = emf.createEntityManager();
	        
	        EntityTransaction tx =  em.getTransaction();

	        tx.begin();
	        System.out.println("***** begin ********");
	       // em.close();
	        //emf.close();
	        
	        
	        System.out.println("FxEntityManager is open ");
	    }

	    public static synchronized FxEntityManager getInstance() {
	        if (instance == null) {
	            instance = new FxEntityManager();
	        }

	        return instance;
	    }

	    public EntityManager getEntityManager() {
	        return em;
	    }
}
