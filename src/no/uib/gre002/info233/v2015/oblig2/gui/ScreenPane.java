package no.uib.gre002.info233.v2015.oblig2.gui;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import no.uib.gre002.info233.v2015.oblig2.gui.controllers.ScreenController;

/**
 * This class set the new screen for the application. It handles the loading of
 * all the FXML files, to display the screens loaded.
 *
 * @author Gaute Gjerløw Remen & Anders Eide
 * @version 1.0
 */

public class ScreenPane extends StackPane {

	private HashMap<String, Node> screens = new HashMap<>();
	private HashMap<String, ScreenController> controllers = new HashMap<>();

	public ScreenPane() {
		super();
	}

	/**
	 * Adds a screen to the collection of screens.
	 * 
	 * @param name
	 *            is a screen with a predefined name in the main class.
	 * @param screen
	 *            is the loaded screen
	 */
	public void addScreen(String name, Node screen) {
		screens.put(name, screen);
	}

	/**
	 * Get a screen from the collection of screens.
	 * 
	 * @param name
	 *            is a screen with a predefined name in the main class.
	 * @return the name of the screen
	 */
	public Node getScreen(String name) {
		return screens.get(name);
	}

	/**
	 * Loads the FXML file, add the screen to the screens collection and injects
	 * the screenPane to the controller.
	 * 
	 * @param name
	 *            is a screen with a predefined name in the main class.
	 * @param resource
	 *            is the FXML resource.
	 * @return if the screen loaded exist. if not, print error message.
	 */
	public boolean loadScreen(String name, String resource) {
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource(
					resource));
			Parent loadScreen = (Parent) myLoader.load();
			ScreenController myScreenController = ((ScreenController) myLoader
					.getController());
			myScreenController.setScreenPane(this);
			addScreen(name, loadScreen);
			controllers.put(name, myScreenController);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @return A map of the screencontrollers with the name of each screen being
	 *         the key to its controller
	 */
	public HashMap<String, ScreenController> getControllers() {
		return controllers;
	}

	/**
	 * This method tries to display the screen with a predefined name. First it
	 * makes sure the screen has been already loaded. Then if the is more than
	 * one screen, the new screen is added second, and then the current screen
	 * is removed. If there are no screens being displayed, the new screen is
	 * just added to the root.
	 * 
	 * This method is also quite messy.
	 * 
	 * @param name
	 *            is a screen with a predefined name in the main class.
	 * @return true if screen is loaded, or false if no screen is loaded
	 */
	public boolean setScreen(final String name) {
		if (screens.get(name) != null) {
			final DoubleProperty opacity = opacityProperty();

			if (!getChildren().isEmpty()) {
				Timeline fade = new Timeline(new KeyFrame(Duration.ZERO,
						new KeyValue(opacity, 1.0)), new KeyFrame(new Duration(
						500), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent t) {
						getChildren().remove(0);
						getChildren().add(0, screens.get(name));
						Timeline fadeIn = new Timeline(new KeyFrame(
								Duration.ZERO, new KeyValue(opacity, 0.0)),
								new KeyFrame(new Duration(500), new KeyValue(
										opacity, 1.0)));
						fadeIn.play();
					}
				}, new KeyValue(opacity, 0.0)));
				fade.play();

			} else {
				setOpacity(0.0);
				getChildren().add(screens.get(name));

				Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO,
						new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(
						1000), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}
			return true;
		} else {
			System.out.println("Error: Screen has not been loaded! \n");
			return false;
		}
	}

	/**
	 * This method will remove the screen with the given name from the
	 * collection of screens.
	 * 
	 * @param name
	 *            is a screen with a predefined name in the main class.
	 * @return false if the screen the method is trying to unload does not
	 *         exist, else true.
	 */
	public boolean unloadScreen(String name) {
		if (screens.remove(name) == null) {
			System.out.println("Error: Screen do not exist");
			return false;
		} else {
			return true;
		}
	}

}
