package com.dinomudrovcic.finapp.ws.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinomudrovcic.finapp.ws.exception.ResourceNotFoundException;
import com.dinomudrovcic.finapp.ws.model.Transaction;
import com.dinomudrovcic.finapp.ws.model.User;
import com.dinomudrovcic.finapp.ws.repository.TransactionRepository;
import com.dinomudrovcic.finapp.ws.repository.UserRepository;
import com.dinomudrovcic.finapp.ws.util.TransactionHelper;

@RestController
@RequestMapping("users")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	private TransactionHelper helper = new TransactionHelper();
	
//	@GetMapping()
//	public Long getUsersCount() {
//		return userRepository.count();
//	}
	
	@GetMapping()
	public List<User> getAllUsers() {
		logger.info("In method getAllUsers..");
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		logger.info("In method getUserById. UserId : " + userId);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + userId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		logger.info("Creating user with name : " + user.getName() + ", surname : " + user.getSurname() +
			" and account status : " + user.getAccountStatus());
		return userRepository.save(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userData)
			throws ResourceNotFoundException {
		logger.info("Update user  with new name : " + userData.getName() + ", surname : " + userData.getSurname() +
				" and account status : " + userData.getAccountStatus());
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + userId));
		user.setName(userData.getName());
		user.setSurname(userData.getSurname());
		user.setAccountStatus(userData.getAccountStatus());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		logger.info("In method deleteUser. UserId that is deleted : " + userId);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@PostMapping("/transaction")
	public List<User> updateUsersAfterTransaction(@Valid @RequestBody Transaction transactionData) throws ResourceNotFoundException{
		logger.info("Transaction data = date created : " + transactionData.getCreateDate() + ", userId : " + 
				transactionData.getIdUser() + ", operation : " + transactionData.getOperation() + ", amount : " + 
				transactionData.getAmount() + " and debtorId : " + transactionData.getIdDebtor());
		List<User> users = userRepository.findAll();
		User user = helper.fillUser(transactionData.getUser().getIdUser(), userRepository);
		transactionData.setUser(user);
		transactionData.setIdUser(user.getIdUser());
		User debtor;
		List<User> updatedUsers = new ArrayList<>();
		List<User> newUsers = new ArrayList<>();
		if(transactionData.getDebtor() != null) {
			debtor = helper.fillUser(transactionData.getDebtor().getIdUser(), userRepository);
			transactionData.setDebtor(debtor);
			transactionData.setIdDebtor(debtor.getIdUser());

			for(User u : users) {
				if(u.getIdUser() == user.getIdUser()) {
					updatedUsers.add(user);
				}
				else if(u.getIdUser() == debtor.getIdUser()) {
					updatedUsers.add(debtor);
				}
				else{
					updatedUsers.add(u);
				}
			}
			newUsers = helper.handleTransaction(updatedUsers, transactionData);
		}
		else {
			newUsers = helper.handleTransaction(users, transactionData);
		}
		transactionRepository.save(transactionData);
		return userRepository.saveAll(newUsers);		
	}
	
}
