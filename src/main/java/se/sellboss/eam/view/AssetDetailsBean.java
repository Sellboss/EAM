package se.sellboss.eam.view;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.domain.AssetType;
import se.sellboss.eam.service.AssetService;

/**
 * Managed bean used when creating a new asset
 * 
 * 
 * @author Martin
 *
 */
@Component
@Scope("session")
public class AssetDetailsBean {

	public AssetDetailsBean() {
		asset = new Asset();
	}

	@Autowired
	private AssetService assetService;
	private boolean newAsset;
	private Asset asset;
	private Map<String, String> assetTypeMap;

	/**
	 * Populate map of asset types from db after bean construction.
	 */
	@PostConstruct
	public void init() {
		assetTypeMap = new TreeMap<String, String>();
		for (AssetType assetType : assetService.getAllAssetTypes()) {
			assetTypeMap.put(assetType.getName(), assetType.getId());
		}
	}

	public void doSave(ActionEvent event) {

		try {
			assetService.saveAsset(asset);

			//Print growl message on success.
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Save Successful!"));
		} catch (DataAccessException e) {
			e.printStackTrace();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error when saving asset!",
					null));

		}
	}

	public boolean isNewAsset() {
		return newAsset;
	}

	public void setNewAsset(boolean newAsset) {
		this.newAsset = newAsset;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Map<String, String> getAssetTypeMap() {
		return assetTypeMap;
	}

	public void setAssetTypeMap(Map<String, String> assetTypeMap) {
		this.assetTypeMap = assetTypeMap;
	}
}
