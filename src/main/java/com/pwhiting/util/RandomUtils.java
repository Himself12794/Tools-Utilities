package com.pwhiting.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;

public final class RandomUtils {

	private RandomUtils() {}
	
	@SafeVarargs
	public static <T extends IWeightedItem> T selectRandomWeightedItem(T...items) {
		return selectRandomWeightedItem(null, items);
	}
	
	@SafeVarargs
	public static <T extends IWeightedItem> T selectRandomWeightedItem(Random rand, T...items) {
		return selectRandomWeightedItem(rand, Lists.newArrayList(items));
	}

	/**
	 * Selects a random weighted item out of the collection. Returns null is
	 * collection is empty, or all items have a weight of null.
	 * 
	 * @param items
	 * @return
	 */
	public static <T extends IWeightedItem> T selectRandomWeightedItem(Iterable<T> items) {
		return selectRandomWeightedItem(null, items);
	}
	
	public static <T extends IWeightedItem> T selectRandomWeightedItem(Random rand, Iterable<T> items) {
		
		Random randToUse = rand;
		if (rand == null) randToUse = new Random();

		float totalWeight = 0.0F;
		Map<Range<Float>, T> ranges = Maps.newHashMap();

		for (T item : items) {
			
			if (item.getWeight() > 0.0F) {
				float temp = totalWeight + item.getWeight();
				ranges.put(
						Range.closedOpen(totalWeight,
							temp ), item);
				totalWeight = temp;
			}
			
		}

		float choice = randToUse.nextFloat() * totalWeight;

		for (Entry<Range<Float>, T> entry : ranges.entrySet()) {

			if (entry.getKey().contains(choice)) {
				return entry.getValue();
			}

		}

		return null;
	}
	
	public static interface IWeightedItem {
		
		float getWeight();

	}

}
