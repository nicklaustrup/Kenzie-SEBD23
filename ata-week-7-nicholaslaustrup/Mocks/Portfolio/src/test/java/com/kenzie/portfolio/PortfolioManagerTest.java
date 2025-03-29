package com.kenzie.portfolio;

import com.kenzie.portfolio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PortfolioManagerTest {

    @Mock
    private Portfolio portfolio;
    @Mock
    private StockExchangeClient client;
    @InjectMocks
    private PortfolioManager portfolioManager;
    private Stock amznStock = new Stock("amzn", "Amazon");
    private BigDecimal currentAmazonStockPrice = BigDecimal.valueOf(1_000L);
    private int quantityInPortfolio = 3;
    private Map<Stock, Integer> portfolioStocks;

    private Stock nonExistentStock = new Stock("id", "name");


    @BeforeEach
    void setUp() {
        initMocks(this);

    }

    @Test
    void getMarketValue_portfolioWithStocks_returnsValueOfStocks() {
//        // GIVEN
//        BigDecimal expectedValue = currentAmazonStockPrice.multiply(BigDecimal.valueOf(quantityInPortfolio));
//
//        // WHEN
//        BigDecimal value = portfolioManager.getMarketValue();
//
//        // THEN
//        assertEquals(expectedValue, value);
        when(portfolio.getStocks()).thenReturn(portfolioStocks);
        when(client.getPrice(amznStock)).thenReturn(currentAmazonStockPrice);
    }

    @Test
    void buy_existingStock_returnsCostOfBuyingStock() {
        // GIVEN
        int quantityToBuy = 5;
//        BigDecimal expectedCost = currentAmazonStockPrice.multiply(BigDecimal.valueOf(quantityToBuy));
//
//        // WHEN
//        BigDecimal cost = portfolioManager.buy(amznStock, quantityToBuy);
//
//        // THEN
//        assertEquals(expectedCost, cost);
        when(client.submitBuy(amznStock, quantityToBuy)).thenReturn(currentAmazonStockPrice.multiply(BigDecimal.valueOf(quantityToBuy)));
    }

    @Test
    void buy_nonExistingStock_returnsNull() throws InsufficientStockException{
        // GIVEN
        int quantityToBuy = 5;

//        // WHEN
//        BigDecimal cost = portfolioManager.buy(nonExistentStock, quantityToBuy);
//
//        // THEN
//        assertNull(cost);
        when(client.submitBuy(nonExistentStock, quantityToBuy)).thenReturn(null);
        verifyZeroInteractions(portfolio);
    }

    @Test
    void sell_enoughStocksToSell_returnValueSoldFor() {
        // GIVEN
        int quantityToSell = quantityInPortfolio - 1;
//        BigDecimal expectedValue = currentAmazonStockPrice.multiply(BigDecimal.valueOf(quantityToSell));
//
//        // WHEN
//        BigDecimal value = portfolioManager.sell(amznStock, quantityToSell);
//
//        // THEN
//        assertEquals(expectedValue, value);
        when(client.submitSell(amznStock, quantityToSell)).thenReturn(currentAmazonStockPrice.multiply(BigDecimal.valueOf(quantityToSell)));
    }

    @Test
    void sell_notEnoughStocksToSell_returnZeroValue() throws InsufficientStockException {
//        // GIVEN
        int quantityToSell = quantityInPortfolio + 1;
//
//        // WHEN
//        BigDecimal value = portfolioManager.sell(amznStock, quantityToSell);
//
//        // THEN
//        assertEquals(BigDecimal.ZERO, value);
        doThrow(InsufficientStockException.class).when(portfolio).removeStocks(amznStock, quantityToSell);

    }
}
