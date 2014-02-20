package se.sellboss.eam.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.domain.AssetSearchCriteria;
import se.sellboss.eam.domain.Document;
import se.sellboss.eam.service.AssetService;

/**
 * Bean used when editing an existing asset.
 * 
 * @author Martin
 * 
 */
@Component
@Scope("view")
// Custom Spring scope defined in spring context. Created to match JSF view
// scope.
public class EditAssetBean implements Serializable {

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
	private Asset editedAsset;

	/**
	 * After bean construction, get 'assetId' set in request, perform a fetch
	 * from db and get the selected asset, then populate the model for
	 * TreeTable.
	 */
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

		// Set all static asset properties.
		TreeNode id = new DefaultTreeNode(new Document("id", asset.getId(),
				asset.getId().getClass().getSimpleName().toString()), root);
		TreeNode name = new DefaultTreeNode(new Document("assetName",
				asset.getAssetName(), asset.getAssetName().getClass()
						.getSimpleName().toString()), root);
		TreeNode type = new DefaultTreeNode(new Document("assetType",
				asset.getAssetType(), asset.getAssetType().getClass()
						.getSimpleName().toString()), root);
		TreeNode state = new DefaultTreeNode(new Document("assetState",
				asset.getAssetState(), asset.getAssetState().getClass()
						.getSimpleName().toString()), root);
		TreeNode createdBy = new DefaultTreeNode(new Document("createdBy",
				asset.getCreatedBy(), asset.getCreatedBy().getClass()
						.getSimpleName().toString()), root);
		TreeNode createdDate = new DefaultTreeNode(new Document("createdDate",
				asset.getCreatedDate().toString(), asset.getCreatedDate()
						.getClass().getSimpleName().toString()), root);
		TreeNode modifiedBy = new DefaultTreeNode(new Document("modifiedBy",
				asset.getModifiedBy(), asset.getModifiedBy().getClass()
						.getSimpleName().toString()), root);
		TreeNode modifiedDate = new DefaultTreeNode(new Document(
				"modifiedDate", asset.getModifiedDate().toString(), asset
						.getModifiedDate().getClass().getSimpleName()
						.toString()), root);
		TreeNode assetDetails = new DefaultTreeNode(new Document(
				"assetDetails", "", asset.getAssetDetails().getClass()
						.getSimpleName().toString()), root);
		assetDetails.setSelectable(false);
		assetDetails.setExpanded(true);

		// Start iterating objects in assetDetails.
		Iterator iterator = asset.getAssetDetails().entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator.next();

			// If String, set the key and value.
			if (mapEntry.getValue() instanceof String) {
				TreeNode stringNode = new DefaultTreeNode(new Document(mapEntry
						.getKey().toString(), mapEntry.getValue().toString(),
						mapEntry.getValue().getClass().getSimpleName()),
						assetDetails);

				// If Array, set the key and create String to set the Array
				// strings.
			} else if (mapEntry.getValue() instanceof ArrayList) {

				StringBuilder prettyList = new StringBuilder();

				for (String str : (ArrayList<String>) mapEntry.getValue()) {
					prettyList.append(str + ", ");
				}
				TreeNode arrayNode = new DefaultTreeNode(new Document(mapEntry
						.getKey().toString(), prettyList.toString(), mapEntry
						.getValue().getClass().getSimpleName()), assetDetails);

				// If LinkedHashMap, set the key and look for children (only
				// strings).
			} else if (mapEntry.getValue() instanceof LinkedHashMap) {

				TreeNode nodeMap = new DefaultTreeNode(new Document(mapEntry
						.getKey().toString(), "", mapEntry.getValue()
						.getClass().getSimpleName().toString()), assetDetails);
				nodeMap.setSelectable(false);
				nodeMap.setExpanded(true);

				for (Map.Entry<String, String> entry : ((Map<String, String>) mapEntry
						.getValue()).entrySet()) {
					TreeNode stringNode = new DefaultTreeNode(new Document(
							entry.getKey().toString(), entry.getValue()
									.toString(), entry.getValue().getClass()
									.getSimpleName()), nodeMap);
				}

			}
		}

	}

	public void doSave(ActionEvent event) {

		try {
			Document doc = (Document) selectedNode.getData();

			if (selectedNode.getParent().getData().equals("root")) {
				assetService.updateAsset(asset.getId(), doc.getKey(),
						doc.getValue());
			} else {

				List<String> keyList = new ArrayList<String>();
				StringBuilder keyString = new StringBuilder("");
				TreeNode node = selectedNode;
				while (null != selectedNode.getParent()
						&& !node.getParent().getData().equals("root")) {
					keyList.add(((Document) node.getParent().getData())
							.getKey());
					node = node.getParent();
				}
				Collections.reverse(keyList);
				for (String string : keyList) {
					keyString.append(string + ".");
				}
				keyString.append(doc.getKey());
				String key = keyString.toString();
				assetService.updateAsset(asset.getId(), key, doc.getValue());
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
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

	public Asset getEditedAsset() {
		return editedAsset;
	}

	public void setEditedAsset(Asset editedAsset) {
		this.editedAsset = editedAsset;
	}

}
