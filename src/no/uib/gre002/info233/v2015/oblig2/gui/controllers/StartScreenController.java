package no.uib.gre002.info233.v2015.oblig2.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import no.uib.gre002.info233.v2015.oblig2.gui.ScreenPane;
import no.uib.gre002.info233.v2015.oblig2.io.ActivitySerializer;
import no.uib.gre002.info233.v2015.oblig2.models.Activity;

/**
 * This is the controller for the startScreen.fxml
 * 
 * @author Gaute Gjerløw Remen & Anders Eide
 * @version 1.0
 *
 */
public class StartScreenController implements Initializable, ScreenController {
	private ScreenPane myScreenPane;

	/**
	 * strtArw_1 is really startArrow, but because the W3C wants unique id's, we
	 * had to use a more unique name. Or else we get an error message stating
	 * just this.
	 */
	@FXML
	public ImageView strtArw_1;

	/**
	 * Handles the FXML onMouseClicked on the cross symbol, exits the
	 * application.
	 * 
	 * @param e
	 */
	@FXML
	private void handleExitButtonEvent(MouseEvent e) {
		System.out.println("Button is clicked");
		System.exit(0);
	}

	/**
	 * Handles the FXML onMouseClicked on the arrow. This method gets the chosen
	 * item in the ComboBox and sets the next screen.
	 * 
	 * @param e
	 */
	@FXML
	private void handleNextPageEvent(MouseEvent e) {

		// Handle internet exception
		if ((ImageView) e.getSource() == strtArw_1) {
			myScreenPane.setScreen("buildingScreen");
		}
		System.out.println("Clicked");
	}

	@FXML
	private void handleLoadButtonEvent(MouseEvent e) {
		// TODO Add more sophisticated file selection to facilitate multiple
		// cached files and let the user know which room the loaded activities
		// belong to

		CalendarScreenController calendarController = (CalendarScreenController) myScreenPane
				.getControllers().get("calendarTableScreen");
		try {
			List<Activity> loadedActivities = ActivitySerializer
					.readActivitiesFromFile();
			calendarController.populate(loadedActivities);
			
			Activity activity = loadedActivities.get(0);
			calendarController.setLocationInfo(activity.getBuildingCode(), activity.getRoomCode());
			
			myScreenPane.setScreen("calendarTableScreen");
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Display error graphically
			e1.printStackTrace();
			System.out.println("Error reading file");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	/**
	 * Sets the new screen
	 */
	@Override
	public void setScreenPane(ScreenPane screenPage) {
		myScreenPane = screenPage;
	}

}
