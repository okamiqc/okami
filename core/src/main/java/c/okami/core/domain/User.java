package c.okami.core.domain;

import c.okami.core.support.hibernate.BasicDomain;

import javax.persistence.*;

/**
 * Created by qqs on 2016/10/9.
 */
@Entity
@Table(name = "user")
public class User extends BasicDomain<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String password;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
