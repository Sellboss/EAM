package se.sellboss.eam.domain;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class AssetDataModel extends ListDataModel<Asset> implements
		SelectableDataModel<Asset>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssetDataModel() {
	}

	public AssetDataModel(List<Asset> data) {
		super(data);
	}

	@Override
	public Asset getRowData(String rowKey) {
		// In a real app, a more efficient way like a query by rowKey should be
		// implemented to deal with huge data

		List<Asset> assetList = (List<Asset>) getWrappedData();

		for (Asset asset : assetList) {
			if (asset.getId().equals(rowKey))
				return asset;
		}

		return null;
	}

	@Override
	public Object getRowKey(Asset asset) {
		return asset.getId();
	}

}
