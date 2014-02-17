package se.sellboss.eam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import se.sellboss.eam.domain.User;

@Service
public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);

}
