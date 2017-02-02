package model;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(Employee1Id.class)
public class Employee1 implements Serializable, PortableObject {
	private static final long serialVersionUID = 1L;

	private String email;
	
	@Id
	@Column(name="FIRST_NAME")
	private String firstName;

	@Id
	@Column(name="LAST_NAME")
	private String lastName;

	
	public Employee1() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Employee1 [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	@Override
	public void readExternal(PofReader reader) throws IOException {
		email = reader.readString(0) ;
		firstName = reader.readString(1);
		lastName = reader.readString(2);

	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeString(0, email);
		writer.writeString(1, firstName);
		writer.writeString(2, lastName);
	}
	
}