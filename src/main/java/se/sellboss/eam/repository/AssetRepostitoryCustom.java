package se.sellboss.eam.repository;

import java.util.List;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.view.AssetSearchCriteria;

public interface AssetRepostitoryCustom {
	public List<Asset> searchByCriteria(AssetSearchCriteria criteria);
	
}
