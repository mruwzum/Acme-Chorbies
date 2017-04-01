package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.SearchCacheRepository;
import repositories.SearchRepository;

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
public class SearchCacheService {

    // Managed Repository ------------------------
    @Autowired
    private SearchCacheRepository searchCacheRepository;


    // Supporting services -----------------------

    // Constructor -------------------------------
    public SearchCacheService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public SearchCache create() {
        SearchCache res;
        res = new SearchCache();
        return res;
    }

    public SearchCache findOne(int actorId) {
        SearchCache result;

        result = searchCacheRepository.findOne(actorId);

        return result;
    }

    public Collection<SearchCache> findAll() {
        Collection<SearchCache> result;

        result = searchCacheRepository.findAll();

        return result;
    }

    public SearchCache save(SearchCache actor) {
        Assert.notNull(actor);
        return searchCacheRepository.save(actor);
    }

    public void delete(SearchCache actor) {
        Assert.notNull(actor);
        Assert.isTrue(searchCacheRepository.exists(actor.getId()));
        searchCacheRepository.delete(actor);
    }

    // Other business methods -----------------------

    public void flush(){
        searchCacheRepository.flush();
    }


}
