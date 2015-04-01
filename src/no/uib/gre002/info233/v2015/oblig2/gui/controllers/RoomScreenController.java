package no.uib.gre002.info233.v2015.oblig2.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import no.uib.gre002.info233.v2015.oblig2.gui.ScreenPane;
import no.uib.gre002.info233.v2015.oblig2.gui.controllers.utilities.ComboBoxChangeListener;
import no.uib.gre002.info233.v2015.oblig2.io.BuildingCodeParser;
import no.uib.gre002.info233.v2015.oblig2.io.RoomParser;
import no.uib.gre002.info233.v2015.oblig2.models.UIBroom;

/**
 * This is the controller for the roomScreen.fxml
 * 
 * @author Gaute Gjerløw Remen & Anders Eide
 * @version 1.0
 */
public class RoomScreenController implements Initializable, ScreenController {

	private ScreenPane myScreenPane;
	private ComboBoxChangeListener changeListener = new ComboBoxChangeListener();
	private String buildingCode;
	private String roomCode;

	/**
	 * Initialize the ComboBox and the clndrv_1 fx:id in FXML clndrv_1 is really
	 * calenderArrow, but because the W3C wants unique id's, we had to use a
	 * more unique name. Or else we get an error message stating just this.
	 */
	@FXML
	public ComboBox<String> roomCombo;
	@FXML
	public ImageView clndrv_1;

	/**
	 * Handles the FXML onMouseClicked on the cross symbol, exits the
	 * application.
	 * 
	 * @param e
	 *            is the clicked mouse event.
	 */
	@FXML
	private void handleExitButtonEvent(MouseEvent e) {
		System.out.println("Button is clicked");
		System.exit(0);
	}

	/**
	 * Handles the FXML onMouseClicked on the home symbol, takes the user back
	 * to the start screen.
	 * 
	 * @param e
	 *            is the clicked mouse event.
	 */
	@FXML
	private void handleHomeButtonEvent(MouseEvent e) {
		myScreenPane.setScreen("startScreen");
	}

	/**
	 * Handles the FXML onMouseClicked on the arrow. This method gets the chosen
	 * item in the ComboBox and sets the next screen.
	 * 
	 * @param e
	 *            is the mouse clicked event.
	 */
	@FXML
	private void handleNextPageEvent(MouseEvent e) {
		if ((ImageView) e.getSource() == clndrv_1) {

			// TODO Fix rooms so that room names are kept in the list
			roomCode = roomCombo.getSelectionModel().getSelectedItem();
			System.out.println(buildingCode + " - " + roomCode);

			initializeTableContent(buildingCode, roomCode);

			myScreenPane.setScreen("calendarTableScreen");
		}
		System.out.println("Clicked");
	}


	/**
	 * Sets the new screen
	 */
	@Override
	public void setScreenPane(ScreenPane screenPage) {
		myScreenPane = screenPage;
	}

	/**
	 * Initialize the ComboBox items
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		changeListener.comboBoxChangeListener(roomCombo, clndrv_1);

	}

	/**
	 * Used to store which building the user has selected
	 * 
	 * @param buildingCode
	 *            the code of the building (i.e. SV for Laurits Meltzers)
	 */
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	/**
	 * This method populates the ComboBox with room objects, based on what
	 * building the user chose in the last screen.
	 */
	public void populateComboBox() {
		roomCombo.getItems().clear();
		try {
			RoomParser roomParser = new RoomParser(
					BuildingCodeParser.getBuildingURL(buildingCode));
			
			for (UIBroom room : roomParser.getBuildings()) {
				roomCombo.getItems().add(room.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Transfers information about the room and building to the activity display
	 * table
	 * 
	 * @param building
	 *            the building selected by the user
	 * @param room
	 *            the room selected by the user
	 */
	private void initializeTableContent(String building, String room) {
		CalendarScreenController calendarController = (CalendarScreenController) myScreenPane
				.getControllers().get("calendarTableScreen");
		calendarController.setLocationInfo(building, room);
		
		calendarController.populate(calendarController.fetchActivityList());
		
	}
}
