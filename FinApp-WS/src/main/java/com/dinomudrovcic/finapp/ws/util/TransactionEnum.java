package com.dinomudrovcic.finapp.ws.util;

public enum TransactionEnum {
	INCOME,
	EXPENSE,
	EXCHANGE;
	
	@Override
	public String toString() {
		return name();
	}
}
