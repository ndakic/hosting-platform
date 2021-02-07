package uns.ac.rs.hostplatserver.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import uns.ac.rs.hostplatserver.constant.ErrorCode;
import uns.ac.rs.hostplatserver.constant.LabelStatus;
import uns.ac.rs.hostplatserver.constant.Md5Salt;
import uns.ac.rs.hostplatserver.exception.BadRequestException;
import uns.ac.rs.hostplatserver.exception.ResourceNotExistException;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.LabelEntity;
import uns.ac.rs.hostplatserver.repository.LabelRepository;
import uns.ac.rs.hostplatserver.repository.StatusRepository;
import uns.ac.rs.hostplatserver.resource.LabelResource;
import uns.ac.rs.hostplatserver.util.DateUtil;
import uns.ac.rs.hostplatserver.util.Md5Generator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LabelService {

    @NonNull
    private final LabelRepository labelRepository;
    @NonNull
    private final StatusRepository statusRepository;

    @Transactional
    public LabelResource create(LabelResource labelResource){
        LabelEntity labelEntity = LabelEntity.builder()
                .name(labelResource.getName())
                .createDate(DateUtil.nowSystemTime())
                .status(statusRepository.getOne(LabelStatus.ACTIVE.getId()))
                .build();
        labelRepository.save(labelEntity);
        labelEntity.setMd5h(Md5Generator.generateHash(labelEntity.getId(), Md5Salt.LABEl));
        labelResource.setId(labelEntity.getMd5h());
        return labelResource;
    }

    public LabelResource get(String labelId){
        LabelEntity labelEntity = labelRepository.findOneByMd5h(labelId).orElseThrow(() ->
                new ResourceNotExistException(String.format("Label with id %s not found!", labelId), ErrorCode.NOT_FOUND));
        return LabelResource.entityToResource(labelEntity);
    }

    public List<LabelResource> getAllActive(){
        return labelRepository.findAllByStatus_Id(LabelStatus.ACTIVE.getId())
                .stream()
                .map(LabelResource::entityToResource)
                .collect(Collectors.toList());
    }

    public LabelResource delete(String labelId){
        LabelEntity labelEntity = labelRepository.findOneByMd5h(labelId).orElseThrow(() ->
                new ResourceNotExistException(String.format("Label with id %s not found!", labelId), ErrorCode.NOT_FOUND));
        labelEntity.setStatus(statusRepository.getOne(LabelStatus.DELETED.getId()));
        labelRepository.save(labelEntity);
        return LabelResource.entityToResource(labelEntity);
    }

    public LabelResource update(LabelResource labelResource){
        LabelEntity labelEntity = labelRepository.findOneByMd5h(labelResource.getId()).orElseThrow(() ->
                new ResourceNotExistException(String.format("Label with id %s not found!", labelResource.getId()), ErrorCode.NOT_FOUND));
        labelEntity.setName(labelResource.getName());
        labelRepository.save(labelEntity);
        return LabelResource.entityToResource(labelEntity);
    }
    
    public LabelEntity findOne(Long id) throws BadRequestException {
    	return this.labelRepository.findById(id)
        		.orElseThrow(
                ()-> new ResourceNotFoundException(String.format("Label with id %s not found!", id))
       );
    }
}
