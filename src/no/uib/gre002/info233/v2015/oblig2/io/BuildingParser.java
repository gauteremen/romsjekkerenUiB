package no.uib.gre002.info233.v2015.oblig2.io;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.stage.Stage;
import jfx.messagebox.MessageBox;
import no.uib.gre002.info233.v2015.oblig2.models.UIBbuilding;
import no.uib.gre002.info233.v2015.oblig2.models.UIBroom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This class parses though the URL given, placing each RegEx-cleaned text into
 * an object.
 * 
 * @author Gaute Gjerløw Remen
 * @version 1.0
 *
 */

public class BuildingParser {

	private Stage stage;
	Pattern pattern;
	Matcher matcher;
	List<UIBbuilding> uibBuildings = new ArrayList<UIBbuilding>();
	List<UIBroom> uibRooms = new ArrayList<UIBroom>();

	public BuildingParser(String url) throws IOException {
		createBuilding(getValueFromHTML(url));
	}

	/**
	 * Find the buildings in the HTML document.
	 * 
	 * @throws IOException
	 * @throws SocketTimeoutException
	 *             if the internet connection fails. Gets a messagebox stating
	 *             this. MessageBox is a library downloaded from
	 *             http://sourceforge
	 *             .jp/projects/jfxmessagebox/downloads/57065/jfxmessagebox
	 *             -1.1.0.jar/
	 */
	public Elements getValueFromHTML(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (SocketTimeoutException | UnknownHostException e) {
			MessageBox.show(stage, "No internet connection.. \nPlease check your connection and restart", "Connection Error",
					MessageBox.ICON_INFORMATION | MessageBox.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements realTimeValues = doc.select("option[value*=:]");

		return realTimeValues;
	}

	/**
	 * Cleans the building tags for special characters and unnecessary text,
	 * using RegEx, and creates objects from the output.
	 * 
	 * @param buildings
	 */
	private void createBuilding(Elements buildings) {
		for (Element building : buildings) {

			pattern = Pattern.compile("([^)]+:\\S+\\D++)");
			matcher = pattern.matcher(building.text());

			if (matcher.find()) {
				UIBbuilding uib_building = new UIBbuilding(matcher.group(0));
				uibBuildings.add(uib_building);
			}

		}
	}

	/**
	 * 
	 * @return the list of building names
	 */
	public List<UIBbuilding> getBuildings() {
		return uibBuildings;
	}

}
