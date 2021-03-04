package edu.washington.example.eventlistener;

import java.util.EventObject;


/**
 * Event object encapsulating a stock price change event.
 *
 * @author Russ Moul
 */
@SuppressWarnings("serial")
public final class PriceChangeEvent extends EventObject {
    /** The subject stocks ticker symbol. */
    private final String mTicker;

    /** Price of the subject stock as a result of the price change. */
    private final int mPrice;

    /**
     * Constructor.
     *
     * @param source event source
     * @param ticker stock ticker symbol
     * @param price the new price
     */
    public PriceChangeEvent(final Object source,
                            final String ticker, final int price) {
        super(source);
        mTicker = ticker;
        mPrice = price;
    }

    /**
     * Gets the stock ticker symbol for the stock experienceing the price
     * change.
     *
     * @return the stock ticker symbol
     */
    public String getTicker() {
        return mTicker;
    }

    /**
     * Gets the new price of the stoc experienceing the price change.
     *
     * @return the new stock price
     */
    public int getPrice() {
        return mPrice;
    }
}
