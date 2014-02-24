package se.sellboss.eam.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

/**
 * Domain class mapping to the collection eam.asset in MongoDB.
 * 
 * 
 * @author Martin
 *
 */

public class Asset  implements Serializable{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String assetName;
	private String assetType;
	private String assetState;
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	private Map assetDetails;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetState() {
		return assetState;
	}

	public void setAssetState(String assetState) {
		this.assetState = assetState;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Map getAssetDetails() {
		return assetDetails;
	}

	public void setAssetDetails(Map assetDetails) {
		this.assetDetails = assetDetails;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
