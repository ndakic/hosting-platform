package uns.ac.rs.hostplatserver.mapper;

import uns.ac.rs.hostplatserver.dto.LabelDTO;
import uns.ac.rs.hostplatserver.model.LabelEntity;

public class LabelMapper {
	
	public static LabelDTO toDTO(LabelEntity label) {
		return new LabelDTO(label.getId());
	}
	
	public static LabelEntity toLabel(LabelDTO labelDto) {
		return new LabelEntity(labelDto.getId());
	}


}
