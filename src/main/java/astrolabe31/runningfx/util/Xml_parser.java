package astrolabe31.runningfx.util;

public class Xml_parser {
	
	private String westBoundLongitude;
	private String eastBoundLongitude;
	private String southBoundLatitude;
	private String northBoundLatitude;
	
	/*
    <westBoundLongitude>-125.192865</westBoundLongitude>
    <eastBoundLongitude>-66.105824</eastBoundLongitude>
    <southBoundLatitude>19.416377</southBoundLatitude>
    <northBoundLatitude>54.318281</northBoundLatitude>
	*/
	
	

	public String getWestBoundLongitude() {
		return westBoundLongitude;
	}

	public void setWestBoundLongitude(String westBoundLongitude) {
		this.westBoundLongitude = westBoundLongitude;
	}

	public String getEastBoundLongitude() {
		return eastBoundLongitude;
	}

	public void setEastBoundLongitude(String eastBoundLongitude) {
		this.eastBoundLongitude = eastBoundLongitude;
	}
	
	
	
	
	
	/**
	 * Constructeur type
	 
	public Course() {
	    try {
	        User user = new User("admin", "azerty");
	        XMLTools.encodeToFile(user, "user.xml");
	        System.out.println(user);
	        user = new User("newAdmin", "123456"); 
	        System.out.println(user);
	        user = (User) XMLTools.decodeFromFile("user.xml");
	        System.out.println(user);
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	*/
	

}
