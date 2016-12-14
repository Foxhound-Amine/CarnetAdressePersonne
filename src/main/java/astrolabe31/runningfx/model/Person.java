package astrolabe31.runningfx.model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import astrolabe31.runningfx.util.LocalDateAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextInputControl;


@NamedQueries({
	   // @NamedQuery(name = "Running.delete", query = "DELETE FROM Running c WHERE c._id = :id"),
	    @NamedQuery(name = "Eleve.findAll", query = "SELECT r FROM Eleve r")
	    //@NamedQuery(name = "Running.findPays", query = "SELECT r FROM Running r WHERE r._pays = :pays ")
	})
public class Person {
	@Id
    @GeneratedValue
    private Integer id;

	@Basic(optional = false)
	private final SimpleStringProperty firstName;
	 
	@Basic(optional = false)
	private final SimpleStringProperty lastName;

    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthday;
	
	
	public Person(){
		this(null, null);
	}
	
	public Person(String firstname, String lastname ) {
		// TODO Auto-generated constructor stub
		
		this.firstName = new SimpleStringProperty(firstname);
        this.lastName = new SimpleStringProperty(lastname);
        
        this.street = new SimpleStringProperty();
        this.postalCode = new SimpleIntegerProperty();
        this.city = new SimpleStringProperty();
        this.birthday = new SimpleObjectProperty<LocalDate>();
        //this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstname) {
		this.firstName.set( firstname);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastname) {
		this.lastName.set(lastname);
	}

	public StringProperty firtsNameProperty() {
	        return firstName;
	    }
	public StringProperty lastNameProperty() {
        return lastName;
    }
	
	 public String getStreet() {
	        return street.get();
	    }

	    public void setStreet(String street) {
	        this.street.set(street);
	    }

	    public StringProperty streetProperty() {
	        return street;
	    }

	    public int getPostalCode() {
	        return postalCode.get();
	    }

	    public void setPostalCode(int postalCode) {
	        this.postalCode.set(postalCode);
	    }

	    public IntegerProperty postalCodeProperty() {
	        return postalCode;
	    }

	    public String getCity() {
	        return city.get();
	    }

	    public void setCity(String city) {
	        this.city.set(city);
	    }

	    public StringProperty cityProperty() {
	        return city;
	    }

	    @XmlJavaTypeAdapter(LocalDateAdapter.class)
	    public LocalDate getBirthday() {
	        return birthday.get();
	    }

	    public void setBirthday(LocalDate birthday) {
	        this.birthday.set(birthday);
	    }

	    public ObjectProperty<LocalDate> birthdayProperty() {
	        return birthday;
	    }
	
}
