package com.codingdojo.GroupProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.GroupProject.models.Event;
import com.codingdojo.GroupProject.models.User;
import com.codingdojo.GroupProject.repositories.EventRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eRepo;
	
	public Event fetchEvent(Long id) {
		return eRepo.findById(id).orElse(null);
	}
	
	public List<Event> fetchAll() {
		return eRepo.findAll();
	}
	
	public Event createEvent(User creator, Event event) {
		event.setEventCreator(creator);
		return eRepo.save(event);
	}
	
	public void removeUser(User user, Long eventId) {
		Event event = eRepo.findById(eventId).get();
		List<User> attendees = event.getAttendees();
		attendees.remove(user);
		event.setAttendees(attendees);
		eRepo.save(event);
	}
	
	public void joinEvent(User user, Long eventId) {
		Event event = eRepo.findById(eventId).get();
		List<User> attendees = event.getAttendees();
		attendees.add(user);
		event.setAttendees(attendees);
		eRepo.save(event);
	}
}
