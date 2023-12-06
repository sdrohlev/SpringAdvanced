package com.example.dorne.repository;

import com.example.dorne.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    Optional<Event> findByName(String eventName);

    List<Event> findAllByOrderByDayAndTimeAsc();

}
