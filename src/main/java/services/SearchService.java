package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.SearchRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class SearchService {

    // Managed Repository ------------------------
    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private ChorbiService chorbiService;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public SearchService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Search create() {
        Search res;
        res = new Search();
        return res;
    }

    public Search findOne(int actorId) {
        Search result;

        result = searchRepository.findOne(actorId);

        return result;
    }

    public Collection<Search> findAll() {
        Collection<Search> result;

        result = searchRepository.findAll();

        return result;
    }

    public Search save(Search actor) {
        Assert.notNull(actor);
        return searchRepository.save(actor);
    }

    public void delete(Search actor) {
        Assert.notNull(actor);
        Assert.isTrue(searchRepository.exists(actor.getId()));
        searchRepository.delete(actor);
    }

    // Other business methods -----------------------

    public void flush(){
        searchRepository.flush();
    }

    public List<Chorbi> finder(Integer age, Relationship relationship, Genre genre, Coordinate coordinate, String keyword){
        List<Chorbi> chorbis = new ArrayList<>(chorbiService.findAll());
        Integer este = new Date(System.currentTimeMillis()).getYear();
        List<Chorbi> aux = new ArrayList<>();
        if (age==null||relationship==null||genre==null||coordinate.getCity()==null||coordinate.getCountry()==null||coordinate.getState()==null||coordinate.getProvince()==null||keyword==null){
            return chorbis;
        }else {
            for (Chorbi p : chorbis){
                if ((p.getBirthDate().getYear()-este)==age||p.getGenre().equals(genre)||p.getRelationship().equals(relationship)||p.getCoordinate().getCity().equals(coordinate.getCity())||p.getCoordinate().getCountry().equals(coordinate.getCountry())||p.getCoordinate().getState().equals(coordinate.getState()) || p.getCoordinate().getProvince().equals(coordinate.getProvince())&& containsKey(chorbis,keyword)){
                    aux.add(p);
                }
            }
            return aux;
        }
    }
    private Boolean containsKey(List<Chorbi> chorbis, String keyword){
        Boolean res = false;
        for (Chorbi p : chorbis){
            if (p.getDescription().contains(keyword) || p.getCoordinate().getProvince().contains(keyword) || p.getCoordinate().getCity().contains(keyword)|| p.getCoordinate().getState().contains(keyword)||p.getCoordinate().getCountry().contains(keyword)){
                res = true;
            }
        }
        return res;
    }

}
