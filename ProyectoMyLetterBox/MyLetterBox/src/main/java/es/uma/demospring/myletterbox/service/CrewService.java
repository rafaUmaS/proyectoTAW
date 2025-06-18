package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.CrewRepository;
import es.uma.demospring.myletterbox.entity.EntityCrew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewService {

    @Autowired
    CrewRepository crewRepository;

}
