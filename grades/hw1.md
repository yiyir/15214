hw1 Feedback
============

#### Successful use of Git, GitHub, and build automation tools (4/5)

-1, Your commit history could be made more useful. A useful commit history separates work into multiple commits, instead of one or two for the whole assignment. Each commit should have a concise, but descriptive name about what was changed. Committing your work incrementally protects you from data loss or network problems, which might otherwise cause you to lose work or fail to meet the homework deadlines (which are strictly enforced). For more information on writing useful commit messages, see [here](https://git-scm.com/book/ch5-2.html#Commit-Guidelines) or [here](http://chris.beams.io/posts/git-commit/)

#### Basic proficiency in Java (19/20)

-1, Instead of repeatedly creating new documents, create each Document once and store it in an array to access it later. Each time a Document is created, there is an expensive network call and computation, which can be avoided by storing a single instance of the document.

It's a good idea to declare your variables as final if you don't expect their values to change.e.g.Document URL String.
[See: https://github.com/CMU-15-214/yiyir/blob/master/homework/1/src/main/java/edu/cmu/cs/cs214/hw1/Document.java]

Avoid using boxed primitives (i.e. `Integer`, `Boolean`, etc.) in your code. Use primitive types when possible, as they avoid unnecessary memory allocations on the heap. See [Effective Java, Item 49: Prefer primitive types to boxed primitives](http://goo.gl/O8t5Tf).
[See: https://github.com/CMU-15-214/yiyir/blob/master/homework/1/src/main/java/edu/cmu/cs/cs214/hw1/Document.java]

#### Fulfilling the technical requirements of the program specification (14.5/15)

-0.5, Did not handle edge case: 0 or 1 argument for FindClosestMatch.
[See: https://github.com/CMU-15-214/yiyir/blob/master/homework/1/src/main/java/edu/cmu/cs/cs214/hw1/FindClosestMatch.java]

#### Documentation and code style (10/10)

---

#### Total (47.5/50)

Late days used: 0 (5 left)

---

Graded by: Shuli Jiang (shulij@andrew.cmu.edu)

To view this file with formatting, visit the following page:
https://github.com/CMU-15-214/yiyir/blob/master/grades/hw1.md
