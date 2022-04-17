package cz.applifting.endpointsmonitoring.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "monitoring_result")
@Data
@EntityListeners(AuditingEntityListener.class)
public class MonitoringResult {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private LocalDateTime dateOfCheck;

	private Integer statusCode;

	@Lob
	private String payload;

	@ManyToOne
	@JoinColumn(name = "monitored_endpoint_id")
	private MonitoredEndpoint monitoredEndpoint;

	public String getUrl(){
		return monitoredEndpoint.getUrl();
	}
}