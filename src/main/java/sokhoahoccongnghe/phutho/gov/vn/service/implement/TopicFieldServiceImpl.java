package sokhoahoccongnghe.phutho.gov.vn.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.dto.TopicFieldDto;
import sokhoahoccongnghe.phutho.gov.vn.entity.TopicField;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.mapper.TopicFieldMapper;
import sokhoahoccongnghe.phutho.gov.vn.repository.TopicFieldRepository;
import sokhoahoccongnghe.phutho.gov.vn.service.TopicFieldService;

import java.util.List;

@Service
public class TopicFieldServiceImpl implements TopicFieldService {

    @Autowired
    private TopicFieldRepository fieldRepository;
    @Autowired
    private TopicFieldMapper fieldMapper;

    @Override
    public void updateField(Integer id, TopicFieldDto fieldRequest) {
        TopicField fieldEntity = this.getFieldEntity(id);
        fieldEntity.setTitle(fieldRequest.getTitle());
        fieldEntity.setDescription(fieldRequest.getDescription());
        fieldRepository.save(fieldEntity);
    }

    @Override
    public TopicFieldDto createField(TopicFieldDto fieldRequest) {
        TopicField fieldRequestEntity =  fieldMapper.dto2Entity(fieldRequest);
        TopicField fieldCreated = fieldRepository.save(fieldRequestEntity);
        return fieldMapper.entity2Dto(fieldCreated) ;
    }

    @Override
    public List<TopicFieldDto> getFields() {
        List<TopicField> topicFieldList = fieldRepository.findAll();
        return fieldMapper.listEntity2Dto(topicFieldList) ;
    }

    @Override
    public void deleteField(Integer id) {
        TopicField fieldEntity = this.getFieldEntity(id);
        fieldRepository.delete(fieldEntity);
    }

    @Override
    public TopicFieldDto getField(Integer id) {
        TopicField fieldEntity = this.getFieldEntity(id);
        return fieldMapper.entity2Dto(fieldEntity);
    }

    public TopicField getFieldEntity(Integer id){
        return fieldRepository.findById(id)
                .orElseThrow(()->new NotFoundException(id,"topic field"));
    }
}
