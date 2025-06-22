package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.CastRepository;
import es.uma.demospring.myletterbox.dto.CastDTO;
import es.uma.demospring.myletterbox.entity.EntityCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastService extends DTOService<CastDTO, EntityCast> {

    @Autowired protected CastRepository castRepository;

    public List<CastDTO> castEntityList2DTO(List<EntityCast> castList){
        return this.entity2DTO(castList);
    }

}
