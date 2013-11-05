package com.atanor.vrecorder.util;

import org.junit.Test;

import junit.framework.Assert;

public class FormatTimeTest {

	@Test
	public void testFormat(){
		Assert.assertEquals("00:00:01", FormatTime.format(1000L));
		Assert.assertEquals("00:10:02", FormatTime.format(602000L));
		Assert.assertEquals("02:22:13", FormatTime.format(8533000L));
	}
}
