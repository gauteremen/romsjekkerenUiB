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
import no.uib.gre002.info233.v2015.oblig2.io.BuildingParser;
import no.uib.gre002.info233.v2015.oblig2.models.UIBbuilding;

/**
 * This is the controller for the buildingScreen.fxml
 * 
 * @author Gaute Gjerløw Remen & Anders Eide
 * @version 1.0
 */
public class BuildingScreenController implements Initializable,
		ScreenController {

	private ScreenPane myScreenPane;
	private ComboBoxChangeListener changeListener = new ComboBoxChangeListener();
	private String buildingCode;

	/**
	 * Initialize the ComboBox and the rmArw_1 fx:id in FXML.
	 * rmArw_1 is really roomArrow, but because the W3C wants unique id's, 
	 * we had to use a more unique name. Or else we get an error message stating just this.
	 */
	@FXML
	public ComboBox<String> buildingCombo;
	@FXML
	public ImageView rmArw_1;

	/**
	 * Handles the FXML onMouseClicked on the cross symbol,
	 * exits the application.
	 * @param e is the mouse clicked event.
	 */
	@FXML
	private void handleExitButtonEvent(MouseEvent e) {
		System.out.println("Button is clicked");
		System.exit(0);
	}

	/**
	 * Handles the FXML onMouseClicked on the home symbol, takes
	 * the user back to the start screen.
	 * @param e is the mouse clicked event.
	 */
	@FXML
	private void handleHomeButtonEvent(MouseEvent e) {
		myScreenPane.setScreen("startScreen");
	}

	/**
	 * Handles the FXML onMouseClicked on the arrow. This method 
	 * gets the chosen item in the ComboBox and sets the next screen.
	 * 
	 * @param e is the mouse clicked event.
	 */
	@FXML
	private void handleNextPageEvent(MouseEvent e) {
		if ((ImageView) e.getSource() == rmArw_1) {
			buildingCode = buildingCombo.getSelectionModel().getSelectedItem();
			System.out.println(buildingCode);

			transferBuildingCode(buildingCode);

			myScreenPane.setScreen("roomScreen");
		}

		System.out.println("Clicked");
	}


	/**
	 * Sets the new screen from the ScreenPane class.
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
		buildingCombo.getItems().clear();

		try {
			BuildingParser buildingParser = new BuildingParser(
					"http://rom.app.uib.no/ukesoversikt/?entry=byggrom");

			for (UIBbuilding building : buildingParser.getBuildings()) {
				buildingCombo.getItems().add(building.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		changeListener.comboBoxChangeListener(buildingCombo, rmArw_1);
	}

	/**
	 * Method used to transfer which building the RoomController should show
	 * rooms from
	 * 
	 * @param controller
	 *            The controller that handles displaying rooms
	 * @param buildingInfo
	 *            the name and code of the building
	 */
	private void transferBuildingCode(String buildingInfo) {
		RoomScreenController roomController = (RoomScreenController) myScreenPane
				.getControllers().get("roomScreen");
		roomController.setBuildingCode(buildingInfo);
		roomController.populateComboBox();
	}
}
