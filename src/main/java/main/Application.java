package main;
import dao.DaoManager;

public class Application {
	
	private static DaoManager daoManager ;
	private static Application instance = null;
	
	public Application() {
		daoManager = DaoManager.getInstance();
	}
	
	 public static Application getInstance() {
	        if (instance == null) {
	            instance = new Application();
	        }
	        return instance;
	    }
}
