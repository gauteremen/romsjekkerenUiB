package no.uib.gre002.info233.v2015.oblig2.io.utilities;

import java.util.Comparator;

import no.uib.gre002.info233.v2015.oblig2.models.Activity;


/**
 * Utility class for sorting activities based on date
 * @author Anders Eide & Gaute Gjerløw Remen
 * @version 1.0
 */
public class ActivityComparator implements Comparator<Activity> {
	
	/**
	 * Method for comparing activites based on date
	 */
	@Override
	public int compare(Activity activity1, Activity activity2) {
		return activity1.getDate().compareTo(activity2.getDate());
	}

}
