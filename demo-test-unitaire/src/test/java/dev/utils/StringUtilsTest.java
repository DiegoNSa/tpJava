package dev.utils;

import org.junit.*;

public class StringUtilsTest {

	
		//--------------- LevenshteinDistance Tests-------------------------
		@Test
		public void testLevenshteinDistance1() {			
			int res1 = StringUtils.levenshteinDistance("bonjour", "bonjour");
			Assert.assertEquals(0,res1);
		}

		@Test
		public void testLevenshteinDistance2() {			
			int res1 = StringUtils.levenshteinDistance("chat", "chats");
			Assert.assertEquals(1,res1);
		}
		
		@Test
		public void testLevenshteinDistance3() {			
			int res1 = StringUtils.levenshteinDistance("machins", "machine");
			Assert.assertEquals(1,res1);
		}
		
		@Test
		public void testLevenshteinDistance4() {			
			int res1 = StringUtils.levenshteinDistance("avion", "aviron");
			Assert.assertEquals(1,res1);
		}
		
		@Test
		public void testLevenshteinDistance5() {			
			int res1 = StringUtils.levenshteinDistance("distance", "istance");
			Assert.assertEquals(1,res1);
		}
		
		@Test
		public void testLevenshteinDistance6() {			
			int res1 = StringUtils.levenshteinDistance("Chien", "Chine");
			Assert.assertEquals(2,res1);
		}

		@Test
		public void testLevenshteinDistance7() {			
			int res1 = StringUtils.levenshteinDistance(null, "Chine");
			Assert.assertEquals(5,res1);
		}
		
		@Test
		public void testLevenshteinDistance8() {			
			int res1 = StringUtils.levenshteinDistance(null, null);
			Assert.assertEquals(0,res1);
		}

}
