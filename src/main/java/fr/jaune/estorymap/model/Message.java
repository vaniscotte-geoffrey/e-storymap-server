package fr.jaune.estorymap.model;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String message;
    public String author;

    public Message() {

    }

    public Message(String message) {
        this(message, "Geoffrey");
    }


    public Message(String message, String author) {
        this.message = message;
        this.author = author;
    }
}
