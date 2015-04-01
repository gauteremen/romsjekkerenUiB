package no.uib.gre002.info233.v2015.oblig2.io;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import no.uib.gre002.info233.v2015.oblig2.models.Activity;
import no.uib.gre002.info233.v2015.oblig2.models.ActivitySerializationHelper;

/**
 * Class containing static methods for writing and reading activities from files
 * 
 * @author Anders Eide
 * @version 1.0
 *
 */
public class ActivitySerializer {
	private static File activityFile = new File("activities.ser");

	/**
	 * Method for writing a list of activities to a file in the base directory
	 * of the application
	 * 
	 * @param activities
	 *            the activities to be written
	 * @throws IOException
	 */
	public static void writeActivitiesToFile(List<Activity> activities)
			throws IOException {

		FileOutputStream outStream = new FileOutputStream(activityFile);
		ObjectOutputStream activityStream = new ObjectOutputStream(outStream);

		for (Activity activity : activities) {
			activityStream.writeObject(activity.getSerializableObject());
			System.out.println("Wrote " + activity.toString());
		}

		outStream.close();
	}

	/**
	 * Method for reading activities from a file on disk
	 * 
	 * @return a list of activities if activities have previously been written
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Activity> readActivitiesFromFile() throws IOException,
			ClassNotFoundException {
		List<Activity> activityList = new ArrayList<Activity>();

		FileInputStream inStream = new FileInputStream(activityFile);
		ObjectInputStream activityInStream = new ObjectInputStream(inStream);

		boolean fileHasObjects = true;

		while (fileHasObjects) {

			try {
				ActivitySerializationHelper serializedActivity = (ActivitySerializationHelper) activityInStream
						.readObject();

				activityList.add(serializedActivity.getActivity());

			} catch (EOFException e) {
				System.out.println("All activities loaded");
				fileHasObjects = false;
			}
		}

		inStream.close();
		return activityList;
	}
}
