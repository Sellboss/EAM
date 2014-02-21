package se.sellboss.eam.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.domain.AssetDataModel;
import se.sellboss.eam.domain.AssetSearchCriteria;
import se.sellboss.eam.domain.AssetType;
import se.sellboss.eam.service.AssetService;

@Component
@Scope("view")
// Custom Spring scope defined in spring context. Created to match JSF view
// scope.
public class AssetSearchBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AssetService assetService;

	private AssetSearchCriteria assetCriteria = new AssetSearchCriteria();
	private List<Asset> assetList;
	private List<Asset> filteredAssets;
	private Map<String, String> assetTypeMap;
	private TreeNode root;
	private TreeNode selectedNode;
	private String selectedNodeStr;
	private Asset selectedAsset;

	private AssetDataModel assetDataModel;

	@PostConstruct
	public void init() {
		assetList = assetService.getAll();

		assetTypeMap = new TreeMap<String, String>();
		for (AssetType assetType : assetService.getAllAssetTypes()) {
			assetTypeMap.put(assetType.getId(), assetType.getName());
		}

		assetDataModel = new AssetDataModel(assetList);
	}

	public String getAssetDetailsStr(Asset asset) {

		Map<String, String> assetDetails = asset.getAssetDetails();
		StringBuilder prettyDetails = new StringBuilder();
		if (null != assetDetails && !assetDetails.entrySet().isEmpty()) {
			for (Map.Entry<String, String> entry : assetDetails.entrySet()) {
				prettyDetails.append("<b>" + entry.getKey() + ":</b> "
						+ entry.getValue() + "<br/>");
			}
		} else {
			return "No asset details found";
		}
		return prettyDetails.toString();
	}

	public void doSearchAsset(ActionEvent event) {
		assetList = assetService.searchByCriteria(assetCriteria);
		assetDataModel = new AssetDataModel(assetList);
	}

	public TreeNode getRoot() {
		if (null != selectedAsset) {
			populateTreeNode();
			selectedNodeStr = "";
		}
		return root;
	}

	public void displaySelectedSingle(ActionEvent event) {
		if (selectedNode != null) {
			setSelectedNodeStr(selectedNode.toString());
		}
	}

	public String getAssetTypeValue(String assetTypeKey) {
		return assetTypeMap.get(assetTypeKey);
	}

	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}

	public List<Asset> getFilteredAssets() {
		return filteredAssets;
	}

	public void setFilteredAssets(List<Asset> filteredAssets) {
		this.filteredAssets = filteredAssets;
	}

	public AssetSearchCriteria getAssetCriteria() {
		return assetCriteria;
	}

	public void setAssetCriteria(AssetSearchCriteria assetCriteria) {
		this.assetCriteria = assetCriteria;
	}

	public Map<String, String> getAssetTypeMap() {
		return assetTypeMap;
	}

	public void setAssetTypeMap(Map<String, String> assetTypeMap) {
		this.assetTypeMap = assetTypeMap;
	}

	public AssetDataModel getAssetDataModel() {
		return assetDataModel;
	}

	public void populateTreeNode() {
		root = new DefaultTreeNode("Root", null);

		TreeNode nodeId = new DefaultTreeNode("<b>Id:</b> " + selectedAsset.getId(), root);
		TreeNode nodeName = new DefaultTreeNode("<b>Name:</b> " + selectedAsset.getAssetName(), root);
		TreeNode nodeType = new DefaultTreeNode("<b>Type:</b>" + selectedAsset.getAssetType(), root);
		TreeNode nodeState = new DefaultTreeNode("<b>State:</b> " + selectedAsset.getAssetState(), root);
		TreeNode nodeCreatedBy = new DefaultTreeNode("<b>Created by:</b> " + selectedAsset.getCreatedBy(), root);
		TreeNode nodeCreatedDate = new DefaultTreeNode("<b>Created date:</b> " + selectedAsset.getCreatedDate(), root);
		TreeNode nodeModifiedBy = new DefaultTreeNode("<b>Modified by:</b> " + selectedAsset.getModifiedBy(), root);
		TreeNode nodeModifiedDate = new DefaultTreeNode("<b>Modified date:</b>" + selectedAsset.getModifiedDate(), root);

		TreeNode nodeDetails = new DefaultTreeNode("<b>Asset details</b>", root);
		nodeDetails.setExpanded(true);
		
		Iterator iterator = selectedAsset.getAssetDetails().entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator.next();

			if (mapEntry.getValue() instanceof String) {
				TreeNode node00 = new DefaultTreeNode("<b>"
						+ mapEntry.getKey().toString() + ":</b> "
						+ mapEntry.getValue().toString(), nodeDetails);

			} else if (mapEntry.getValue() instanceof ArrayList) {

				StringBuilder prettyList = new StringBuilder();

				for (String str : (ArrayList<String>) mapEntry.getValue()) {
					prettyList.append(str + ", ");
				}
				TreeNode node00 = new DefaultTreeNode("<b>"
						+ mapEntry.getKey().toString() + ":</b> "
						+ prettyList.toString(), nodeDetails);

			} else if (mapEntry.getValue() instanceof LinkedHashMap) {

				TreeNode node00 = new DefaultTreeNode("<b>"
						+ mapEntry.getKey().toString() + "</b>", nodeDetails);
				node00.setExpanded(true);

				for (Map.Entry<String, String> entry : ((Map<String, String>) mapEntry
						.getValue()).entrySet()) {
					TreeNode node01 = new DefaultTreeNode("<b>"
							+ entry.getKey().toString() + ":</b> "
							+ entry.getValue().toString(), node00);
				}

			}

		}
	}

	public Asset getSelectedAsset() {
		return selectedAsset;
	}

	public void setSelectedAsset(Asset selectedAsset) {
		this.selectedAsset = selectedAsset;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public String getSelectedNodeStr() {
		return selectedNodeStr;
	}

	public void setSelectedNodeStr(String selectedNodeStr) {
		this.selectedNodeStr = selectedNodeStr;
	}
	
	
}
