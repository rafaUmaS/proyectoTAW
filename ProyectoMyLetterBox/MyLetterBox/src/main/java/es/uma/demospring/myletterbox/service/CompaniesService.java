package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.CompanieRepository;
import es.uma.demospring.myletterbox.dto.CompanieDTO;
import es.uma.demospring.myletterbox.entity.EntityProductionCompanies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Autor(es): Álvaro Sierra García (100%)
 */

@Service
public class CompaniesService extends DTOService<CompanieDTO, EntityProductionCompanies>{

    @Autowired private CompanieRepository companieRepository;

    public List<CompanieDTO> listarCompanies (){
        List<EntityProductionCompanies> companies = this.companieRepository.findAll();
        return this.entity2DTO(companies);
    }

    public CompanieDTO buscarCompanieById(Integer id){
        EntityProductionCompanies companie = this.companieRepository.findById(id).orElse(null);
        if (companie != null) {
            return companie.toDTO();
        } else {
            return new CompanieDTO();
        }
    }

    public void guardarCompanie(CompanieDTO companieDTO) {
        if(companieDTO.getId() == null){
            EntityProductionCompanies companie = new EntityProductionCompanies();
            companie.setName(companieDTO.getName());
            this.companieRepository.save(companie);
        } else {
            EntityProductionCompanies companie = this.companieRepository.findById(companieDTO.getId()).orElse(null);
            if(companie != null){
                companie.setName(companieDTO.getName());
                this.companieRepository.save(companie);
            }
        }
    }

    public void eliminarCompanieById(Integer id) {
        this.companieRepository.deleteById(id);
    }
}
