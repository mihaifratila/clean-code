package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseARefactorTest {


	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	public static final int NOT_EXPIRED_SELLIN = 15;
	public static final int DEFAULT_QUALITY = 3;
	public static final int EXPIRED_SELLIN = -1;


	@Test
	public void unexpiredDefaultItem_QualityDecreasedBy1() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM,
								NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);

		// invoke
		app.updateQuality();

		// verify
		Item expected = new Item(DEFAULT_ITEM,
				NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 1);

		// expected, actual
		AssertItem(expected, app.items[0]);
	}

	@Test
	public void expiredDefaultItem_QualityDecreasedBy2() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM,
								EXPIRED_SELLIN, DEFAULT_QUALITY);

		// invoke
		app.updateQuality();

		//verify
		Item expected = new Item(DEFAULT_ITEM,
				EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 2);

		// expected, actual
		AssertItem(expected, app.items[0]);
	}

	// helper functions

	private void AssertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn, actual.sellIn);
		assertEquals(expected.quality, actual.quality);
	}

	private GildedRose createGildedRoseWithOneItem(String itemType, int sellIn, int quality) {
		Item item = new Item(itemType, sellIn, quality);
		Item[] items = { item };
		return new GildedRose(items);
	}
}