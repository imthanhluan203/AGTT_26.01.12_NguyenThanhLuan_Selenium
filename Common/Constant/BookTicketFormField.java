package Constant;

public enum BookTicketFormField {
	DEPART_DATE("Date"),
    DEPART_FROM("DepartStation"),
    ARRIVE_AT("ArriveStation"),
	SEAT_TYPE("SeatType"),
	TICKET_AMOUNT("TicketAmount");
	
    private final String value;

    BookTicketFormField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
