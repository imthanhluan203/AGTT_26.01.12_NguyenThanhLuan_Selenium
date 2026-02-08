package Constant;

public enum TimeTable {
	CHECK_PRICE("check price"),
    BOOK_TICKET("book ticket");
	
    private final String value;

    TimeTable(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
