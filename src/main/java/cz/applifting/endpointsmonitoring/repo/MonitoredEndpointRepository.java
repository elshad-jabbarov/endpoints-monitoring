package cz.applifting.endpointsmonitoring.repo;

import cz.applifting.endpointsmonitoring.entity.MonitoredEndpoint;
import cz.applifting.endpointsmonitoring.model.MonitoredEndpointInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@Repository
@RestResource(exported = false)
public interface MonitoredEndpointRepository extends JpaRepository<MonitoredEndpoint, Long> {
	List<MonitoredEndpointInfo> findAllByUser_AccessToken(UUID accessToken);

	Optional<MonitoredEndpointInfo> findByIdAndUser_AccessToken(Long id, UUID accessToken);

}