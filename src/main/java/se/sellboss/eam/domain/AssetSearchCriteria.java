package se.sellboss.eam.domain;

/**
 * Class to hold properties used when querying db.
 * 
 * @author Martin
 *
 */
public class AssetSearchCriteria {
	
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
