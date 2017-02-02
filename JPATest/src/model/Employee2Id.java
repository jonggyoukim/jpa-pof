package model;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

/**
 * Entity implementation class for Entity: EmployeeId
 *
 */
@Embeddable
public class Employee2Id implements Serializable, PortableObject{
	
	private static final long serialVersionUID = 1L;

	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Override
	public String toString() {
		return "Employee2Id [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public Employee2Id() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee2Id other = (Employee2Id) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public void readExternal(PofReader reader) throws IOException {
		firstName = reader.readString(0);
		lastName = reader.readString(1);

	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeString(0, firstName);
		writer.writeString(1, lastName);
	}
}
