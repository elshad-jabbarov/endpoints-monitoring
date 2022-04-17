package cz.applifting.endpointsmonitoring.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import cz.applifting.endpointsmonitoring.entity.MonitoredEndpoint;
import cz.applifting.endpointsmonitoring.entity.MonitoringResult;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.Set;

/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Projection(
	name = "monitoredEndpoint",
	types = {MonitoredEndpoint.class})
public interface MonitoredEndpointInfo {
	Long getId();

	String getName();

	String getUrl();

	Date getCreatedDate();

	Date getLastCheck();

	Long monitoredInterval();

	Set<MonitoringResultInfo> getMonitoringResults();

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Projection(
		name = "monitoringResult",
		types = {MonitoringResult.class})
	interface MonitoringResultInfo {
		Date getDateOfCheck();

		Integer getStatusCode();

		String getPayload();
	}
}
