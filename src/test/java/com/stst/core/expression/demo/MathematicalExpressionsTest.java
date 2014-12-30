package com.stst.core.expression.demo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.stst.core.expression.demo.ExpressionsFactory;
import com.stst.expression.core.ExpressionsEvaluator;

public class MathematicalExpressionsTest {

	@Test
	public void testArithmetic() {
		ExpressionsEvaluator evalExpr = ExpressionsFactory.create("2+3*4-6/2");
		assertEquals(BigDecimal.valueOf(11), evalExpr.eval());
	}
	
	@Test
	public void testMathFunctions() {
		ExpressionsEvaluator evalExpr = ExpressionsFactory.create("tan(toRadians(45.0)) + sqrt(9.0)");
		assertEquals(Double.valueOf(4), evalExpr.eval());
	}
	
	@Test
	public void testVariableDrivenFunctions() {
		ExpressionsEvaluator evalExpr = ExpressionsFactory.create("log10(var1)");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("var1", 100);
		assertEquals(Double.valueOf(2), evalExpr.eval(variables));
	}
}
