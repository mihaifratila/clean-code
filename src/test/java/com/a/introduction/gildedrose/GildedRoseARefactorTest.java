package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseARefactorTest {


	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	public static final int NOT_EXPIRED_SELLIN = 16;
	public static final int EXPIRED_SELLIN = -2;
	private static final int POSITIVE_SELLIN_LESS_THAN_5 = 2;
	private static final int SELLIN_BETWEEN_5_AND_10 = 7;
	public static final int DEFAULT_QUALITY = 4;
	public static final int MAXIMUM_QUALITY = 50;


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

	@Test
	public void unexpiredAgedBrie_QualityIncreasedBy1() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE,
								NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);

		// invoke
		app.updateQuality();

		// verify
		Item expected = new Item(AGED_BRIE,
				NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1);

	    // expected, actual
		AssertItem(expected, app.items[0]);
	}

	@Test
	public void unexpiredAgedBrie_QualityDoesNotGoBeyondMaximumValue() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE,
				NOT_EXPIRED_SELLIN, MAXIMUM_QUALITY);

		// invoke
		app.updateQuality();

		// verify
		Item expected = new Item(AGED_BRIE,
				NOT_EXPIRED_SELLIN - 1, MAXIMUM_QUALITY);

		// expected, actual
		AssertItem(expected, app.items[0]);
	}

	@Test
	public void expiredAgedBrie_QualityIncreasedBy2() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE,
				EXPIRED_SELLIN, DEFAULT_QUALITY);

		// invoke
		app.updateQuality();

		// verify
		Item expected = new Item(AGED_BRIE,
				EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 2);

		// expected, actual
		AssertItem(expected, app.items[0]);
	}

	@Test
	public void backstagePassBeyond10Days_QualityIncreasedBy1() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES,
				NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);

		// invoke
		app.updateQuality();

		// verify
		Item expected = new Item(BACKSTAGE_PASSES,
				NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1);

		// expected, actual
		AssertItem(expected, app.items[0]);
	}
	@Test
	public void backstagePassBetween5And10Days_QualityIncreasedBy2() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES,
				SELLIN_BETWEEN_5_AND_10, DEFAULT_QUALITY);

		// invoke
		app.updateQuality();

		// verify
		Item expected = new Item(BACKSTAGE_PASSES,
				SELLIN_BETWEEN_5_AND_10 - 1, DEFAULT_QUALITY + 2);

		// expected, actual
		AssertItem(expected, app.items[0]);
	}

	@Test
	public void backstagePassLessThan5Days_QualityIncreasedBy2() {
		// setup
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES,
				POSITIVE_SELLIN_LESS_THAN_5, DEFAULT_QUALITY);

		// invoke
		app.updateQuality();

		// verify
		Item expected = new Item(BACKSTAGE_PASSES,
				POSITIVE_SELLIN_LESS_THAN_5 - 1, DEFAULT_QUALITY + 3);

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