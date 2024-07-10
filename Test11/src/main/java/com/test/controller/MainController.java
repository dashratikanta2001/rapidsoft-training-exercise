package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dao.BookingDao;
import com.test.dao.RoomDao;
import com.test.dao.UserDao;
import com.test.model.Booking;
import com.test.model.Room;
import com.test.model.User;

@RestController
@RequestMapping
public class MainController {

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("/user")
	public ResponseEntity<?> postMethodName(@RequestBody User user) {
		User savedUser = userDao.save(user);
		
		return ResponseEntity.ok(savedUser);
	}
	
	@PostMapping("/room")
	public ResponseEntity<?> postMethodName(@RequestBody Room room) {
		Room savedRoom = roomDao.save(room);
		return ResponseEntity.ok(savedRoom);
	}
	
	@PostMapping("/booking")
	public ResponseEntity<?> postMethodName(@RequestBody Booking booking) {
		
		User user = userDao.findById(booking.getUser().getId()).orElse(null);
		if(user == null)
			return ResponseEntity.badRequest().body("Invalid user id");
		
		Room room = roomDao.findById(booking.getRoom().getId()).orElse(null);
		if(room == null)
			return ResponseEntity.badRequest().body("Invalid room id");
		room.setAvailability(false);
		booking.setUser(user);
		booking.setRoom(room);
		Booking savedRoom = bookingDao.save(booking);
		return ResponseEntity.ok(savedRoom);
	}
	
	
	
}
