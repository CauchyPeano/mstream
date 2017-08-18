package de.cauchypeano.mstream.rest;

import de.cauchypeano.mstream.data.Event;
import de.cauchypeano.mstream.data.EventRepository;
import de.cauchypeano.mstream.data.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController("/api")
public class RestEndpoint {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public List<Event> test() {
        Iterable<Event> all = eventRepository.findAll();
        return convertToList(all);
    }

    private ArrayList<Event> convertToList(Iterable<Event> all) {
        ArrayList<Event> result = new ArrayList<>();
        all.forEach(result::add);
        return result;
    }



}
