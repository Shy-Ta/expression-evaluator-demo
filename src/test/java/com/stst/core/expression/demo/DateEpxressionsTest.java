package com.stst.core.expression.demo;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.stst.core.expression.demo.ExpressionsFactory;
import com.stst.expression.core.ExpressionsEvaluator;

public class DateEpxressionsTest {

	@Test
	public void testDate() {
		ExpressionsEvaluator evalExpr = ExpressionsFactory.create("DATE()");
		assertEquals(new Date().toString(), evalExpr.eval().toString());
	}

	@Test
	public void testDateBasedOnAPattern() {
		ExpressionsEvaluator evalExpr = ExpressionsFactory.create("ASDATE('20141229','yyyyMMdd')");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, Calendar.DECEMBER, 29, 0, 0, 0);
		assertEquals(calendar.getTime().toString(), evalExpr.eval().toString());
	}
}
