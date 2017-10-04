hw3 Feedback
============

#### Demonstrate mastery of earlier learning goals, especially the concepts of information hiding and polymorphism, software design based on informal specifications, testing, and testing best practices. (43/60)

* Information hiding (10/10)
* Compliance with specification (30/30)
  * Permutation Generator (10/10)
  * Cryptarithm Representation (7/10)

    * -3, Instead of parsing the string yourself, you used our terminal calculator to achieve that. We expect your solution to be only dependent on the Expression library. Consequently, your solution is inefficient because the string has to be parsed every time new values are substituted in.

  * Cryptarithm Solver (10/10)

* Testing practices (2/10)
   * -3, There is no assertion made in your SolveCryptarithmTest. To test your code your unit tests must contain JUnit assertions.  Without JUnit assertions your tests will never fail, and never actually test your code. You should design your SolveCryptarithm implementation in a way that makes it testable.

   * -3, You did not test that your permutation generator produces all the possible permutations (You have to test whether it generates the correct number and whether all outputs are distinct).

   * -2, Tests should be split up into the smallest testable parts of a program. Try to avoid testing multiple things in one test case.


* Java coding best practices and style (4/10)
  * -2, Your commit messages are not very descriptive of the changes you make. The first line of a commit message should always contain a concise description of the specific changes you made, and you should commit sufficiently regularly that it's easy to describe your changes with such a concise, specific message.  In practice commit messages are often very important to track changes in a project. Please attempt more descriptive changes in the future.

  * -2, Constants should be in all uppercase, separated by underscores. Ex: `DIGITS`.

  * -1, Please document your public classes/methods with comments. You have incomplete documentations in several classes. Building your code with gradle and checking Travis-CI are good ideas because they reveal checkstyle errors.

  * -1, Avoid using magic numbers in your code. Declare variables as constants at the top of the file.

  * -0.1, Your permutation generator and cryptarithm solver should be in separate packages. It is important to keep an organized package structure to facilitate information hiding and code reuse.



#### Use inheritance and delegation, and design patterns effectively to achieve design flexibility and code reuse (20/30)
  * -5, You are storing a collection of all permutations in memory an unnecessary burden on the system. Instead, you can use either the strategy, template method or iterator pattern to execute the client operation as you generate and only store valid results, resulting in an overall more customizable and better performance.

  * -5, Your design for command pattern is inappropriate because the heap algorithm should not be the command

#### Discuss the relative advantages and disadvantages of alternative design choices (7/10)
  * -2, Your description of command pattern abstracts the method of permutation generation instead of the work done for each permutation.

  * -1, Your discussion about the usability of command pattern is incorrect based on your design because user have to implement everything about a permutation generator.

---

#### Total (70/100)

Late days used: 1 (4 left)

---

#### Additional Notes

Graded by: Alvin Shi (xiangs1@andrew.cmu.edu)

To view this file with formatting, visit the following page: https://github.com/CMU-15-214/STUDENT_ANDREW_ID/blob/master/grades/hw3.md
