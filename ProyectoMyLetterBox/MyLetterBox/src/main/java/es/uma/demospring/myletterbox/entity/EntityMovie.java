/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.demospring.myletterbox.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "movie")
public class EntityMovie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "movie_id")
    private Integer movieId;
    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "overview")
    private String overview;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "popularity")
    private Double popularity;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Column(name = "revenue")
    private Integer revenue;
    @Column(name = "runtime")
    private Integer runtime;
    @Column(name = "status")
    private String status;
    @Column(name = "title")
    private String title;
    @Column(name = "vote_average")
    private Double voteAverage;
    @Column(name = "vote_count")
    private Integer voteCount;
    @Column(name = "budget")
    private Integer budget;
    @Column(name = "original_language")
    private String originalLanguage;
    @Column(name = "original_title")
    private String originalTitle;
    @JoinTable(name = "production_companies_has_movie", joinColumns = {
        @JoinColumn(name = "Movie_movie_id", referencedColumnName = "movie_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Production_companies_id", referencedColumnName = "id")})
    @ManyToMany
    private List<EntityProductionCompanies> productionCompaniesList;
    // @ManyToMany(mappedBy = "movieList")
    @ManyToMany
    @JoinTable(name = "genre_has_movie", joinColumns =
        @JoinColumn(name = "Movie_id", referencedColumnName = "movie_id"), inverseJoinColumns = {
        @JoinColumn(name = "Genre_id", referencedColumnName = "id")})
    private List<EntityGenre> genreList;
    @JoinTable(name = "movie_has_spoken_language", joinColumns = {
        @JoinColumn(name = "Movie_id", referencedColumnName = "movie_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Spoken_language_ISO_639_1", referencedColumnName = "ISO_639_1")})
    @ManyToMany
    private List<EntitySpokenLanguage> spokenLanguageList;
    @JoinTable(name = "production_countries_has_movie", joinColumns = {
        @JoinColumn(name = "Movie_id", referencedColumnName = "movie_id")}, inverseJoinColumns = {
        @JoinColumn(name = "production_countries_ISO_3166_1", referencedColumnName = "ISO_3166_1")})
    @ManyToMany
    private List<EntityProductionCountries> productionCountriesList;
    @ManyToMany(mappedBy = "movieList")
    private List<EntityKeywords> keywordsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieMovieId")
    private List<EntityUsuarioSaveMovie> usuarioSaveMovieList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieMovieId")
    private List<EntityReview> reviewList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moviemovieid")
    private List<EntityCrew> crewList;

    public EntityMovie() {
    }

    public EntityMovie(Integer movieId) {
        this.movieId = movieId;
    }

    public EntityMovie(Integer movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<EntityProductionCompanies> getProductionCompaniesList() {
        return productionCompaniesList;
    }

    public void setProductionCompaniesList(List<EntityProductionCompanies> productionCompaniesList) {
        this.productionCompaniesList = productionCompaniesList;
    }

    public List<EntityGenre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<EntityGenre> genreList) {
        this.genreList = genreList;
    }

    public List<EntitySpokenLanguage> getSpokenLanguageList() {
        return spokenLanguageList;
    }

    public void setSpokenLanguageList(List<EntitySpokenLanguage> spokenLanguageList) {
        this.spokenLanguageList = spokenLanguageList;
    }

    public List<EntityProductionCountries> getProductionCountriesList() {
        return productionCountriesList;
    }

    public void setProductionCountriesList(List<EntityProductionCountries> productionCountriesList) {
        this.productionCountriesList = productionCountriesList;
    }

    public List<EntityKeywords> getKeywordsList() {
        return keywordsList;
    }

    public void setKeywordsList(List<EntityKeywords> keywordsList) {
        this.keywordsList = keywordsList;
    }

    public List<EntityUsuarioSaveMovie> getUsuarioSaveMovieList() {
        return usuarioSaveMovieList;
    }

    public void setUsuarioSaveMovieList(List<EntityUsuarioSaveMovie> usuarioSaveMovieList) {
        this.usuarioSaveMovieList = usuarioSaveMovieList;
    }

    public List<EntityReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<EntityReview> reviewList) {
        this.reviewList = reviewList;
    }

    public List<EntityCrew> getCrewList() {
        return crewList;
    }

    public void setCrewList(List<EntityCrew> crewList) {
        this.crewList = crewList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieId != null ? movieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntityMovie)) {
            return false;
        }
        EntityMovie other = (EntityMovie) object;
        if ((this.movieId == null && other.movieId != null) || (this.movieId != null && !this.movieId.equals(other.movieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.uma.demospring.myletterbox.entity.Movie[ movieId=" + movieId + " ]";
    }
    
}
