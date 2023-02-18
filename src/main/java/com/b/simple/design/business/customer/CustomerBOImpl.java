package com.b.simple.design.business.customer;

import java.math.BigDecimal;
import java.util.List;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

public class CustomerBOImpl implements CustomerBO {

	@Override
	public Amount getCustomerProductsSum(List<Product> products)
			throws DifferentCurrenciesException {

		if (products.size() == 0)
			return new AmountImpl(BigDecimal.ZERO, Currency.EURO);

		if(!checkSameCurrencyOnAllProducts(products))
			throw new DifferentCurrenciesException();

		return calculateSumOfProducts(products);
	}

	private static AmountImpl calculateSumOfProducts(List<Product> products) {
		BigDecimal sum = products.stream().map(product -> product.getAmount().getValue())
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return new AmountImpl(sum, getFirstProductCurrency(products));
	}

	private static boolean checkSameCurrencyOnAllProducts(List<Product> products) {
		Currency firstProductCurrency = getFirstProductCurrency(products);

		return products.stream().map(product -> product.getAmount().getCurrency())
				.allMatch(currency -> currency.equals(firstProductCurrency));
	}

	private static Currency getFirstProductCurrency(List<Product> products) {
		return products.get(0).getAmount().getCurrency();
	}
}