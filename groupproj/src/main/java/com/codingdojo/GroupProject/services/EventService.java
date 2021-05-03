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
	
		// Add a person 
		public void addPerson(User user, Event events) {
			List<User> attendees = events.getAttendees();
			attendees.add(user);
			this.eRepo.save(events);
		}
		//Remove a person
		public void removePerson(User user, Event events) {
			List<User> attendees = events.getAttendees();
			attendees.remove(user);
			this.eRepo.save(events);
		}
}
