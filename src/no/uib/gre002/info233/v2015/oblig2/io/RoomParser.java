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
import no.uib.gre002.info233.v2015.oblig2.models.UIBroom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class used to retrieve a list of rooms from a URL
 * @author Gaute Gjerløw Remen
 * @version 1.0
 *
 */
public class RoomParser {

	Stage stage;
	Pattern pattern;
	Matcher matcher;
	List<UIBroom> uibRooms = new ArrayList<UIBroom>();

	public RoomParser(String url) throws IOException {
		createRooms(getValueFromHTML(url));
	}

	/**
	 * Find the buildings in the HTML document
	 * 
	 * @throws IOException
	 * @throws SocketTimeoutException
	 *             if the internet connection fails. Gets a messagebox stating
	 *             this. MessageBox is a library downloaded from
	 *             http://sourceforge
	 *             .jp/projects/jfxmessagebox/downloads/57065/jfxmessagebox
	 *             -1.1.0.jar/
	 */
	public Elements getValueFromHTML(String url){
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
	 * @param rooms
	 */
	private void createRooms(Elements rooms) {
		
		for (Element room : rooms) {
			pattern = Pattern.compile(":([^)]+)");
			matcher = pattern.matcher(room.text());
			
			if (matcher.find()) {
				UIBroom uib_room = new UIBroom(matcher.group(1));
				uibRooms.add(uib_room);
				System.out.println(matcher.group(1));
			}
		}
	}
	
	public List<UIBroom> getBuildings() {
		return uibRooms;
	}
}

