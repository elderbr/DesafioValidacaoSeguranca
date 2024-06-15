package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Transactional
    public EventDTO save(EventDTO dto) {
        Event entity = repository.save(dto.parseToEntity());
        return parseToDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllOrderByName(Pageable pageable) {
        Page<Event> pages = repository.findAllByOrderByNameAsc(pageable);
        return pages.map(EventDTO::new);
    }

    private EventDTO parseToDTO(Event entity) {
        return new EventDTO(entity.getId(), entity.getName(), entity.getDate(), entity.getUrl(), entity.getCity().getId());
    }
}
