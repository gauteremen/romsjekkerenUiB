
package no.uib.gre002.info233.v2015.oblig2.gui.controllers;

import no.uib.gre002.info233.v2015.oblig2.gui.ScreenPane;



/**
 * Methods all controllers must have.
 *
 * @author Gaute Gjerløw Remen
 * @version 1.0
 */
public interface ScreenController {
    
	/**
	 * This method will allow the injection of the ScreenPane
	 * 
	 * @param screenPage given in the specific controller
	 */
    public void setScreenPane(ScreenPane screenPage);
}
