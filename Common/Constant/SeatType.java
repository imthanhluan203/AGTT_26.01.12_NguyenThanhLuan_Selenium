package Constant;

public enum SeatType {
	HS("Hard seat"),
	SS("Soft seat"),
	SSC("Soft seat with air conditioner"),
	HB("Hard bed"),
	SB("Soft bed"),
	SBC("Soft bed with air conditioner");
	
	private final String value; 
	SeatType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
