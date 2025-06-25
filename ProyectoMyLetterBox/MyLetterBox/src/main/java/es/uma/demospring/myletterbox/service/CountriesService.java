package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.CountryRepository;
import es.uma.demospring.myletterbox.dto.CountryDTO;
import es.uma.demospring.myletterbox.entity.EntityProductionCountries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Autor(es): Álvaro Sierra García (100%)
 */

@Service
public class CountriesService extends DTOService<CountryDTO, EntityProductionCountries> {

    @Autowired private CountryRepository countryRepository;

    public List<CountryDTO> listarCountries (){
        List<EntityProductionCountries> countries = this.countryRepository.findAll();
        return this.entity2DTO(countries);
    }

    public CountryDTO buscarCountryById(String id){
        EntityProductionCountries countrie = this.countryRepository.findById(id).orElse(null);
        if (countrie != null) {
            return countrie.toDTO();
        } else {
            return new CountryDTO();
        }
    }

    public void guardarCountry(CountryDTO countryDTO) {
        EntityProductionCountries country = this.countryRepository.findById(countryDTO.getIso31661()).orElse(null);
        if(country != null){
            country.setName(countryDTO.getName());
            this.countryRepository.save(country);
        } else {
            EntityProductionCountries nuevo = new EntityProductionCountries();
            nuevo.setIso31661(countryDTO.getIso31661());
            nuevo.setName(countryDTO.getName());
            this.countryRepository.save(nuevo);
        }
    }

    public void eliminarCountryById(String id) {
        this.countryRepository.deleteById(id);
    }

    public List<CountryDTO> entityGenreList2DTO (List<EntityProductionCountries> countries){
        return this.entity2DTO(countries);
    }
}
