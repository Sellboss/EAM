package se.sellboss.eam.domain;

import java.io.Serializable;

/**
 * Class to hold properties used when querying db.
 * 
 * @author Martin
 *
 */
public class AssetSearchCriteria implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String assetId;
	private String assetName;

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

}
