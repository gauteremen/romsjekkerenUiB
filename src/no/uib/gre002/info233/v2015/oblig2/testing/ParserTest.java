package no.uib.gre002.info233.v2015.oblig2.testing;

import static org.junit.Assert.*;

import java.util.List;

import no.uib.gre002.info233.v2015.oblig2.io.ActivityParser;
import no.uib.gre002.info233.v2015.oblig2.models.Activity;

import org.junit.Test;

/**
 * 
 * @author Anders Eide & Gaute Gjerløw Remen
 * @version 1.0
 *
 */
public class ParserTest {

	@Test
	public void activityParserTestShouldReturn12Activities() {
		String url = "http://rom.app.uib.no/ukesoversikt/?entry=byggrom&building=A70%3A&room=A70%3AA200";
		ActivityParser parser = new ActivityParser(url, "A70:", "A200");
		
		List<Activity> activities = parser.getActivityList();
		
		assertEquals(12, activities.size());
	}
}
