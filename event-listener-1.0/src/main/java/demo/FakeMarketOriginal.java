package demo;

import edu.washington.example.eventlistener.PriceChangeEvent;
import edu.washington.example.eventlistener.PriceChangeListener;
import edu.washington.example.eventlistener.PriceTracker;
import edu.washington.example.eventlistener.Stock;

import javax.swing.event.EventListenerList;
import java.util.Random;

/**
 * A simulated market.
 *
 * @author Russ Moul
 */
public final class FakeMarketOriginal extends Thread {
    /** Initial price for ACME. */
    private static final int INIT_ACME_PRICE = 10000;

    /** Initial price for ACE. */
    private static final int INIT_ACE_PRICE = 4000;

    /** Initial price for ABC. */
    private static final int INIT_ABC_PRICE = 6000;

    /** Half second. */
    private static final int HALF_SECOND = 500;

    /** Tenth of a second. */
    private static final int TENTH_SECOND = 100;

    /** Tenth of a second. */
    private static final int PERCENT = 100;

    /** Indicates if prices are adjusting or not. */
    private boolean mAdjusting = true;

    /** The stocks in the market. */
    private Stock[] stocks = {new Stock("ACME", INIT_ACME_PRICE),
                              new Stock("ACE", INIT_ACE_PRICE),
                              new Stock("ABC", INIT_ABC_PRICE)};

    /** The markets listeners. */
    private EventListenerList listenerList = new EventListenerList();

    /**
     * Constructs the market and starts adjusting prices.
     */
    public FakeMarketOriginal() {
    	    // PropertyChangeListener to all the stocks. 
    	    PriceTracker tracker = new PriceTracker();
    	    for (Stock s : stocks) {
    	    	    s.addPropertyChangeListener(tracker);
    	    }
        start();
    }

    /**
     * Performs the price adjustments.
     */
    public void run() {
        Random rand = new Random();

        while (mAdjusting) {
            try {
                // get random delay, within range
                int interval = TENTH_SECOND
                             + (int) (rand.nextDouble() * HALF_SECOND);

                // get random ticker
                int ndx = rand.nextInt(stocks.length);

                // get random adjustment <= 1%
                int price = stocks[ndx].getPrice();
                int adjustment = rand.nextInt() % (price / PERCENT);
                price += adjustment;
                stocks[ndx].setPrice(price);
                firePriceChangeEvent(new PriceChangeEvent(this,
                        stocks[ndx].getTicker(), price));
                sleep(interval);
            } catch (InterruptedException iex) {
                System.out.println("Adjusting thread interrupted.");
            }
        }
    }

    /**
     * Stop the price adjustment.
     */
    public void close() {
        mAdjusting = false;
    }

    /**
     * Adds a price change listener.
     *
     * @param l the listener to add
     */
    public synchronized void addPriceChangeListener(
                                           final PriceChangeListener l) {
        listenerList.add(PriceChangeListener.class, l);
    }

    /**
     * Removes a price change listener.
     *
     * @param l the listener to remove
     */
    public synchronized void removePriceChangeListener(
                                              final PriceChangeListener l) {
        listenerList.remove(PriceChangeListener.class, l);
    }

    /**
     * Fires a price changed event.
     *
     * @param evnt the event to be fired
     */
    protected void firePriceChangeEvent(final PriceChangeEvent evnt) {
        PriceChangeListener[] listeners = listenerList.getListeners(PriceChangeListener.class);
        for (PriceChangeListener pcl : listeners) {
            pcl.priceChanged(evnt);
        }
    }
}
