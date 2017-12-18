hw6 Feedback
============

#### Checkpoint (sequential implementation) (5/5)
* 5/5: Your sequential implementation seemed reasonably complete by the checkpoint deadline.

#### Sequential implementation (25/25)
* 15/15: Your sequential implementation largely functioned as we expected. Good job!
* 10/10: The quality of design of your expections meets our expectations. Good job!

#### Parallel implementation and discussion (30/35)
* Functionality (10/10)
  * 10/10: Your parallel implementation functioned as we expected and was functionally equivalent to your sequential implementation. Good job!

* Use of Java concurrency tools (13/15)
  * ([link](https://github.com/CMU-15-214/yiyir/blob/master/homework/6/src/main/java/edu/cmu/cs/cs214/hw6/ParallelGitAnalysis.java#L68)) It is not clear why you need a blocking queue here. Your call to `invokeAll` on line 101 returns a list of `Future<Integer>` representing the results of the calls. You could just map `.get` over this list to return the list of churn rates rather than extracting these results from a queue. Furthermore, a blocking queue is only useful if it is necessary to add/remove elements of this queue from multiple threads.

* Discussion (7/10)
  * -3: In your discussion it's not clear how you evaluated the performance of your sequential and parallel implementations.  On what repositories did you benchmark your solution's performance?  What quantitative results did you get?

#### Software development best practices (29/35)

* Code reuse (4/4)
  * Nice job accomplishing code reuse between your sequential and parallel implementations.

* Build automation (5/10)
  * -5: For this homework we expected you to use most of the development tools we introduced in this course, and specifically mentioned use of continuous integration as a best practice we would evaluate.  To use continuous integration for this assignment you need to just configure Gradle to build your homework (inside the `homework/6` directory) and edit the root directory's settings.gradle file to include `homework/6`.

* Use of VCS (5/6)
  * -1, Commit messages should contain a concise description of the changes made. We identified several of your commits messages that could be more descriptive.

* Static analysis (6/6)

* Testing (4/4)
  * -0.1, Some students had a good solution for running automated tests that I wanted to share: even though Git does not support nested repositories, you can still commit the .git directory (with a different name) from another repo to your repo. Then, you can write tests that access the renamed .git directory.

* Documentation and Style (5/5)

---

#### Total (89/100)

Late days used: 0 (0 left)

---

#### Additional Notes

Graded by: Nick Roberts (nroberts@andrew.cmu.edu)

To view this file with formatting, visit the following page: https://github.com/CMU-15-214/yiyir/blob/master/grades/hw6.md
