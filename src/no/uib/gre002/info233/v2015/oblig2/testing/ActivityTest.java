package no.uib.gre002.info233.v2015.oblig2.testing;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import no.uib.gre002.info233.v2015.oblig2.io.ActivityParser;
import no.uib.gre002.info233.v2015.oblig2.models.Activity;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Anders Eide & Gaute Gjerløw Remen
 * @version 1.0
 *
 */
public class ActivityTest {
	private ActivityParser parser;
	

	@Before
	public void setup(){
		String url = "http://rom.app.uib.no/ukesoversikt/?entry=byggrom&building=SV%3A&room=SV%3AAS";
		parser = new ActivityParser(url, "SV:", "AAS");
		
	}
	
	@Test
	public void testRoomCodeShouldReturnSV105() {
		assertEquals("AAS", parser.getActivityList().get(1).getRoomCode());
	}
	
	@Test
	public void testFirstActivity(){
		Activity testActivity = parser.getActivityList().get(0);
		String startTime = testActivity.getBeginTime().get(Calendar.HOUR_OF_DAY) + ":" + testActivity.getBeginTime().get(Calendar.MINUTE);
		String endTime = testActivity.getEndTime().get(Calendar.HOUR_OF_DAY) + ":" + testActivity.getBeginTime().get(Calendar.MINUTE);
		assertEquals("ECON110", testActivity.getSubject());
		assertEquals("Forelesning", testActivity.getDescription());
		assertEquals("10:15", startTime);
		assertEquals("12:00", endTime);
	}

}
