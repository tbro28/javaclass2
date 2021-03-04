package edu.washington.example.eventlistener;

import java.util.EventListener;


/**
 * Listener interface for price change events.
 *
 * @author Russ Moul
 */
public interface PriceChangeListener extends EventListener {
    /**
     * Processes price change events.
     *
     * @param event the price change event
     */
    void priceChanged(PriceChangeEvent event);
}
