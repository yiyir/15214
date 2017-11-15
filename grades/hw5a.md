hw5a Feedback
============

## Description of Framework (25/25)
  * 25/25, Your framework description is reasonably complete, good job!

## Framework Design (31/35)
#### Basic Structure (Understanding + Old Concepts) (11/15)
  * 11/15, You have demonstrated a reasonably good understanding of framework design, but we identified the following minor issues: 
    * -2, The plug-ins have direct access to parts of the framework internals that should only be accessible to the framework UI. We do not know how `showPlot()` works in your data plugin.

    * -2, Instead of passing a 2D array of String representing the data model you created for your domain, it’s more standard to pass in a `Data` object, which contains a list of these data objects. This way, if you choose to provide plugins with more data, it can be done without rewriting any code in the plugins.

#### Framework/Plugin Interaction & Control Flow (New Concepts) (15/15)
   * 15/15, You have demonstrated a good understanding of responsibility assignment and control flow. Well done!

#### Style (5)
  * 5/5, Your notation meets our expectations regarding style.

## Planning (9/10)
   * -1, You did not leave adequate time before the project is due to perform code reviews. At least two people on your team should be familiar with every part of the program. We would like to know how you plan to achieve this. 

## Presentation (20/20)
#### Talk quality (17/17)
  * 17/17, The quality of your presentation fully met our expectations, good job!

#### Timing (3/3)


## Other Feedback (see email)

---

#### Total (85/90)

Late days used: 0 (2 left for hw5)

---

#### Additional Notes

Graded by: Shuli Jiang (shulij@andrew.cmu.edu) and Dustin Liu (kaigel@andrew.cmu.edu)

To view this file with formatting, visit the following page: https://github.com/CMU-15-214/yiyir/blob/master/grades/hw5a.md
