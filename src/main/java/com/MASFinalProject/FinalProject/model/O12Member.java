package com.MASFinalProject.FinalProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "PersonId")
public class O12Member extends Person {
    @NotBlank
    @Column(unique = true)
    private String login;
    @NotBlank
    private String password;

    public O12Member() {
    }

    public O12Member(@NotBlank String name, @NotBlank String surname, @NotBlank String login, @NotBlank String password) {
        super(name, surname);
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(@NotBlank String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }
}
