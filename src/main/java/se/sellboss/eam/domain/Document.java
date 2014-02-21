package se.sellboss.eam.domain;

import java.util.List;

/**
 * 
 * Object used when creating Primefaces TreeTable.
 * 
 * @author Martin
 * 
 */
public class Document {

	private String key;
	private String value;
	private String type;
	private List<String> arrayValues;

	public Document(String key, String value, String type,
			List<String> arrayValues) {
		super();
		this.key = key;
		this.value = value;
		this.type = type;
		this.arrayValues = arrayValues;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getArrayValues() {
		return arrayValues;
	}

	public void setArrayValues(List<String> arrayValues) {
		this.arrayValues = arrayValues;
	}

}
