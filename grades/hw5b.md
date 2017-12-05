hw5b Feedback
============

#### Core Framework Implementation (38/70)

* 16/30, Your framework meets the technical requirements to a reasonable degree. Good job!
  * -10, Your framework does not display the output of display plugins at all.
  * -4, Your framework is unable to run multiple display plugins at the same time. 

* 5/20, Your framework mostly works with a decent level of testing, but we identified the following issues:
  * -5, Your framework GUI throws IndexOutOfBoundException when I try to to visualize data from Google. 
  * -3, Your framework's implementation does not behave as expected when plugins throw exceptions, and the framework documentation does not clearly describe which exceptions plugins are allowed to throw. Either the framework should handle exceptions thrown by the plugin (perhaps showing an error message to the screen), or the framework should clearly document the contract of data/display plugins in this regard.

  * -4, Your unit tests for your framework do not use testing stubs.  To test your framework you should write a simple stub data plugin class (i.e., a mock data plugin) and a simple stub display plugin class (i.e., a mock display plugin) to generate and consume sample data so that you can test the behavior of your framework.  Because you are writing both stub classes for testing purposes, your stub data plugin and stub display plugin can be coupled to each other so that you can test the control flow for the framework as it processes a specific set of data.
  * -3, You need more test cases for your framework. The test coverage of your code was low (below 50%).

* 17/20, The general design of your framework is reasonable, but we identified the following issues:
  * -3, Your display plugin should not directly display a GUI. It should return a JPanel/JFrame to the framework. This allows the framework to have some control over what output is being displayed to the user.

#### Sample Plugins (26/30)
* 26/30, Your provided sample plugins are adequate and meet our expectations. Good job!
  * -4, Your display plugin does not actually visualize anything.

#### High quality documentation and style (11/25)

* 2/15, Your documentation meets our expectations. Good job!
  * -2, Your `README` does not explain some important aspect of writing a plugin. We fail to understand how to use your data plugins. You should at least provide some examples about the usage of your framework.
  * -8, Your plug-in interface contains no Javadocs. Your plug-in interface is arguably the most important class in your framework because it is one that developers will interact with and need to understand the most. Therefore it is extremely important that it be documented explaining exactly the purpose of each method, when each method is called by the framework, any potential exceptions that could be thrown by the method, etc.
	* -4 if there are multiple missing important javadocs which really hurt the understanding of the interface. Don’t double count against the plugins, though if they do forget to comment the plugins they’ll likely get this deduction too. 

* 5/5, Build automation using gradle and Travis CI seems to work fine and we were able to start your framework using `gradle run`. Nice! 

* 4/5, There are several repeated issues regarding documentation and style that you might want to address: 
  * -1, Avoid using magic numbers in your code. Declare variables as `static final` constants at the top of the file.



---

#### 5b Total (75/125)

Late days used: 2 (0 left for hw5)

---

#### Additional Notes

5b Graded by: Shuli Jiang (shulij@andrew.cmu.edu) and Dustin Liu (kaigel@andrew.cmu.edu)

To view this file with formatting, visit the following page: https://github.com/CMU-15-214/yiyir/blob/master/grades/hw5b.md