package se.sellboss.eam.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.repository.AssetRepostitoryCustom;
import se.sellboss.eam.view.AssetSearchCriteria;

public class AssetRepositoryImpl implements AssetRepostitoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Asset> searchByCriteria(AssetSearchCriteria criteria) {
		Query query = new Query();
		if (StringUtils.hasText(criteria.getAssetId())) {
			Criteria c = Criteria.where("_id").is(criteria.getAssetId());
			query.addCriteria(c);
		}
		if (StringUtils.hasText(criteria.getAssetName())) {
			Criteria c = Criteria.where("assetName").regex(
					".*" + criteria.getAssetName() + ".*", "i");
			query.addCriteria(c);
		}

		return mongoTemplate.find(query, Asset.class);
	}
}
