package no.uib.gre002.info233.v2015.oblig2.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.jsoup.nodes.Node;

/**
 * Class representing activities, containing information about which subject it
 * belongs to, a description of it and when and where it takes place.
 * 
 * @author Anders Eide
 *
 */
public class Activity implements ActivityInterface {

	// Node is not serializable, drop from serialization
	private Node activityNode;

	private String roomCode;
	private String buildingCode;

	private StringProperty subject;
	private StringProperty type;
	private StringProperty weekday;
	private StringProperty time;
	private StringProperty date;

	private Calendar startCalendar;
	private Calendar endCalendar;

	/**
	 * 
	 * @param activityNode
	 *            Node containing the activity itself
	 * @param subject
	 *            String representing the subject
	 * @param time
	 *            String representation of start and endtime of the activity
	 * @param type
	 *            String type of the activity (Forelesning/Seminar ect)
	 * @param room
	 *            String representation of the code for the room the activity
	 *            takes place in
	 * @param building
	 *            String represenatation of the code of the building the
	 *            activity takes place in
	 * @param date
	 *            String representation of the date for the activity
	 */

	public Activity(Node activityNode, String subject, String time,
			String type, String building, String room, String date) {

		String calendarDate = "";

		Pattern pattern = Pattern.compile("[0-9.]+");
		Matcher matcher = pattern.matcher(date);

		if (matcher.find())
			calendarDate = matcher.group(0);

		setWeekday(date.substring(0, 7));
		setSubject(subject);
		setTime(time);
		setType(type);
		setDate(calendarDate);

		this.startCalendar = parseCalendarDate(calendarDate,
				time.substring(0, 5));
		this.endCalendar = parseCalendarDate(calendarDate,
				time.substring(6, 11));

		this.activityNode = activityNode;
		this.roomCode = room;
		this.buildingCode = building;

	}

	/**
	 * 
	 * @return the StringProperty containing which weekday this activity occurs
	 *         on
	 */
	private StringProperty weekdayProperty() {
		if (weekday == null)
			weekday = new SimpleStringProperty(this, "weekday");
		return weekday;
	}

	/**
	 * 
	 * @return the StringProperty containing the time of day this activity
	 *         occurs
	 */
	private StringProperty timeProperty() {
		if (time == null)
			time = new SimpleStringProperty(this, "time");
		return time;
	}

	/**
	 * 
	 * @return the StringProperty containing the date which this activity occurs
	 */
	private StringProperty dateProperty() {
		if (date == null)
			date = new SimpleStringProperty(this, "date");
		return date;
	}

	/**
	 * 
	 * @return the StringProperty containing the subject this activity belongs
	 *         to
	 */
	private StringProperty subjectProperty() {
		if (subject == null)
			subject = new SimpleStringProperty(this, "subject");
		return subject;
	}

	/**
	 * 
	 * @return the StringProperty containing the subject this activity belongs
	 *         to
	 */
	private StringProperty typeProperty() {
		if (type == null)
			type = new SimpleStringProperty(this, "type");
		return type;
	}

	private void setWeekday(String value) {
		weekdayProperty().set(value);
	}

	public String getWeekday() {
		return weekdayProperty().get();
	}

	private void setSubject(String value) {
		subjectProperty().set(value);
	}

	public String getSubject() {
		return subjectProperty().get();
	}

	private void setTime(String time) {
		timeProperty().set(time);
	}

	public String getTime() {
		return timeProperty().get();
	}

	private void setType(String type) {
		typeProperty().set(type);
	}

	private void setDate(String date) {
		dateProperty().set(date);
	}

	public String getDate() {
		return dateProperty().get();
	}

	/**
	 * 
	 * @param dateString
	 *            String containing the date for the activity in a dd.mm.yyyy
	 *            format
	 * @param timeString
	 *            String containing the start or endtime for the activity in a
	 *            hh:mm format
	 * @return A Calendar object with the time and date input, or null if either
	 *         parameter is invalid
	 */
	private Calendar parseCalendarDate(String dateString, String timeString) {

		String formatedDateString = timeString + "." + dateString;
		SimpleDateFormat dateformater = new SimpleDateFormat("HH:mm.dd.MM.yyyy");

		try {
			Date date = dateformater.parse(formatedDateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method to assist in serializing activitites. Do note that information
	 * about which Node this activity belongs to is lost during serialization.
	 * 
	 * @return a serializable representation of this activity To deserialize
	 */
	public ActivitySerializationHelper getSerializableObject() {
		return new ActivitySerializationHelper(getWeekday(), getTime(),
				getDate(), getSubject(), getType(), getBuildingCode(),
				getRoomCode());
	}

	/**
	 * Returns a string representation of the object with the date, time and
	 * room code
	 */
	@Override
	public String toString() {
		return getWeekday() + " " + getTime() + " " + getDate() + " "
				+ getSubject() + " " + getType() + " " + getRoomCode();
	}

	/**
	 * @return the Node object containing the information about this activity Do
	 *         note that this will return null if the object has loaded from a
	 *         file
	 */
	@Override
	public Node getNode() {
		return activityNode;
	}

	/**
	 * Returns what type of activity this is (Lecture/Seminar ect..)
	 */
	@Override
	public String getType() {
		return typeProperty().get();
	}

	/**
	 * Returns the room code for the room where the activity takes place
	 */
	@Override
	public String getRoomCode() {
		return roomCode;
	}

	public String getBuildingCode() {
		return buildingCode;
	}

	/**
	 * Returns the date and time as a Calendar at which the activity starts
	 */
	@Override
	public Calendar getBeginTime() {
		return startCalendar;
	}

	/**
	 * Returns the date and time as a Calendar at which the activity ends
	 */
	@Override
	public Calendar getEndTime() {
		return endCalendar;
	}

	/**
	 * Returns a string description of the activity (same as getType())
	 */
	@Override
	public String getDescription() {
		return typeProperty().get();
	}

}
