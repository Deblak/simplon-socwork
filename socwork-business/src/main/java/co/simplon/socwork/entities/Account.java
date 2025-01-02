package co.simplon.socwork.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_accounts")
public final class Account extends AbstractEntity {

    @Column(name = "username")
    private final String username;

    @Column(name = "password")
    private final String password;

    @ManyToMany(fetch = FetchType.EAGER) // fetch for lazy loading (see toString)
    @JoinTable(name = "t_account_roles", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Account(String username, String password) {
	this(username, password, new HashSet<Role>());

    }

    private Account() {
	this.username = null;
	this.password = null;
	this.roles = new HashSet<>();
    }

    public Account(String username, String password, Set<Role> roles) {
	Objects.requireNonNull(username);
	Objects.requireNonNull(password);
	Objects.requireNonNull(roles);
	this.username = username;
	this.password = password;
	this.roles = new HashSet<>(roles);
	for (Role role : roles) {
	    addAccountRole(role);
	}
    }

    private void addAccountRole(Role role) {
	Objects.requireNonNull(roles);
	roles.add(role);
    }

    public String getUsername() {
	return username;
    }

    public String getPassword() {
	return password;
    }

    public Set<Role> getRoles() {
	return Collections.unmodifiableSet(roles);
    }

    public void setRoles(Set<Role> roles) {
	this.roles = roles;
    }

    @Override
    public int hashCode() {
	return Objects.hash(username, roles);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Account)) {
	    return false;
	}
	return obj instanceof Account other && username.equals(other.username);
    }

    // No expose collections -> so add lazy loading
    @Override
    public String toString() {
	return "Account [username=" + username + ", roles= LAZY_LOADED ]";
    }

}
