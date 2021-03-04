package edu.washington.example.eventlistener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Simple PriceChangeListener that "records" each price change.
 */
public class PriceTracker implements PropertyChangeListener {

	/** {@inheritDoc}  */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.printf("Tracker: Price changed for %s from %d to %d%n",
				          evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
		
	}
}
