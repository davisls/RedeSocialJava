package RedeSocial;

public class Post {
	public String text;
	public String date;
	public String hours;
	
	public void create(String text, String date, String hours) throws InvalidDateException, InvalidHoursException {
		formatDate(date);
		formatHours(hours);

		this.text = text;
		this.date = date;
		this.hours = hours;
	}
	
	public void formatDate(String date) throws InvalidDateException {
		if (date.length() != 10) {
			throw new InvalidDateException();
		} else if (date.charAt(2) != ('/') || date.charAt(5) != ('/')) {
			throw new InvalidDateException();
		} else {
			String day = date.substring(0, 2);
			String month = date.substring(3, 5);
			String year = date.substring(6, 10);

			if (!day.matches("[0-9]+") || !month.matches("[0-9]+") || !year.matches("[0-9]+")) {
				throw new InvalidDateException();
			}
		}
	}
	
	public void formatHours(String hours) throws InvalidHoursException {
		if (hours.length() != 5) {
			throw new InvalidHoursException();
		} else if (hours.charAt(2) != (':')) {
			throw new InvalidHoursException();
		} else {
			String hour = hours.substring(0, 2);
			String minutes = hours.substring(3, 5);
			
			if (!hour.matches("[0-9]+") || !minutes.matches("[0-9]+")) {
				throw new InvalidHoursException();
			}
		}
	}
}
