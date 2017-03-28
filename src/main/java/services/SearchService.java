package services;

import domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.SearchRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class SearchService {

    // Managed Repository ------------------------
    @Autowired
    private SearchRepository searchRepository;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public SearchService() {
        super();
    }

    // Simple CRUD methods -----------------------

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
}
