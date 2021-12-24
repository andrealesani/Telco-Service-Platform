package it.polimi.db2.telcoservice.entities;

import javax.persistence.*;

@Entity
@Table(name = "service_package")
@NamedQueries({})
public class ServicePackage {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private int subsID;

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

    public int getSubsID() {
        return subsID;
    }

    public void setSubsID(int subsID) {
        this.subsID = subsID;
    }
}
