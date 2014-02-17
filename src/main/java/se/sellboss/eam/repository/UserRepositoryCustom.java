package se.sellboss.eam.repository;

import java.util.List;

import se.sellboss.eam.domain.User;
import se.sellboss.eam.domain.UserSearchCriteria;

public interface UserRepositoryCustom {
	public List<User> searchByCriteria(UserSearchCriteria criteria);
	
}
