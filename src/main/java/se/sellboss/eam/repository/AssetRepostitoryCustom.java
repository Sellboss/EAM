package se.sellboss.eam.repository;

import java.util.List;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.domain.AssetSearchCriteria;

public interface AssetRepostitoryCustom {
	public List<Asset> searchByCriteria(AssetSearchCriteria criteria);

	public void updateAsset(String id, String key, String value);

}
