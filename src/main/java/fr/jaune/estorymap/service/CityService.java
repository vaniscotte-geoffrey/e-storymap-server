package fr.jaune.estorymap.service;

import fr.jaune.estorymap.model.Message;
import fr.jaune.estorymap.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityService {

    @Autowired
    private MessageRepository repository;

    public List<Message> findAll() {
        return (List<Message>) repository.findAll();
    }

    public Message save(Message message) {
        return this.repository.save(message);
    }

}
