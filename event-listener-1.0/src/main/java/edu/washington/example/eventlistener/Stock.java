package edu.washington.example.eventlistener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A stock, consisting of a ticker symbol and price.
 *
 * @author Russ Moul
 */
public final class Stock {
    /** Maximum length of a ticker. */
    private static final int MAX_TICKER_LEN = 4;

    /** The ticker symbol. */
    private final String ticker;

    /** The stock price. */
    private int price = 0;


    /** PropertyChangeSupport instance. */
    private final PropertyChangeSupport pcs;


    /**
     * Constructor.
     *
     * @param ticker the stock ticker
     * @param price the stock price
     */
    public Stock(final String ticker, final int price) {
        if ((ticker == null) || (ticker.length() == 0)
                             || (ticker.length() > MAX_TICKER_LEN)) {
            throw new IllegalArgumentException(
                    "Ticker must be 1 to 4 chararacters.");
        }
        this.ticker = ticker;

        if (price < 0) {
            throw new IllegalArgumentException(
                "Price must be a positive value.");
        }
        this.price = price;

        pcs = new PropertyChangeSupport(this);

    }





    /**
     * Gets the ticker.
     *
     * @return the stock ticker
     */
    public String getTicker() {
        return ticker;
    }


    /**
     * Sets the price, price is a bound property.
     *
     * @param price the stock price
     */
    public void setPrice(final int newPrice) {
        if (price < 0) {
            throw new IllegalArgumentException(
                "Price must be a positive value.");
        }

	    int oldPrice = price;
	    price = newPrice;
	    pcs.firePropertyChange("price", oldPrice, price);        
    }


    public void addPropertyChangeListener(PropertyChangeListener pcl) {
    	    pcs.addPropertyChangeListener("price", pcl);
    }


    public void removePropertyChangeListener(PropertyChangeListener pcl) {
	    pcs.removePropertyChangeListener("price", pcl);
    }


    /**
     * Gets the price.
     *
     * @return the stock price
     */
    public int getPrice() {
        return price;
    }


    /**
     * Gets the string representation of the stock, <i>ticker</i>:<i>price</i>
     *
     * @return the string
     */
    public String toString() {
        return ticker + ":" + price;
    }


}
