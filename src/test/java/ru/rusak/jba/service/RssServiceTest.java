package ru.rusak.jba.service;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import ru.rusak.jba.entity.Item;
import ru.rusak.jba.exception.RssException;

public class RssServiceTest {

	private RssService rssService;
	
	@Before
	public void setUp() throws Exception {
		rssService	=	new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		List<Item> items = rssService.getItems(new File("test-rss/javavids_rss.xml"));
		assertEquals(10, items.size());
		
		Item firstItem	=	items.get(0);
		assertEquals("How to solve Source not found error during debug in Eclipse", firstItem.getTitle());
		
		SimpleDateFormat simpleDateFormat	=	new SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.ENGLISH);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		assertEquals("22 06 2014 20:35:49", simpleDateFormat.format(firstItem.getPublishedDate()));
	}

}
