package it.polimi.db2.db2jpa2021.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
    public Employee() {
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "FNAME")
    private String firstName;
    @Column(name = "LNAME")
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfFirstName() {
        return firstName;
    }

    public void setfFirstName(String fName) {
        this.firstName = fName;
    }

    public String getlLastName() {
        return lastName;
    }

    public void setlLastName(String lName) {
        this.lastName = lName;
    }
}
