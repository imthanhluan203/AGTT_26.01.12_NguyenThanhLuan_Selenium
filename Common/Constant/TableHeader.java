package Constant;

public enum TableHeader {
	DEPART_STATION("Depart Station"),
	ARRIVE_STATION("Arrive Station"),
	DEPART_TIME("Depart Time"),
	ARRIVE_TIME("Arrive Time"),
	CHECK_PRICE("Check Price"),
	BOOK_TICKET("Book ticket"),
	DEPART_DATE("Depart Date"),
	SEAT_TYPE("Seat Type"),
	AMOUNT("Amount");
	
	private final String value;
	TableHeader(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    
}
