package se.sellboss.eam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.domain.AssetSearchCriteria;
import se.sellboss.eam.domain.AssetType;
import se.sellboss.eam.repository.AssetRepository;
import se.sellboss.eam.repository.AssetTypeRepository;

@Service
public class AssetService {

	@Autowired
	private AssetTypeRepository assetTypeRepository;

	@Autowired
	private AssetRepository assetRepository;

	public List<Asset> searchByCriteria(AssetSearchCriteria criteria) {
		return assetRepository.searchByCriteria(criteria);
	}

	public void saveAsset(Asset p) {
		assetRepository.save(p);
	}

	public void deleteAsset(Asset p) {
		assetRepository.delete(p);
	}

	public void updateAsset(String id, String key, String value) {
		assetRepository.updateAsset(id, key, value);
	}
	
	public void updateAssetArray(String id, String key, List<String> arrayValues){
		assetRepository.updateAssetArray(id, key, arrayValues);
	}

	public List<Asset> getAll() {
		return (List<Asset>) assetRepository.findAll();
	}

	public List<AssetType> getAllAssetTypes() {
		return (List<AssetType>) assetTypeRepository.findAll();
	}

}
