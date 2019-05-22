package com.dinomudrovcic.finapp.ws.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dinomudrovcic.finapp.ws.model.Transaction;
import com.dinomudrovcic.finapp.ws.model.User;

public class TransactionHelperTest {
	
	private static final double INCOME_AMOUNT = 150.00;
	private static final double EXPENSE_AMOUNT = 60.00;
	private static final double EXCHANGE_AMOUNT = 100.00;
	
	static List<User> users;
	static List<User> originalUsers;
	TransactionHelper transHelper;

	@Before
	public void setup() {
		new TestHelper();
		transHelper = new TransactionHelper();
		users = TestHelper.createUserList();
		originalUsers = TestHelper.createUserList();
	}
	
	@After
	public void clear() {
		users.clear();
		originalUsers.clear();
	}
	
	
	@Test
	public void incomeLogic() {
		new TestHelper();
		Transaction transactionData = TestHelper.createTransactionDataForIncome();
		transHelper.handleTransaction(users, transactionData);
		
		
		double expectedAmount = 0.0;
		double actualAmount = 0.0;
		for (int i = 0; i < users.size(); i++) {
				expectedAmount = originalUsers.get(i).getAccountStatus() + (INCOME_AMOUNT / users.size());
				actualAmount = users.get(i).getAccountStatus();
			
			assertEquals(expectedAmount, actualAmount, 0);
		}
	}
	
	@Test
	public void expenseLogic() {
		new TestHelper();
		Transaction transactionData = TestHelper.createTransactionDataForExpense();
		transHelper.handleTransaction(users, transactionData);
		
		double expectedAmount = 0.0;
		double actualAmount = 0.0;
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getIdUser() == transactionData.getIdUser()) {
				expectedAmount = originalUsers.get(i).getAccountStatus() + (EXPENSE_AMOUNT / users.size());
				actualAmount = users.get(i).getAccountStatus();
			}
			else {
				expectedAmount = originalUsers.get(i).getAccountStatus() - (EXPENSE_AMOUNT / users.size());
				actualAmount = users.get(i).getAccountStatus();
			}
			
			assertEquals(expectedAmount, actualAmount, 0);
		}
	}
	
	@Test
	public void exchangeLogicTest() {
		new TestHelper();
		Transaction transactionData = TestHelper.createTransactionDataForExchange();
		List<User> newUsers = transHelper.handleTransaction(users, transactionData);
		
		double expectedAmount = 0.0;
		double actualAmount = 0.0;
		
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getIdUser() == transactionData.getIdUser()) {
				expectedAmount = originalUsers.get(i).getAccountStatus() + EXCHANGE_AMOUNT;
				actualAmount = newUsers.get(i).getAccountStatus();
			}
			else if(users.get(i).getIdUser() == transactionData.getIdDebtor()) {
				expectedAmount = originalUsers.get(i).getAccountStatus() - EXCHANGE_AMOUNT;
				actualAmount = newUsers.get(i).getAccountStatus();
			}
			else {
				expectedAmount = originalUsers.get(i).getAccountStatus();
				actualAmount = newUsers.get(i).getAccountStatus();
			}
			
			assertEquals(expectedAmount, actualAmount, 0);
		}
	}
}
