package com.stst.core.expression.demo;

import com.stst.expression.core.ExpressionsBuilder;
import com.stst.expression.core.ExpressionsEvaluator;

public class ExpressionsFactory {

	public static ExpressionsEvaluator create(String expression) {
		return new ExpressionsBuilder(expression).build();
	}
}
