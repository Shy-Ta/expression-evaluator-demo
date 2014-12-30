package com.stst.core.expression.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.stst.core.expression.demo.ExpressionsFactory;
import com.stst.expression.core.ExpressionsEvaluator;

public class FormatExpressionsTest {

	@Test
	public void testDateFormat() {
		ExpressionsEvaluator evalExpr = ExpressionsFactory.create("FORMAT(ASDATE('20141229','yyyyMMdd'),'MMM-dd-yyyy')");
		assertEquals("Dec-29-2014", evalExpr.eval());
	}
	
	@Test
	public void testNumberFormat() {
        assertEquals("9,876.54", ExpressionsFactory.create("FORMAT(9876.543, '#,##0.00')").eval());
        assertEquals("9877", ExpressionsFactory.create("FORMAT(9876.543, '#')").eval());
        assertEquals("54%", ExpressionsFactory.create("FORMAT(0.543, '0%')").eval());
	}
}
