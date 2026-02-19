package Enum;

public enum City {
	SAIGON("Sài Gòn"),
	PHANTHIET("Phan Thiết"),
	NHATRANG("Nha Trang"),
	DANANG("Đà Nẵng"),
	HUE("Huế"),
	QUANGNGAI("Quảng Ngãi");
	
	private final String value;
	
	City(String value) {
        this.value = value;
    }

	public String getValue() {
		return this.value;
	}
	
}
