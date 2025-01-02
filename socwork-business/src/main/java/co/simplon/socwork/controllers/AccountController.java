package co.simplon.socwork.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.socwork.dtos.AccountCreate;
import co.simplon.socwork.dtos.AccountLogIn;
import co.simplon.socwork.entities.Account;
import co.simplon.socwork.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody AccountCreate inputs) {
	service.create(inputs);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    Object authentificated(@RequestBody AccountLogIn inputs) {
	return service.authenticate(inputs);
    }

    @GetMapping("/with-role")
    Object withRole() {
	return "with role";
    }

    @GetMapping("/display-last")
    public Account entitiesDisplay(Long id) {
	return service.accountInfo();
    }

}