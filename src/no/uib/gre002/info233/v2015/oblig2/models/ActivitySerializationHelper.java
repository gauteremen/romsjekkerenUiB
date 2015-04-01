package no.uib.gre002.info233.v2015.oblig2.models;

import java.io.Serializable;

/**
 * Helper class used to serialize Activity objects by extracting the values of
 * the non-serializable fields and storing them in a string array
 * 
 * @author Anders Eide
 * @version 1.0
 *
 */
public class ActivitySerializationHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	String[] properties = new String[7];

	public ActivitySerializationHelper(String weekday, String time,
			String date, String subject, String type, String buildingCode,
			String roomCode) {

		properties[0] = weekday;
		properties[1] = time;
		properties[2] = date;
		properties[3] = subject;
		properties[4] = type;
		properties[5] = roomCode;
		properties[6] = buildingCode;

	}

	/**
	 * Method used to deserialize activities
	 * @return The activity used to generate this helper object
	 */
	public Activity getActivity() {
		return new Activity(null, properties[3], properties[1], properties[4],
				properties[6], properties[5], properties[0] + properties[2]);
	}
}
