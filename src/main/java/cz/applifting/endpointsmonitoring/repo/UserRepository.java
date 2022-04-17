package cz.applifting.endpointsmonitoring.repo;

import cz.applifting.endpointsmonitoring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByAccessToken(UUID accessToken);
}