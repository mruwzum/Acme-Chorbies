package services;

import domain.Actor;
import domain.Chorbi;
import domain.Coordinate;
import domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class ActorService {

	// Managed Repository ------------------------
	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private ChorbiService chorbiService;
	@Autowired
	private CoordinateService coordinateService;
	@Autowired
	private CreditCardService creditCardService;
    // Supporting services -----------------------

    // Constructor -------------------------------
    public ActorService() {
        super();
    }

	// Simple CRUD methods -----------------------

	public Actor findOne(int actorId) {
		Actor result;

		result = actorRepository.findOne(actorId);

		return result;
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = actorRepository.findAll();

		return result;
	}

	public Actor save(Actor actor) {
		Assert.notNull(actor);
		return actorRepository.save(actor);
	}

	public void delete(Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actorRepository.exists(actor.getId()));
		actorRepository.delete(actor);
	}

	// Other business methods -----------------------

	public Actor registerAsAChorbi(Chorbi u) {
		Assert.notNull(u);
		Authority autoh = new Authority();
		autoh.setAuthority("CHORBI");
		UserAccount res = new UserAccount();
		res.addAuthority(autoh);
		res.setUsername(u.getUserAccount().getUsername());
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		String hash = encoder.encodePassword(u.getUserAccount().getPassword(), null);
		res.setPassword(hash);
		UserAccount userAccount = userAccountService.save(res);
		u.setUserAccount(userAccount);
		Assert.notNull(u.getUserAccount().getAuthorities(),"authorities null al registrar");

		Date date = new Date(System.currentTimeMillis()-1000);

//TODO capturar excepción cuando es menor
		Assert.isTrue(u.getAge() < 18,"Menor de edad / Under age");

		Coordinate coordinate = new Coordinate();
		coordinate.setCity(u.getCoordinate().getCity());
		coordinate.setCountry(u.getCoordinate().getCountry());
		coordinate.setProvince(u.getCoordinate().getProvince());
		coordinate.setState(u.getCoordinate().getState());
		Coordinate savec = coordinateService.save(coordinate);
		u.setCoordinate(savec);


		u.setBanned(false);


		Chorbi resu = chorbiService.save(u);
		return resu;
	}


    public Actor findByPrincipal() {
        Actor result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    private Actor findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Actor result;

        result = actorRepository.findByUserAccountId(userAccount.getId());

        return result;
    }

	public void flush(){
		actorRepository.flush();
	}

}