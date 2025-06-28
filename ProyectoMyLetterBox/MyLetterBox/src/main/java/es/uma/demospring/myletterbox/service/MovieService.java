package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.*;
import es.uma.demospring.myletterbox.dto.MovieDTO;
import es.uma.demospring.myletterbox.entity.*;
import es.uma.demospring.myletterbox.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Autor(es): Ivan Pedraza Díez (70%), Álvaro Sierra García (20%), Adrián Huete Peña (10%)
*/

@Service
public class MovieService extends DTOService<MovieDTO, EntityMovie> {

    @Autowired private GeneroService generoService;

    @Autowired private MovieRepository movieRepository;

    @Autowired private GenreRepository genreRepository;

    @Autowired private CompanieRepository companieRepository;

    @Autowired private CountryRepository countryRepository;

    public List<MovieDTO> listarMovies(){
        List<EntityMovie> movies = movieRepository.findAll();
        return this.entity2DTO(movies);
    }

    public List<MovieDTO> filtrarYOrdenarPeliculas(Filtro filtro, String campoOrden, boolean asc) {

        String nombre = null, idioma = null, genero = null;

        if (filtro != null && filtro.getColumnaFiltro()!=null) {
            switch (filtro.getColumnaFiltro()) {
                case "NOMBRE":
                    nombre = filtro.getNombre();
                    break;
                case "IDIOMA":
                    idioma = filtro.getNombre();
                    break;
                case "GENERO":
                    genero = filtro.getNombre();
                    break;
            }
        }

        if (campoOrden!= null && !campoOrden.isEmpty()) {
            Sort sort = Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, campoOrden);

            List<EntityMovie> movies = movieRepository.filtrarPeliculas(nombre, idioma, genero, sort);
            return this.movieDTOList(movies);
        }else {

            List<EntityMovie> movies = movieRepository.filtrarPeliculas(nombre, idioma, genero);

            return this.movieDTOList(movies);
        }
    }

    public  MovieDTO movieDTOList (EntityMovie movie){
        List<EntityMovie> movieList = new ArrayList<>();
        movieList.add(movie);
        return this.movieDTOList(movieList).get(0);
    }

    public List<MovieDTO> movieDTOList(List<EntityMovie> movies) {
        List<MovieDTO> moviesDTO = new ArrayList<>();

        for (EntityMovie movie : movies){
            MovieDTO dto = new MovieDTO();
            dto.setMovieId(movie.getMovieId());
            dto.setName(movie.getTitle());
            dto.setOriginalTittle(movie.getOriginalTitle());
            dto.setLanguage(movie.getOriginalLanguage());
            dto.setDescription(movie.getOverview());
            dto.setPopularity(movie.getPopularity());
            dto.setRevenue(movie.getRevenue());
            dto.setBudget(movie.getBudget());
            dto.setDuration(movie.getRuntime());
            dto.setVoteNumber(movie.getVoteCount());
            dto.setVoteAverage(movie.getVoteAverage());
            dto.setDate(movie.getReleaseDate());
            dto.setEstado(movie.getStatus());

            if(movie.getGenreList() != null){
                dto.setGenerosDTO(this.generoService.entityGenreList2DTO(movie.getGenreList()));
            }

            if(movie.getCrewList() != null){
                List<Integer> crewIdList = new ArrayList<>();
                for(EntityCrew crew : movie.getCrewList()){
                    crewIdList.add(crew.getId());
                }
                dto.setCrewList(crewIdList);
            }
            moviesDTO.add(dto);

        }
        return moviesDTO;
    }

    public Map<Integer,MovieDTO> listarMoviesDTO() {
        List<EntityMovie> entidades = movieRepository.findAll();
        Map<Integer, MovieDTO> agrupado = new HashMap<>();

        for (EntityMovie e : entidades) {
            MovieDTO dto = new MovieDTO();
            dto.setMovieId(e.getMovieId());
            dto.setName(e.getName());
            dto.setVoteAverage(e.getVoteAverage());

            agrupado.put(e.getMovieId(), dto);
        }

        return agrupado;
    }

    public MovieDTO buscarMovieById(Integer id){
        return this.movieDTOList(this.movieRepository.findById(id).orElse(null));
    }

    public MovieDTO getMovieDTOById(Integer id){
        EntityMovie movie = this.movieRepository.findById(id).orElse(null);

        if(movie == null){
            return null;
        } else {
            MovieDTO dto = new MovieDTO();

            dto.setMovieId(movie.getMovieId());
            dto.setName(movie.getTitle());
            dto.setOriginalTittle(movie.getOriginalTitle());
            dto.setLanguage(movie.getOriginalLanguage());
            dto.setDescription(movie.getOverview());
            dto.setPopularity(movie.getPopularity());
            dto.setRevenue(movie.getRevenue());
            dto.setBudget(movie.getBudget());
            dto.setDuration(movie.getRuntime());
            dto.setVoteNumber(movie.getVoteCount());
            dto.setVoteAverage(movie.getVoteAverage());
            dto.setDate(movie.getReleaseDate());
            dto.setEstado(movie.getStatus());

            dto.setGeneros(new ArrayList<>());
            if(movie.getGenreList() != null){
                for(EntityGenre g : movie.getGenreList()){
                    dto.getGeneros().add(g.getId());
                }
            }

            dto.setCrewList(new ArrayList<>());
            if (movie.getCrewList() != null) {
                for (EntityCrew c : movie.getCrewList()) {
                    dto.getCrewList().add(c.getId());
                }
            }

            dto.setEmpresas(new ArrayList<>());
            if (movie.getProductionCompaniesList() != null) {
                for (EntityProductionCompanies pc : movie.getProductionCompaniesList()) {
                    dto.getEmpresas().add(pc.getId());
                }
            }

            dto.setPaises(new ArrayList<>());
            if (movie.getProductionCountriesList() != null) {
                for (EntityProductionCountries country : movie.getProductionCountriesList()) {
                    dto.getPaises().add(country.getIso31661());
                }
            }

            return dto;
        }
    }

    public void guardarMovie(MovieDTO dto) {
        EntityMovie movie;
        if(dto.getMovieId() == null){
            movie = new EntityMovie();
        } else {
            movie = movieRepository.findById(dto.getMovieId()).orElse(null);
            if(movie == null){
                return;
            }
        }

        movie.setName(dto.getName());
        movie.setTitle(dto.getName());
        movie.setOriginalTitle(dto.getOriginalTittle());
        movie.setOriginalLanguage(dto.getLanguage());
        movie.setOverview(dto.getDescription());
        movie.setPopularity(dto.getPopularity() != null ? dto.getPopularity() : 0);
        movie.setRevenue(dto.getRevenue() != null ? dto.getRevenue() : 0);
        movie.setBudget(dto.getBudget() != null ? dto.getBudget() : 0);
        movie.setRuntime(dto.getDuration() != null ? dto.getDuration() : 0);
        movie.setVoteCount(dto.getVoteNumber() != null ? dto.getVoteNumber() : 0);
        movie.setVoteAverage(dto.getVoteAverage() != null ? dto.getVoteAverage() : 0.0);
        movie.setReleaseDate(dto.getDate());
        movie.setStatus(dto.getEstado());

        if (dto.getGeneros() != null) {
            List<EntityGenre> generos = this.genreRepository.findAllById(dto.getGeneros());
            movie.setGenreList(generos);
        }

        if (dto.getEmpresas() != null) {
            List<EntityProductionCompanies> companies = this.companieRepository.findAllById(dto.getEmpresas());
            movie.setProductionCompaniesList(companies);
        }

        if (dto.getPaises() != null) {
            List<EntityProductionCountries> countries = this.countryRepository.findAllById(dto.getPaises());
            movie.setProductionCountriesList(countries);
        }

        movieRepository.save(movie);
    }

    public void borrarMovieById(Integer id) {
        this.movieRepository.deleteById(id);
    }
}
