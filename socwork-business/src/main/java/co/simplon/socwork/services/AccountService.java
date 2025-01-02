package co.simplon.socwork.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.socwork.config.JwtProvider;
import co.simplon.socwork.dtos.AccountCreate;
import co.simplon.socwork.dtos.AccountLogIn;
import co.simplon.socwork.dtos.LoginResponse;
import co.simplon.socwork.entities.Account;
import co.simplon.socwork.entities.Role;
import co.simplon.socwork.repositories.AccountRepository;
import co.simplon.socwork.repositories.RoleRepository;

@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository repository;
    private final RoleRepository roleRepository;
    private final JwtProvider provider;
    private PasswordEncoder passwordEncoder;

    protected AccountService(AccountRepository repository, RoleRepository roleRepository, JwtProvider provider,
	    PasswordEncoder passwordEncoder) {
	this.repository = repository;
	this.roleRepository = roleRepository;
	this.provider = provider;
	this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void create(AccountCreate inputs) {
	Role roleDefault = roleRepository.findByName("Manager").filter(role -> role.isRoleDefault())
		.orElseThrow(() -> new IllegalStateException("RoleDefault not found"));

	Set<Role> roles = new HashSet<>();
	roles.add(roleDefault);

	Account account = new Account(inputs.username(), passwordEncoder.encode(inputs.password()),
		Set.of(roleDefault));
	repository.save(account);
    }

    public LoginResponse authenticate(AccountLogIn inputs) {
	String username = inputs.username();
	Account entity = repository.findByUsernameIgnoreCase(username)
		.orElseThrow(() -> new BadCredentialsException(username));// si null prend ce qu'il y entre corps du
	System.out.println(entity); // bloc if
	String password = inputs.password();
	String encoded = entity.getPassword();

	Set<Role> roles = entity.getRoles();
	if (roles.isEmpty()) {
	    Role defaultRole = roleRepository.findByName("ROLE_Manager")
		    .orElseThrow(() -> new IllegalStateException("Role not found"));
	    roles.add(defaultRole);
	}
	Set<String> roleName = new HashSet<>();

	for (Role role : roles) {
	    roleName.add(role.getName());
	}

	if (passwordEncoder.matches(password, encoded)) {
	    String token = provider.create(username, roleName);
	    return new LoginResponse(token, "Authentication successful");

	} else {
	    throw new BadCredentialsException(username);
	}
    }

    public Account accountInfo() {
	return repository.findAll().getLast();
    }
}
