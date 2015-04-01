package no.uib.gre002.info233.v2015.oblig2.models;

import java.io.Serializable;
import java.util.Calendar;

import org.jsoup.nodes.Node;


/**
 * Defines an activity, its fields and necessary methods, for obligatory
 * assignment 2 info233 The purpose of an activity is the storage and access of
 * its field for use in the GUI.
 * 
 * @author mof077
 * @version 1.0
 */
public interface ActivityInterface extends Serializable {

	/**
	 * Returns the value of the field node, which saves the primary node of the
	 * activity
	 * 
	 * @return Node the HTML node
	 */
	public Node getNode();

	/**
	 * Gets the type of activity
	 * 
	 * @return String the type of activity
	 */
	public String getType();

	/**
	 * Gets the room associated with the Activity
	 * 
	 * @return String the room name
	 */
	public String getRoomCode();

	/**
	 * Gets the begin date and time of the Activity saved as a Calendar object
	 * 
	 * @return Calendar the begin time and date
	 */
	public Calendar getBeginTime();

	/**
	 * Gets the end time and date of the activity as a Calendar object
	 * 
	 * @return Calendar the end time and date
	 */
	public Calendar getEndTime();

	/**
	 * Returns the description of the Activity
	 * 
	 * @return String the description
	 */
	public String getDescription();
}