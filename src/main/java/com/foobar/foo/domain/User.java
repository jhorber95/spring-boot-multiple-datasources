package com.foobar.foo.domain;


import javax.persistence.*;

@Entity
@Table(name = "userTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="foo_id_seq")
    @SequenceGenerator(name="foo_id_seq", sequenceName="foo_id_seq", allocationSize=1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
