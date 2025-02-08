---
layout: default
title: "Mutation Testing"
type: Lab
number: 03
active_tab: lab
release_date: 2025-02-09

---

<!-- Check whether the assignment is ready to release -->
{% capture today %}{{'now' | date: '%s'}}{% endcapture %}
{% capture release_date %}{{page.release_date | date: '%s'}}{% endcapture %}
{% if release_date > today %} 
<div class="alert alert-danger">
Warning: this assignment is out of date.  It may still need to be updated for this year's class.  Check with your instructor before you start working on this assignment.
</div>
{% endif %}
<!-- End of check whether the assignment is up to date -->


<!-- Check whether the assignment is up to date -->
<!--{% capture this_year %}{{'now' | date: '%Y'}}{% endcapture %}
{% capture due_year %}{{page.due_date | date: '%Y'}}{% endcapture %}
{% if this_year != due_year %} 
<div class="alert alert-danger">
Warning: this assignment is out of date.  It may still need to be updated for this year's class.  Check with your instructor before you start working on this assignment.
</div>
{% endif %}-->
<!-- End of check whether the assignment is up to date -->



{% if page.materials %}
<div class="alert alert-info">
You can download the materials for this assignment here:
<ul>
{% for item in page.materials %}
<li><a href="{{item.url}}">{{ item.name }}</a></li>
{% endfor %}
</ul>

</div>
{% endif %}





{{page.type}} {{page.number}}: {{page.title}}
=============================================================


You can optionally work with a partner on this lab. You should each submit separately to gradescope, but it can be the exact same document. 

## Objectives:

The main goals for this lab are:

1. Run a mutation tester to evaluate your test suite.
2. Design, implement, and evaluate a mutation operator.

Code coverage is the established standard metric for assessing test suite quality. In the last lab, we saw that even with a test suite that achieves the most rigorous measure of coverage (100% feasible path coverage), bugs may still go undetected. In this lab, we’ll continue the discussion on how to measure and improve test suite quality. Coverage alone is not enough. Test oracles are also important to high quality tests that can detect faults. A test oracle describes the correct output based on an input and is often written as an assertion.

Consider a test suite with full coverage, but without any assertions. Would it be able to find any bugs? 

<details>
<summary>>>Answer:</summary>
It would only be able to find violations in safety properties (things that should be true across all java programs), but it would not be able to find violations in functional properties. In our [BuyTicket](https://github.com/elizabethdinella/code-coverage/blob/main/src/BuyTicket.java) code from last lab, BUG 1 could be detected without assertions but BUG 2 could not. 
</details>

Mutation testing involves modifying the program in small ways to evaluate the quality of a test suite. Each mutated version is called a mutant and tests detect and reject mutants by causing the behavior of the original version to differ from the mutant. This is called killing the mutant. Test suites are measured by their mutation score which is a percentage of mutants that they kill. In this lab you will get familiar with mutation testing through running a mutation tester and designing, implementing, and evaluating your own mutation operator.  

Remember in the previous lab you added two tests. One for seniors and one for minors which triggered a bug. For a customerAge < 18, the  expression (customerAge - 65) / maxDiscount results in a negative value, which incorrectly reduces the discount rate.

Pitest requires that your tests pass. So, for this lab, download the corrected source from here: 
`wget https://bmc-cs-software-analysis.github.io/383/labs/lab03/BuyTicket.java`

The bug is fixed by adding a special case for ticket buyers under 18 and correctly subtracting customerAge from 18. It also includes helper methods for checking if the customer is eligible for an age discount. 

Setup:
1. Download the pitest jars and put them in a directory named `lib/`  
    a. `wget https://repo1.maven.org/maven2/org/pitest/pitest/1.18.0/pitest-1.18.0.jar`  
    b. `wget https://repo1.maven.org/maven2/org/pitest/pitest-command-line/1.18.0/pitest-command-line-1.18.0.jar`  
    c. `wget https://repo1.maven.org/maven2/org/pitest/pitest-junit5-plugin/1.2.1/pitest-junit5-plugin-1.2.1.jar`  
    d. `wget https://repo1.maven.org/maven2/org/pitest/pitest-entry/1.18.0/pitest-entry-1.18.0.jar`  
2. Download the run_pitest.sh script I’ve created: `wget https://bmc-cs-software-analysis.github.io/383/labs/lab03/run_pitest.sh`  
3. Copy over your tests from the previous lab.   
4. Move the hamcrest and junit jars from the previous lab to the `lib` directory.   

Your directory structure should look like this:  
.  
├── run_pitest.sh  
├── src  
│   └── BuyTicket.java  
├── lib  
│   └── JARS  
└── test  
    └── TestBuyTicket.java  


**Part 1: Running Pitest**

Evaluate the quality of your test suite by running run_pitest.sh 
Open pitest-report/index.html in a browser of your choice and inspect the mutations that survived.
Clicking on “Covering tests” for the surviving mutants (highlighted in red), you will see the test which cover the mutated region, but do not kill the mutant. 

**Exercise:** Add tests to your TestBuyTicket.java which kill the surviving mutants. 

Useful information about each mutation type is given in the [pitest documentation](https://pitest.org/quickstart/mutators/)

**Part 2: Design your Own Mutation Operator**

In this part of the assignment, you will design your own program mutation operator and evaluate its effectiveness. You will not be graded on the quality of your mutation but instead, the quality of your evaluation. 

Pitest does mutations on the bytecode level but you’re not required to do so. The only requirement is that your mutation creates at least two syntactic mutants to BuyTicket. 

To help evaluate your mutation, I’ve included a benchmark of test suites:
`wget https://bmc-cs-software-analysis.github.io/383/labs/lab03/tests.zip`

Evaluation:
1. How many mutants did your mutation create? Remember that the requirements state you must have at least 2. Include the program before and after transformation in your report clearly indicating the transformation. 
2. For each test suite, categorize the mutants based on their behavior:
    a. Survived (not killed by tests)  
    b. Killed (detected by at least one test)  
    c. Equivalent (mutants that produce identical behavior to the original program, making them undetectable)  
3. Compilation rate of mutants:  
    a. How many of your generated mutants compile successfully?   
    b. If any mutants fail to compile, explain why.  
4. Mutation score for each test suite
5. A qualitative evaluation of your mutation:  
    a. Do the given mutation scores align with what you expect? Do the bad test suites have lower scores than the good test suites? why / why not?   
    b. Did your mutation create realistic faults that resemble real-world bugs?  
6. Other questions:  
    a. What program representation does your transformation operate over and why?   
    b. If you worked with a partner, state both of your names at the top of this document.  


### Submission 
1. `TestBuyTicket.java`
2. Your mutation operator code
3. Lab report answering the questions in Part 2

