package no.uib.gre002.info233.v2015.oblig2.gui.controllers.utilities;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

/**

 * 
 * @author Gaute Gjerløw Remen
 * @version 1.0
 *
 */
public class ComboBoxChangeListener {

	/**
	 * This method listens to if a object is selected in the ComobBox of the
	 * respective controller.
	 *
	 * @param combobox
	 *            is the ComboBox taken in from a controller.
	 * @param imageview
	 *            is the ImageView taken in from a controller.
	 */
	public void comboBoxChangeListener(ComboBox<String> combobox,
			ImageView imageview) {
		combobox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {

					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						imageview.setDisable(false);
					}
				});
	}

}
