package no.uib.gre002.info233.v2015.oblig2.io;

import java.util.List;

import no.uib.gre002.info233.v2015.oblig2.models.Activity;

import org.jsoup.nodes.Node;


public interface ParserInterface {

	/**
	 * Initializes different classes, like builders and the document
	 */
	public abstract void docToLists();

	/**
	 * Recursive parser
	 *
	 * @param node
	 *            the root node of the part of the document to be parsed
	 * @param parent
	 *            the parent is needed to add the content in the node without
	 *            children
	 * @param nodeList
	 *            the nodeList to add the nodes to
	 */
	public abstract List<Node> nodesToList(Node node, Node parent,
			List<Node> nodeList);

	public abstract List<Node> getNodeList();

	public abstract List<Activity> getActivityList();

	public abstract List<String> getDateStringList();

}