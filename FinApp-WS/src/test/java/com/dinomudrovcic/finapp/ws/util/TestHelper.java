package com.dinomudrovcic.finapp.ws.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dinomudrovcic.finapp.ws.model.Transaction;
import com.dinomudrovcic.finapp.ws.model.User;

public class TestHelper {
	
	private static final double INCOME_AMOUNT = 150.00;
	private static final double EXPENSE_AMOUNT = 60.00;
	private static final double EXCHANGE_AMOUNT = 100.00;
	
	
	public static List<User> createUserList(){
		List<User> users = new ArrayList<>();
		
		User user1 = new User();
		user1.setIdUser(1);
		user1.setName("Miro");
		user1.setSurname("Miric");
		user1.setAccountStatus(0.0);
		
		User user2 = new User();
		user2.setIdUser(2);
		user2.setName("Ivo");
		user2.setSurname("Ivic");
		user2.setAccountStatus(0.0);
		
		User user3 = new User();
		user3.setIdUser(3);
		user3.setName("Ana");
		user3.setSurname("Anic");
		user3.setAccountStatus(0.0);

		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		return users;
	}
	
	public static Transaction createTransactionDataForIncome() {
		User user = new User();
		user.setIdUser(1);
		user.setName("Miro");
		user.setSurname("Miric");
		user.setAccountStatus(0.0);
		
//		User debtor = new User();
//		debtor.setIdUser(2);
//		debtor.setName("Ivo");
//		debtor.setSurname("Ivic");
//		debtor.setAccountStatus(0.0);
		
		Transaction transaction = new Transaction();
		transaction.setIdTransaction(0);
		transaction.setCreateDate(new Date());
		transaction.setUser(user);
		transaction.setIdUser(1);
		transaction.setOperation(TransactionEnum.INCOME.toString());
		transaction.setAmount(INCOME_AMOUNT);
//		transaction.setDebtor(debtor);
//		transaction.setIdDebtor(2);
		
		return transaction;
	}
	

	public static Transaction createTransactionDataForExpense() {
		User user = new User();
		user.setIdUser(1);
		user.setName("Miro");
		user.setSurname("Miric");
		user.setAccountStatus(0.0);
		
//		User debtor = new User();
//		debtor.setIdUser(2);
//		debtor.setName("Ivo");
//		debtor.setSurname("Ivic");
//		debtor.setAccountStatus(0.0);
		
		Transaction transaction = new Transaction();
		transaction.setIdTransaction(0);
		transaction.setCreateDate(new Date());
		transaction.setUser(user);
		transaction.setIdUser(1);
		transaction.setOperation(TransactionEnum.EXPENSE.toString());
		transaction.setAmount(EXPENSE_AMOUNT);
//		transaction.setDebtor(debtor);
//		transaction.setIdDebtor(2);
		
		return transaction;
	}
	
	public static Transaction createTransactionDataForExchange() {
		User user = new User();
		user.setIdUser(1);
		user.setName("Miro");
		user.setSurname("Miric");
		user.setAccountStatus(0.0);
		
		User debtor = new User();
		debtor.setIdUser(2);
		debtor.setName("Ivo");
		debtor.setSurname("Ivic");
		debtor.setAccountStatus(0.0);
		
		Transaction transaction = new Transaction();
		transaction.setIdTransaction(0);
		transaction.setCreateDate(new Date());
		transaction.setUser(user);
		transaction.setIdUser(1);
		transaction.setOperation(TransactionEnum.EXCHANGE.toString());
		transaction.setAmount(EXCHANGE_AMOUNT);
		transaction.setDebtor(debtor);
		transaction.setIdDebtor(2);
		
		return transaction;
	}
	
}
