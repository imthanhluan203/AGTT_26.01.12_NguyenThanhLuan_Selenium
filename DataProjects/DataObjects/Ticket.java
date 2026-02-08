package DataObjects;

import Constant.SeatType;

public class Ticket {
	private String departDate;
	private String departFrom;
	private String arriveAt;
	private String seatType;
	private String ticketAmount;
	private int duration;
	
	public Ticket(String departDate, String departFrom, String arriveAt, SeatType seatType, String ticketAmount,int duration) {
		this.departDate = departDate;
		this.departFrom = departFrom;
		this.arriveAt = arriveAt;
		this.seatType = seatType.getValue();
		this.ticketAmount = ticketAmount;
		this.duration = duration;
	}
	
	public Ticket(String departFrom, String arriveAt, SeatType seatType, String ticketAmount,int duration) {
		this("",departFrom,arriveAt,seatType,ticketAmount,duration);
	}
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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
