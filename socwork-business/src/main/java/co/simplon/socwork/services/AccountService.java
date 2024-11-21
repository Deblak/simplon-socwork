package co.simplon.socwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.socwork.config.JwtProvider;
import co.simplon.socwork.dtos.AccountCreate;
import co.simplon.socwork.dtos.AccountLogIn;
import co.simplon.socwork.entities.Account;
import co.simplon.socwork.repositories.AccountRepository;

@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository repository;

    private final JwtProvider provider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    protected AccountService(AccountRepository repository, JwtProvider provider) {
	super();
	this.repository = repository;
	this.provider = provider;
    }

    @Transactional
    public void create(AccountCreate inputs) {
	Account account = new Account();
	account.setUsername(inputs.username());
	account.setPassword(passwordEncoder.encode(inputs.password()));
	repository.save(account);
    }

    public Object authenticated(AccountLogIn inputs) {
	String username = inputs.username();
	String password = inputs.password();

	Account entity = repository.findByUsernameIgnoreCase(username)
		.orElseThrow(() -> new BadCredentialsException(username));

	if (!passwordEncoder.matches(password, entity.getPassword())) {
	    throw new BadCredentialsException(username);
	}

	String sessionProvider = provider.create(username);
	// return authentication token (unique identifier)
	return sessionProvider;
    }
}
