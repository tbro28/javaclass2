package demo;

import edu.washington.example.eventlistener.PriceChangeEvent;
import edu.washington.example.eventlistener.PriceChangeListener;

/**
 * Demonstrates the use of a price change listener.
 *
 * @author Russ Moul
 */
public final class PriceChangeDemo {
    /** Delay duration. */
    private static final int DELAY = 5000;

    /**
     * Prevent instantiation.
     */
    private PriceChangeDemo() {
    }

    /**
     * Entry point.
     *
     * @param args (not used)
     *
     * @throws InterruptedException if the main thead is interrupted
     */
    public static void main(final String[] args) throws InterruptedException {
        FakeMarket market = new FakeMarket();

        PriceChangeListener listener1;
        PriceChangeListener listener2;
        listener1 = new SimplePriceChangeListener("Listener 1");
        listener2 = new SimplePriceChangeListener("Listener 2");

        market.addPriceChangeListener(listener1);
        market.addPriceChangeListener(listener2);

        Thread.sleep(DELAY);

        System.out.println();
        System.out.println("Removing second listener!");
        System.out.println();
        market.removePriceChangeListener(listener2);

        Thread.sleep(DELAY);

        System.out.println();
        System.out.println("Closing market.");
        market.close();
    }
}


/**
 * A simple PriceChangeListener that prints the price change.
 */
final class SimplePriceChangeListener implements PriceChangeListener {
    /** The listeners name. */
    private String mName;

    /**
     * Constructor.
     *
     * @param listenerName the listeners name
     */
    public SimplePriceChangeListener(final String listenerName) {
        mName = listenerName;
    }

    /**
     * Price changed event handler.
     *
     * @param event the price changed event
     */
    public void priceChanged(final PriceChangeEvent event) {
        System.out.println(
               mName + ": " + event.getTicker() + " " + event.getPrice());
    }
}
