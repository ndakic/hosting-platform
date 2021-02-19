package uns.ac.rs.hostplatserver.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uns.ac.rs.hostplatserver.resource.LabelResource;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LabelTaskDTO {
	public Long task_id;
	public Set<LabelResource> labels;

}