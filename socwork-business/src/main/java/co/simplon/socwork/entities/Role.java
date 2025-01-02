package co.simplon.socwork.entities;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_roles")
public final class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private final String name;

    @Column(name = "role_default")
    private final boolean roleDefault;

    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts;

    public Long getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public boolean isRoleDefault() {
	return roleDefault;
    }

    public Role() {
	this.name = null;
	this.roleDefault = false;
    }

    protected Role(Long id, String name, boolean roleDefault, Set<Account> accounts) {
	super();
	this.id = id;
	this.name = name;
	this.roleDefault = roleDefault;
	this.accounts = accounts;
    }

    @SuppressWarnings("unused")
    public void setId(Long id) {
//	this.id = id; genareted by db
    }

    @Override
    public String toString() {
	return "AccountRole [id=" + id + ", name=" + name + ", roleDefault=" + roleDefault + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(name, roleDefault);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof Role other && name.equals(other.name);
    }

    public Role(String name, boolean roleDefault) {
	Objects.requireNonNull(name);
	this.name = name;
	this.roleDefault = roleDefault;
    }
}
