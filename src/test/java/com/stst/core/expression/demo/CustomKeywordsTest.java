package com.stst.core.expression.demo;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.stst.expression.core.ExpressionsBuilder;
import com.stst.expression.core.ExpressionsEvaluator;

public class CustomKeywordsTest {

	@Test
	public void testCustomExactFunction() {
		ExpressionsEvaluator evalExpr = new ExpressionsBuilder("EXACT(\"Hello\", inputStr)").withImports(
				Arrays.asList(new String[] { "static com.stst.core.expression.demo.CustomKeywords.*" })).build();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("inputStr", "Helo");
		assertEquals(false, evalExpr.eval(variables));
		variables.put("inputStr", "Hello");
		assertEquals(true, evalExpr.eval(variables));
	}

}
