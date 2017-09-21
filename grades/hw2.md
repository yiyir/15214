hw2 Feedback
============

#### Correctly applying the concepts of polymorphism and information hiding (13/20)

* Polymorphism (8/15)

  *  -7, You missed an opportunity for polymorphism in your Expression classes. You could have reused `Operator`s instead of reimplementing the same behavior. Then, you could have just `BinaryOperatorExpression` and `UnaryOperatorExpression` classes that delegate to the underlying implementation of `Operator`.


* Information Hiding (5/5)


#### Java best practices and compatibility with our informal specification (28/30)

* Part 1 (8/8)

* Part 2 (9/9)

* Part 3 (11/13)

  * -1, The `eval()` method of your `DerivativeExpression` does not restore your variable to its original value. This may result in unexpected results when evaluating other expressions using the same variable.

  * -1, Your `zero()` method does not restore your variable to its original value. This may result in unexpected results when evaluating other expressions using the same variable.



#### Unit testing, including coverage and compliance with best practices (28/30)

* Testing (10/12)

  * -2, Your tests do not adequately test the specification of the methods or classes. For instance, you should test invalid inputs to methods to ensure that the methods fail. Also, you should test multiple inputs to methods to verify the behavior on those inputs. Creating tests in for loops (stress testing) is a good way to test many inputs.

* Coverage (12/12)

* Best Practices (6/6)


#### Documentation and style (11/20) 

  * -5, Please document your public classes/methods with comments. You are missing documentation in several classes. Read EJ item 44 for further information. 

  * -1, Constants should be in all uppercase, separated by underscores. Ex: `VARIABLE_NAME`. 

  * -0.1, Please remove TODO comments from your code, your submissions should be finalized. 

  * -1, Your commit messages are not very descriptive of the changes you make. In practice commit messages are often very important to track changes in a project. Please attempt more descriptive messages in the future.

  * -2, You committed all or most of your code in one or two big commit(s). It is good practice (and also a good backup strategy) to commit regularly at milestones while you are still developing the solution.



---

#### Total (80/100)

Late days used: 0 (5 left)

---

#### Additional Notes

Graded by: Zilei Gu (zileig@andrew.cmu.edu)

To view this file with formatting, visit the following page: https://github.com/CMU-15-214/yiyir/blob/master/grades/hw2.md
