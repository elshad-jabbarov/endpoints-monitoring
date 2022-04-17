package cz.applifting.endpointsmonitoring.service;


import cz.applifting.endpointsmonitoring.entity.MonitoredEndpoint;
import cz.applifting.endpointsmonitoring.model.MonitoredEndpointInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
public interface EndpointService {
	MonitoredEndpointInfo findEndpointById(long id, UUID accessToken);

	List<MonitoredEndpointInfo> findAllEndpoints(UUID accessToken);

	MonitoredEndpoint createEndpoint(MonitoredEndpoint endpoint, UUID accessToken);

	MonitoredEndpoint updateEndpoint(long id, MonitoredEndpoint endpoint, UUID accessToken);

	void deleteEndpoint(long id, UUID accessToken);

	List<MonitoredEndpointInfo.MonitoringResultInfo> findResultsForEndpoint(Long endpointId, UUID accessToken, Pageable pageable);

}
