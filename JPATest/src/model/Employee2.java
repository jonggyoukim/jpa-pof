package model;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;


/**
 * The persistent class for the EMPLOYEES2 database table.
 * 
 * CREATE TABLE new_employee
   ( first_name VARCHAR2(20)
   , last_name VARCHAR2(25)
   , email VARCHAR2(25)
   ) ;
*/

@Entity
@Table(name="NEW_EMPLOYEE")
public class Employee2 implements Serializable, PortableObject{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Employee2Id id;

	private String email;

	public Employee2() {
	}

	public Employee2Id getId() {
		return id;
	}

	public void setId(Employee2Id id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee2 [id=" + id + ", email=" + email + "]";
	}


	@Override
	public void readExternal(PofReader reader) throws IOException {
		id = (Employee2Id)reader.readObject(0);
		email = reader.readString(1) ;
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeObject(0, id);
		writer.writeString(1, email);
	}
	
}