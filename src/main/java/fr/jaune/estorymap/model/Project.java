package fr.jaune.estorymap.model;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public Project() {
    }

    public Project(String title) {
        this.title = title;
    }
}
