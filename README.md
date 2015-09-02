![Alt Text] (https://travis-ci.org/Shy-Ta/expression-evaluator-demo.svg)

expression-evaluator-demo
=========================

A general purpose java library which offers excel or Google spreadsheet like formula/ expression support. The library also allows easy addition of new functions.

The project contains examples\ tests to demonstrate what is already on offer and also give some examples to extend or add new functionality. The sequence of topics is as below:

* Arithmetic Functions  
* String Functions  
* Date Functions  
* Format Functions  
* Condition/ Operator based Functions  
* User Defined Functions (Ability to customize or add new functions)  
* Performance Considerations  


#####Arithmetic Functions  

```
  ExpressionsEvaluator evalExpr = ExpressionsFactory.create("2+3*4-6/2");  
  assertEquals(BigDecimal.valueOf(11), evalExpr.eval());  
  //mathematical functions  
  ExpressionsEvaluator evalExpr = ExpressionsFactory.create("tan(toRadians(45.0)) + sqrt(9.0)");  
  assertEquals(Double.valueOf(4), evalExpr.eval());  
  //variable driven functions  
  ExpressionsEvaluator evalExpr = ExpressionsFactory.create("log10(var1)");  
  Map<String, Object> variables = new HashMap<String, Object>();  
  variables.put("var1", 100);  
  assertEquals(Double.valueOf(2), evalExpr.eval(variables));  
  ```

#####String Functions  

```
  ExpressionsEvaluator evalExpr = ExpressionsFactory.create("LEFT('New York', 3)");
  assertEquals("New", evalExpr.eval());
  //variable driven String function
  ExpressionsEvaluator evalExpr = ExpressionsFactory.create("LEFT(City, 3)");
  Map<String, Object> variables = new HashMap<String, Object>();
  variables.put("City", "New York");
  assertEquals("New", evalExpr.eval(variables));
  //composite String function
  ExpressionsEvaluator evalExpr = ExpressionsFactory.create("CONCATENATE(LEFT('New York', 3), ' Hampshire')");
  assertEquals("New Hampshire", evalExpr.eval());
  ```

Some of the included functions are LEFT, RIGHT, CONCATENATE, LEN, TRIM, UPPER, LOWER, FIND, REPLACE and a few more. New functions can be added easily as explained later in the blog.

#####Date Functions

The library internally uses the joda-time library; however it exposes the date as java.util in order to ensure compatibility with standard jdk distribution.

```
  ExpressionsEvaluator evalExpr = ExpressionsFactory.create("DATE()");
  assertEquals(new Date().toString(), evalExpr.eval().toString());
  //date representation of a custom date
  ExpressionsEvaluator evalExpr = ExpressionsFactory.create("ASDATE('20141229','yyyyMMdd')");
  Calendar calendar = Calendar.getInstance();
  calendar.set(2014, Calendar.DECEMBER, 29, 0, 0, 0);
  assertEquals(calendar.getTime().toString(), evalExpr.eval().toString());
  ```

The keyword DATESTR returns the string representation of a date while DATETIME returns the string representation of both the date as well as the time.

#####Format Functions for Date or Number

```
  ExpressionsEvaluator evalExpr = ExpressionsFactory.create("FORMAT(ASDATE('20141229','yyyyMMdd'),'MMM-dd-yyyy')");
  assertEquals("Dec-29-2014", evalExpr.eval());
  //number formatting
  assertEquals("9,876.54", ExpressionsFactory.create("FORMAT(9876.543, '#,##0.00')").eval());
  assertEquals("9877", ExpressionsFactory.create("FORMAT(9876.543, '#')").eval());
  assertEquals("54%", ExpressionsFactory.create("FORMAT(0.543, '0%')").eval());
  ```

#####Logical/ Conditional Operators (AND, OR, IF)

```
  ExpressionsEvaluator evalExpr = ExpressionsFactory.create("IF(AND(Score>0,Score<=40), 'Fail', IF(AND(Score>40, Score<=65), 'Grade II','Grade I'))");
  Map<String, Object> variables = new HashMap<String, Object>();
  variables.put("Score", 90);
  assertEquals("Grade I", evalExpr.eval(variables));
  variables.put("Score", 50);
  assertEquals("Grade II", evalExpr.eval(variables));
  ```

The above is a great example of how business logic can be made configuration driven rather than code driven; this makes it easier to just change the expressions and get the updated result rather than changing the code and going through the release-deploy cycle.

#####Customize (Add user defined functions)

A sample implementation for EXACT function in excel which compares the equality of two string can be done by creating a class CustomKeywords with the method below

```
    public static boolean EXACT(String str1, String str2) {
        return str1.equals(str2);
    }
```
and then an example would look like
```
  ExpressionsEvaluator evalExpr = new ExpressionsBuilder("EXACT(\"Hello\", inputStr)").withImports(Arrays.asList(new String[] { "static com.stst.core.expression.demo.CustomKeywords.*" })).build();
  Map<String, Object> variables = new HashMap<String, Object>();
  variables.put("inputStr", "Helo");
  assertEquals(false, evalExpr.eval(variables));
  variables.put("inputStr", "Hello");
  assertEquals(true, evalExpr.eval(variables));
  ```

#####Performance

The performance of the expression engine is comparable to the existing solutions like jexl, embedded javascript engine in jdk et-all. This is based on a benchmark that I had done previously but I would recommend running your own benchmarks and analyze the pros and cons. The following tricks should definitely be included for the performance analysis
* Minimize new creation of GroovyShell (ExpressionsBuilder allows groovy shell re-use). In a multi-threaded environment use a thread local as groovy shell isn't thread safe.
* Minimize creation of expression evaluator - as it has one time cost of running the parser. An expression once created can be cached and then run for different set of variables.

A new class is loaded for every new instance of expression - this leads to a slight increase in perm gen memory. This shouldn't be a concern in the majority of cases. However, even in cases where this is a concern the gc option to support class unloading should take care of it. Do reach out in case you need any help with this.

#####Conclusion

As you may have guessed all the functions can be used in conjunction so feel free to explore. All the tests mentioned in the examples above can be found in this maven project.

Do let me know your experience and any questions or feedback you may have.
