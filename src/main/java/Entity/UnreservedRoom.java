package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UnreservedRoom implements roominter {
 
	@Id 
	private int id;

	public UnreservedRoom( int id) {
		this.id = id;
	}
	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
