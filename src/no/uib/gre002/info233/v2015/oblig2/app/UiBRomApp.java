package no.uib.gre002.info233.v2015.oblig2.app;

import java.io.IOException;

import no.uib.gre002.info233.v2015.oblig2.gui.ScreenPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author Gaute Gjerløw Remen
 * @version 1.0
 * 
 *
 */
public class UiBRomApp extends Application {

	public static String startScreenFXML = "startScreen.fxml";
	public static String buildingScreenFXML = "buildingScreen.fxml";
	public static String roomScreenFXML = "roomScreen.fxml";
	public static String calendarTableScreenFXML = "calendarTableScreen.fxml";
    private double xOffset = 0;
    private double yOffset = 0;

	/**
	 * In Java 8, the main method is bypassed and the start()-method is used
	 * instead. Since the JRE System Library is set to 1.7, the main-method must
	 * be implemented and use the launch(args); from the Application extention.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		launch(args);
	}

	/**
	 * The main method of a JavaFX-app in Java 8.
	 * Included a EventHandler for dragging the window. This was difficult in FXML.
	 */
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.initStyle(StageStyle.UNDECORATED);

		ScreenPane mainContainer = new ScreenPane();
		mainContainer.loadScreen("startScreen", UiBRomApp.startScreenFXML);
		mainContainer
				.loadScreen("buildingScreen", UiBRomApp.buildingScreenFXML);
		mainContainer.loadScreen("roomScreen", UiBRomApp.roomScreenFXML);
		mainContainer.loadScreen("calendarTableScreen", UiBRomApp.calendarTableScreenFXML);
		mainContainer.setScreen("startScreen");

		mainContainer.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        mainContainer.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

		Scene scene = new Scene(mainContainer);

		primaryStage.setScene(scene);
		primaryStage.show();

	}
}