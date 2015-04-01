package no.uib.gre002.info233.v2015.oblig2.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import no.uib.gre002.info233.v2015.oblig2.gui.ScreenPane;
import no.uib.gre002.info233.v2015.oblig2.io.ActivityParser;
import no.uib.gre002.info233.v2015.oblig2.io.ActivitySerializer;
import no.uib.gre002.info233.v2015.oblig2.io.BuildingCodeParser;
import no.uib.gre002.info233.v2015.oblig2.models.Activity;

/**
 * This is the controller for the calendarTableScreen.fxml
 * 
 * @author Gaute Gjerløw Remen & Anders Eide
 *
 */
public class CalendarScreenController implements Initializable,
		ScreenController {

	private ObservableList<Activity> activities;
	private ScreenPane myScreenPane;
	private String buildingCode;
	private String roomCode;

	@FXML
	private TableView<Activity> activityTable;
	@FXML
	private TableColumn<Activity, String> dayColumn;
	@FXML
	private TableColumn<Activity, String> timeColumn;
	@FXML
	private TableColumn<Activity, String> dateColumn;
	@FXML
	private TableColumn<Activity, String> typeColumn;
	@FXML
	private TableColumn<Activity, String> subjectColumn;
	@FXML
	private Label locationLabel;

	/**
	 * Method used to populate the calendar table with events previously fetched
	 * via fetchActivityList()
	 */
	public void populate(List<Activity> activityList) {

		this.activities = FXCollections.observableArrayList(activityList);

		dayColumn
				.setCellValueFactory(new PropertyValueFactory<Activity, String>(
						"weekday"));
		timeColumn
				.setCellValueFactory(new PropertyValueFactory<Activity, String>(
						"time"));
		dateColumn
				.setCellValueFactory(new PropertyValueFactory<Activity, String>(
						"date"));
		subjectColumn
				.setCellValueFactory(new PropertyValueFactory<Activity, String>(
						"subject"));
		typeColumn
				.setCellValueFactory(new PropertyValueFactory<Activity, String>(
						"type"));

		activityTable.setItems(activities);
	}


	/**
	 * Handles the FXML onMouseClicked on the cross symbol
	 * 
	 * @param e
	 */
	@FXML
	private void handleExitButtonEvent(MouseEvent e) {
		System.out.println("Button is clicked");
		System.exit(0);
	}

	/**
	 * Handles the FXML onMouseClicked on the home symbol
	 * 
	 * @param e
	 */
	@FXML
	private void handleHomeButtonEvent(MouseEvent e) {
		myScreenPane.setScreen("startScreen");
	}

	@FXML
	private void handleSaveButtonEvent(MouseEvent e) {

		try {
			ActivitySerializer.writeActivitiesToFile(activities);
		} catch (IOException exception) {
			// TODO Display warning in GUI
			exception.printStackTrace();
		}

		System.out.println("Save button is clicked");
	}

	/**
	 * Sets the new screen
	 */
	@Override
	public void setScreenPane(ScreenPane screenPage) {
		myScreenPane = screenPage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	/**
	 * Setter for building and room fields
	 * 
	 * @param building
	 *            the selected building
	 * @param room
	 *            the selected room
	 */
	public void setLocationInfo(String building, String room) {
		this.buildingCode = building;
		this.roomCode = room;
		
		locationLabel.setText(building + " " + room);
	}

	/**
	 * Method for retrieving the list of activities from the building and room
	 * previously set by calling setLocationInfo()
	 */
	public List<Activity> fetchActivityList() {
		String roomURL = BuildingCodeParser.getRoomURL(buildingCode, roomCode);
		ActivityParser activityParser = new ActivityParser(roomURL, buildingCode, roomCode);

		return activityParser.getActivityList();
	}

}
