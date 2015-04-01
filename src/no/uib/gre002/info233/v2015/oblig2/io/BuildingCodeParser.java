package no.uib.gre002.info233.v2015.oblig2.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for constructing URLs
 * 
 * @author Anders Eide & Gaute Gjerløw Remen
 * @version 1.0
 *
 */
public class BuildingCodeParser {

	public static String getBuildingCode(String buildingName) {
		
		Pattern pattern = Pattern.compile("([A-Za-zÆØÅæøå][^)]+:)");
		Matcher matcher = pattern.matcher(buildingName);

		if (matcher.find()) {
			System.out.println(matcher.group(0));

			return matcher.group(0);
		}
		return null;
	}

	/**
	 * Method for getting the complete URL for a building from its name and code
	 * 
	 * @param buildingName
	 *            The name and code of the building
	 * @return The full URL of the building
	 */
	public static String getBuildingURL(String buildingName) {
			return "http://rom.app.uib.no/ukesoversikt/?entry=byggrom&building="
					+ getBuildingCode(buildingName);
	}

	public static String getRoomURL(String buildingName, String roomName) {
		String buildingCode = getBuildingCode(buildingName);
		// http://rom.app.uib.no/ukesoversikt/?entry=byggrom&building=SV%3A&room=SV%3AS204
		return "http://rom.app.uib.no/ukesoversikt/?entry=byggrom&building=" + buildingCode + "&room=" + buildingCode + roomName;
	}
}
