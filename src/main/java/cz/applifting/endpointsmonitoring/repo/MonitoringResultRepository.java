package cz.applifting.endpointsmonitoring.repo;

import cz.applifting.endpointsmonitoring.entity.MonitoringResult;
import cz.applifting.endpointsmonitoring.model.MonitoredEndpointInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@RestResource(exported = false)
@Repository
public interface MonitoringResultRepository extends CrudRepository<MonitoringResult, Long> {
	List<MonitoredEndpointInfo.MonitoringResultInfo> findAllByMonitoredEndpointIdAndMonitoredEndpoint_User_AccessToken(Long id, UUID accessToken, Pageable pageable);
}