package se.sellboss.eam.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.CommandResult;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.domain.AssetSearchCriteria;
import se.sellboss.eam.repository.AssetRepostitoryCustom;

public class AssetRepositoryImpl implements AssetRepostitoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * Creates new AssetSearchCriteria and queries db.
	 */
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

	public void updateAsset(String id, String key, String value) {

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));

		// update.set(key, value);

		Update update = new Update().set(key, value);

		WriteResult result = mongoTemplate.updateFirst(query, update, "asset");
		
	}
}
