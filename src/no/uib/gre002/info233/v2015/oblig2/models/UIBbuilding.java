package no.uib.gre002.info233.v2015.oblig2.models;

/**
 * Class for making a building object
 * 
 * @author Gaute Gjerløw Remen
 * @version 1.0
 *
 */
public class UIBbuilding {

	private String name;
	
	public UIBbuilding(String name) {
		this.name= name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
