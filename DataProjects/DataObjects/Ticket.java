package DataObjects;

import Constant.SeatType;

public class Ticket {
	private String departDate;
	private String departFrom;
	private String arriveAt;
	private String seatType;
	private String ticketAmount;
	
	public Ticket(String departDate, String departFrom, String arriveAt, SeatType seatType, String ticketAmount) {
		this.departDate = departDate;
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
		this.seatType = seatType.getValue();
		this.ticketAmount = ticketAmount;
	}
	
	public Ticket(String departFrom, String arriveAt, SeatType seatType, String ticketAmount) {
		this("",departFrom,arriveAt,seatType,ticketAmount);
	}
	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	public String getDepartFrom() {
		return departFrom;
	}
	public void setDepartFrom(String departFrom) {
		this.departFrom = departFrom;
	}
	public String getArriveAt() {
		return arriveAt;
	}
	public void setArriveAt(String arriveAt) {
		this.arriveAt = arriveAt;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	
}
