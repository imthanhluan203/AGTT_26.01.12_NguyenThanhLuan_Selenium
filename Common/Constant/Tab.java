package Constant;

public enum Tab {
	HOME("Home"),
    LOGIN("Login"),
    REGISTER("Register"),
	FAQ("FAQ"),
	LOGOUT("Log out"),
	CREATE("create an account"),
	BOOKTICKET("Book ticket"),
	TIMETABLE("Timetable"),
	MYTICKET("My ticket");
	
    private final String value;

    Tab(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}