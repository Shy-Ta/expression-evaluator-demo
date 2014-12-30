package com.stst.core.expression.demo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.stst.core.expression.demo.ExpressionsFactory;
import com.stst.expression.core.ExpressionsEvaluator;

public class ConditionsExpressionTest {

	@Test
	public void testIfWithAndOperator() {
		ExpressionsEvaluator evalExpr = ExpressionsFactory
				.create("IF(AND(Score>0,Score<=40), 'Fail', IF(AND(Score>40, Score<=65), 'Grade II','Grade I'))");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("Score", 90);
		assertEquals("Grade I", evalExpr.eval(variables));
		variables.put("Score", 50);
		assertEquals("Grade II", evalExpr.eval(variables));
	}

}
