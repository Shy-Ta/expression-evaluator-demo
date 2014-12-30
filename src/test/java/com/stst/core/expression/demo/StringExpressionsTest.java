package com.stst.core.expression.demo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.stst.core.expression.demo.ExpressionsFactory;
import com.stst.expression.core.ExpressionsEvaluator;

public class StringExpressionsTest {

	@Test
	public void testSingleStringFunction() {
		ExpressionsEvaluator evalExpr = ExpressionsFactory.create("LEFT('New York', 3)");
		assertEquals("New", evalExpr.eval());
	}

	@Test
	public void testSingleStringFunctionWithVariable() {
		ExpressionsEvaluator evalExpr = ExpressionsFactory.create("LEFT(City, 3)");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("City", "New York");
		assertEquals("New", evalExpr.eval(variables));
	}

	@Test
	public void testCompositeStringFunctionWithVariable() {
		ExpressionsEvaluator evalExpr = ExpressionsFactory.create("CONCATENATE(LEFT('New York', 3), ' Hampshire')");
		assertEquals("New Hampshire", evalExpr.eval());
	}
}
