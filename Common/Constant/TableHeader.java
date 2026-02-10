package Constant;

public enum TableHeader {
	DEPARTSTATION("Depart Station"),
	ARRIVESTATION("Arrive Station"),
	DEPARTTIME("Depart Time"),
	ARRIVETIME("Arrive Time"),
	CHECKPRICE("Check Price"),
	BOOKTICKET("Book ticket");
	private final String value;
	TableHeader(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    
}
