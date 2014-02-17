package se.sellboss.eam.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import se.sellboss.eam.domain.User;
import se.sellboss.eam.domain.UserSearchCriteria;
import se.sellboss.eam.repository.UserRepositoryCustom;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<User> searchByCriteria(UserSearchCriteria criteria) {
		Query query = new Query();
		if (StringUtils.hasText(criteria.getUsername())) {
			Criteria c = Criteria.where("username").is(criteria.getUsername());
			query.addCriteria(c);
		}
		return mongoTemplate.find(query, User.class);
	}
}
