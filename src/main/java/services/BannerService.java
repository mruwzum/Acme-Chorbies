package services;

import domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.BannerRepository;

import java.util.Collection;

/**
 * Created by daviddelatorre on 12/3/17.
 */
@Service
@Transactional
public class BannerService {

    // Constructors--------------------------------------------------------------------------------------

    public BannerService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private BannerRepository bannerRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Banner create() {
        Banner res;
        res = new Banner();
        return res;
    }

    public Collection<Banner> findAll() {
        Collection<Banner> res;
        res = bannerRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Banner findOne(int Banner) {
        Banner res;
        res = bannerRepository.findOne(Banner);
        Assert.notNull(res);
        return res;
    }

    public Banner save(Banner a) {
        Assert.notNull(a);
        Banner res;
        res = bannerRepository.save(a);
        return res;
    }

    public void delete(Banner a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        bannerRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------
    public void flush(){
        bannerRepository.flush();
    }
}
