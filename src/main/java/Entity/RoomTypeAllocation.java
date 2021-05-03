package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RoomTypeAllocation {

	@Id @GeneratedValue
	private int allocId;
	private int contractId;
	private String roomType;
	private int price;
	private int numberOfRoom;
	private int max;
	
	public RoomTypeAllocation(int id, String type, int price, int number, int max) {
		this.contractId = id;
		this.roomType = type;
		this.price = price;
		this.numberOfRoom = number;
		this.max = max;
	}
	
	public int getAllocId() {
		return allocId;
	}
	public void setAllocId(int allocId) {
		this.allocId = allocId;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumberOfRoom() {
		return numberOfRoom;
	}
	public void setNumberOfRoom(int numberOfRoom) {
		this.numberOfRoom = numberOfRoom;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
}
