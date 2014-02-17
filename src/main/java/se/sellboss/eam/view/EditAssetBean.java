package se.sellboss.eam.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.domain.Document;
import se.sellboss.eam.service.AssetService;

@Component
@Scope("view")
public class EditAssetBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AssetService assetService;

	private String assetId;
	private Asset asset;
	private TreeNode root;
	private TreeNode selectedNode;

	@PostConstruct
	public void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String assetIdParam = params.get("assetId");
		AssetSearchCriteria criteria = new AssetSearchCriteria();
		criteria.setAssetId(assetIdParam);
		asset = new Asset();
		asset = assetService.searchByCriteria(criteria).get(0);
		populateTree();
	}

	private void populateTree() {

		root = new DefaultTreeNode("root", null);

		TreeNode id = new DefaultTreeNode(new Document("Id", asset.getId(),
				asset.getId().getClass().getSimpleName().toString()), root);
		TreeNode name = new DefaultTreeNode(new Document("Name",
				asset.getAssetName(), asset.getAssetName().getClass()
						.getSimpleName().toString()), root);
		TreeNode type = new DefaultTreeNode(new Document("Type",
				asset.getAssetType(), asset.getAssetType().getClass()
						.getSimpleName().toString()), root);
		TreeNode state = new DefaultTreeNode(new Document("State",
				asset.getAssetState(), asset.getAssetState().getClass()
						.getSimpleName().toString()), root);
		TreeNode createdBy = new DefaultTreeNode(new Document("Created by",
				asset.getCreatedBy(), asset.getCreatedBy().getClass()
						.getSimpleName().toString()), root);
		TreeNode createdDate = new DefaultTreeNode(new Document("Created date",
				asset.getCreatedDate().toString(), asset.getCreatedDate()
						.getClass().getSimpleName().toString()), root);
		TreeNode modifiedBy = new DefaultTreeNode(new Document("Modified by",
				asset.getModifiedBy(), asset.getModifiedBy().getClass()
						.getSimpleName().toString()), root);
		TreeNode modifiedDate = new DefaultTreeNode(new Document(
				"Modified date", asset.getModifiedDate().toString(), asset
						.getModifiedDate().getClass().getSimpleName()
						.toString()), root);
		TreeNode assetDetails = new DefaultTreeNode(new Document(
				"Asset details", "", asset.getAssetDetails().getClass()
						.getSimpleName().toString()), root);

		Iterator iterator = asset.getAssetDetails().entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator.next();

			if (mapEntry.getValue() instanceof String) {
				TreeNode stringNode = new DefaultTreeNode(new Document(mapEntry
						.getKey().toString(), mapEntry.getValue().toString(),
						mapEntry.getValue().getClass().getSimpleName()),
						assetDetails);

			} else if (mapEntry.getValue() instanceof ArrayList) {

				StringBuilder prettyList = new StringBuilder();

				for (String str : (ArrayList<String>) mapEntry.getValue()) {
					prettyList.append(str + ", ");
				}
				TreeNode arrayNode = new DefaultTreeNode(new Document(mapEntry
						.getKey().toString(), prettyList.toString(), mapEntry
						.getValue().getClass().getSimpleName()), assetDetails);

			} else if (mapEntry.getValue() instanceof LinkedHashMap) {

				TreeNode nodeMap = new DefaultTreeNode(new Document(mapEntry
						.getKey().toString(), "", mapEntry.getValue()
						.getClass().getSimpleName().toString()), assetDetails);

				for (Map.Entry<String, String> entry : ((Map<String, String>) mapEntry
						.getValue()).entrySet()) {
					TreeNode stringNode = new DefaultTreeNode(new Document(entry
							.getKey().toString(), entry.getValue().toString(),
							entry.getValue().getClass().getSimpleName()),
							nodeMap);
				}

			}
		}

	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

}
