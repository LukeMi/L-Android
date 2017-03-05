package com.tbug.android.mlibrary.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The {@code XMLToJSON} class creates an object from XML
 *
 * @author adamopan
 * @version 0.1
 * @since 0.1
 */

public class XMLToJSON {

	/**
	 * {@code XMLToJSON} is a singleton. instance contains it's instance.
	 */
	private static XMLToJSON instance = new XMLToJSON();

	/**
	 * Constructor for the singleton {@code XMLToJSON}
	 */
	protected XMLToJSON() {

	}

	/**
	 * @return the instance
	 */
	public static XMLToJSON getInstance() {
		return instance;
	}

	/**
	 * Method used to retrieve a matching node from the xml
	 *
	 * @param node
	 *            the parent node to search
	 * @param matchItem
	 *            the node name to match on
	 * @return the list of nodes which passed the match criteria
	 */
	private List<Node> browseChildren(Node node, String matchItem) {
		matchItem = matchItem.trim().toLowerCase();
		NodeList nodeList = node.getChildNodes();
		LinkedList<Node> nodes = new LinkedList<>();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node innerNode = nodeList.item(i);

			if (innerNode.getNodeName().trim().toLowerCase().equals(matchItem)) {
				nodes.add(innerNode);
			}
		}

		return nodes;

	}

	/**
	 * Method used to convert xml to json
	 *
	 * @param filePath
	 *            the xml file path
	 * @param tagName
	 *            a tagName to locate
	 * @return true file loading succeeded
	 */
	public String getJSONFromXML(String filePath, String tagName) {
		try {
			File xmlConfigFile = new File(filePath);
			InputStream xmlInputStream = new FileInputStream(xmlConfigFile);
			String json = this.getJSONFromXML(xmlInputStream, tagName);
			xmlInputStream.close();
			return json;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Method used to extract text content of xml tag attributes
	 *
	 * @param node
	 *            the node attributes to examine
	 * @param match
	 *            the node attribute from which to extract it's text content
	 * @return trimmed text content of xml tag attributes
	 */
	private String getTagAttributes(Node node, String match) {
		Node attributeNode = node.getAttributes().getNamedItem(match);
		String textContent = null;
		if (attributeNode != null)
			textContent = attributeNode.getTextContent();// .trim();

		return textContent;
	}

	/**
	 * Method used to extract attributes from XML
	 *
	 * @param value
	 *            the parent node to explore
	 * @return the attributes in json
	 * 
	 */
	private Object getObjectsFromXML(Node value) {

		if (value == null)
			return null;

		String argValue = value.getTextContent();

		NodeList nodeList = value.getChildNodes();

		Map<String, List<Object>> members = new LinkedHashMap<>();

		for (int i = 0; i < nodeList.getLength(); i++) {

			Node innerNode = nodeList.item(i);

			if (innerNode.getNodeType() == Node.ELEMENT_NODE) {

				// TODO: xml tag attributes
				/*
				 * NamedNodeMap innerNodeAttributes = innerNode.getAttributes();
				 * for (int j = 0; j < innerNodeAttributes.getLength(); j++) {
				 * 
				 * String nodeAttribute =
				 * innerNodeAttributes.item(j).getNodeName().trim().toLowerCase(
				 * ); String nodeAttributeValue =
				 * innerNodeAttributes.item(j).getTextContent(); }
				 */

				String tagName = innerNode.getNodeName();// .trim().toLowerCase();

				if (!members.containsKey(tagName))
					members.put(tagName, new LinkedList<Object>());

				members.get(tagName).add(getObjectsFromXML(innerNode));

			}

		}

		if (members.isEmpty())
			return argValue;

		return members;

	}



	/**
	 * Method used to convert xml to json
	 *
	 * @param xmlInputStream
	 *            the input stream containing the xml data
	 * @param tagName
	 *            a tagName to locate
	 * @return true when loading succeeded
	 */
	public String getJSONFromXML(InputStream xmlInputStream, String tagName) {



		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlInputStream);

			doc.getDocumentElement().normalize();

			List<Node> definitions = this.browseChildren(doc, tagName);


			if(!definitions.isEmpty()) {

				Object members = getObjectsFromXML(definitions.get(0));
		
//				ObjectMapper mapper = new ObjectMapper();
							
//				return mapper.writeValueAsString(members);
			}

		

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {

		XMLToJSON xml2json = XMLToJSON.getInstance();

		String json = xml2json.getJSONFromXML("./examples/kpiml.xml", "KPIDefinition");

		System.out.println(json);
	}
}
