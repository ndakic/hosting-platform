package uns.ac.rs.hostplatserver.resource;

import lombok.*;
import org.springframework.util.ObjectUtils;
import uns.ac.rs.hostplatserver.model.LabelEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelResource {
    private String id; // NOTE: This is md5h value of id
    private String name;
    private String userId;
    private Long statusId;

    public static LabelResource entityToResource(LabelEntity labelEntity){
        if(ObjectUtils.isEmpty(labelEntity)) { return null; }
        return LabelResource.builder()
                .id(labelEntity.getMd5h())
                .name(labelEntity.getName())
//                .userId() TODO: Update later
                .statusId(labelEntity.getStatus().getId())
                .build();
    }
}
