package com.dinomudrovcic.finapp.ws.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dinomudrovcic.finapp.ws.exception.ResourceNotFoundException;
import com.dinomudrovcic.finapp.ws.model.Transaction;
import com.dinomudrovcic.finapp.ws.model.User;
import com.dinomudrovcic.finapp.ws.repository.UserRepository;

public class TransactionHelper {
	
	private static Logger logger = Logger.getLogger(TransactionHelper.class);
	
	public List<User> handleTransaction(List<User> users, Transaction transactionData) {
		logger.info("In handling transaction method. Transaction operation is : " + transactionData.getOperation());
		List<User> updatedUsers = new ArrayList<>();
		
		String operation = transactionData.getOperation();
		switch(operation) {
		case "INCOME":
			updatedUsers = incomeLogic(users, transactionData);
			break;
		case "EXPENSE":
			updatedUsers = expenseLogic(users, transactionData);
			break;
		case "EXCHANGE":
			updatedUsers = exchangeLogic(users, transactionData);
			break;
		default:
			break;
		}
		return updatedUsers;
	}

	private List<User> incomeLogic(List<User> users, Transaction transactionData) {
		logger.info("In incomeLogic() method.");
		List<User> updatedUsers = new ArrayList<>();
		int numberOfUsers = users.size();
		double amountToChange = transactionData.getAmount() / numberOfUsers;
		for(User user : users) {
			double currentAccountStatus = user.getAccountStatus();
			double newAccountStatus = currentAccountStatus + amountToChange;
			user.setAccountStatus(newAccountStatus);
			updatedUsers.add(user);
		}
		return updatedUsers;
	}

	private List<User> expenseLogic(List<User> users, Transaction transactionData) {
		logger.info("In expenseLogic() method.");
		List<User> updatedUsers = new ArrayList<>();
		int numberOfUsers = users.size();
		double amountToChange = transactionData.getAmount() / numberOfUsers;
		for(User user : users) {
			if(user.getIdUser() == transactionData.getIdUser()) {
				double currentAccountStatus = user.getAccountStatus();
				double newAccountStatus = currentAccountStatus + amountToChange;
				user.setAccountStatus(newAccountStatus);
				updatedUsers.add(user);
			} 
			else {
				double currentAccountStatus = user.getAccountStatus();
				double newAccountStatus = currentAccountStatus - amountToChange;
				user.setAccountStatus(newAccountStatus);
				updatedUsers.add(user);
			}
		}
		return updatedUsers;
	}

	private List<User> exchangeLogic(List<User> users, Transaction transactionData) {
		logger.info("In exchangeLogic() method.");
		List<User> updatedUsers = new ArrayList<>();
		User user = transactionData.getUser();
		User debtor = transactionData.getDebtor();
		
		if(debtor == null)
			return null;
		
		double userCurrentAccountStatus = user.getAccountStatus();
		double debtorCurrentAccountStatus = debtor.getAccountStatus();
		double amount = transactionData.getAmount();
		user.setAccountStatus(userCurrentAccountStatus + amount);
		debtor.setAccountStatus(debtorCurrentAccountStatus - amount);
		
		
		for (User u : users) {
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
		return updatedUsers;
		
	}

	public User fillUser(long id, UserRepository userRepository) throws ResourceNotFoundException {
		logger.info("In fillUser() method.");
		User tmpUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id : " + id));
		return tmpUser;
	}
}
