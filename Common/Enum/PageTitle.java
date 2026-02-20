package Enum;

public enum PageTitle {
	GENERAL("Safe Railway: General"),
	HOME("Safe Railway"),
	FAQ("Safe Railway - FAQ"),
	CONTACT("Safe Railway"),
	TIME_TABLE("Safe Railway - Train Timetable"),
	TICKET_PRICES("Safe Railway - Train ticket price list"),
	LOGIN("Safe Railway - Login"),
	REGISTER("Safe Railway - Register an Account"),
	CHANGE_PASSWORD("Safe Railway - Change Password"),
	MY_TICKET("Safe Railway - My Ticket"),
	BOOK_TICKET("Safe Railway - Book Ticket"),
	BOOK_SUCCESS("Safe Railway - Success"),
	TICKET_PRICE("Safe Railway - Ticket Price"),
	REGISTER_THANK("Safe Railway - Thanks for registering"),
	REGISTER_CONFIRM("Safe Railway - Registration Confirmation Page"),
	RESET_PASSWORD("Safe Railway - Password Reset"),
	GUERRILLA_MAIL("Guerrilla Mail - Disposable Temporary E-Mail Address");
	private final String value; 
	PageTitle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
