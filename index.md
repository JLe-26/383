---
title: Software Analysis - Bryn Mawr College
layout: default
img: <!-- turk-engraving-detail -->
img_link: <!-- http://en.wikipedia.org/wiki/The_Turk -->
caption: <!--An engraving of the Mechanical Turk, the 18th century chess-playing automaton -->
active_tab: main_page 
---


This course offers an introduction to the theoretical foundations of software analysis and its applications in software engineering and security. Effective program analysis is essential for ensuring the reliability, security, and performance of software systems. Students will explore a range of methods for analyzing program behavior including testing, static analysis, and formal methods. The course emphasizes both theoretical knowledge and practical experience through implementation of analysis tools and engaging with current state-of-the-art techniques.

<!-- Display an alert about upcoming homework assignments -->
{% capture now %}{{'now' | date: '%s'}}{% endcapture %}
{% for page in site.pages %}
{% if page.release_date and page.due_date %}
{% capture release_date %}{{page.release_date | date: '%s'}}{% endcapture %}
{% capture due_date %}{{page.due_date | date: '%s'}}{% endcapture %}
{% if release_date < now and due_date >= now %}
<div class="alert alert-info">
<a href="{{site.url}}{{site.baseurl}}{{page.url}}">{{ page.title }}</a> has been released.  
{% if page.deliverables %}
The assignment has multiple deliverables.
{% for deliverable in page.deliverables %}
The {{deliverable.description}} is due before {{ deliverable.due_date | date: "%I:%M%p" }} on {{ deliverable.due_date | date: "%A, %B %-d, %Y" }}.  
{% endfor %}
{% else %}
It is due before {{ page.due_date | date: "%I:%M%p" }} on {{ page.due_date | date: "%A, %B %-d, %Y" }}.
{% endif %}
</div>
{% endif %}
{% endif %}
{% endfor %}
<!-- End alert for upcoming homework assignments -->


<!--
<div class="alert alert-info" markdown="1">
Check out the [excellent final projects](http://crowdsourcing-class.org/final-projects-2016.html) from last year's class.
</div>
-->


Course number
: CMSC B383 

Instructor
: [Elizabeth Dinella](https://www.seas.upenn.edu/~edinella/)

<!--
Teaching Assistants
: [Course Staff](staff.html) 
-->

Website 
: [https://bmc-cs-383.github.io/](https://bmc-cs-383.github.io/)

<!-- 
Code Samples From Lecture
: [Github](https://github.com/BMC-CS-151/class-examples-s24)
-->

Discussion Forum
: [Piazza](https://piazza.com/class/lr8bl2y6e4f5ql/)

Time and place
: Spring 2024, MW 1-2:30pm, Location: Park 245
: Lab M: 2:40-4:00pm, Location: Park 231

Prerequisites
: The following courses are required with a grade of 2.0 or better (or permission of the instructor):
1. CS B223 (or H equivalent??)
2. CS B340 (or H equivalent??)
3. CS B151

Course Readings
: Some lectures will have accompanying optional reading related to the lecture's topic

Grading
* Homeworks: 45%
* Labs: 10%
* Midterm: 20%
* Final: 20% 
* Participation: 5%


<!--Late day policy
: As a general rule, no late homework will be accepted.
<br>
See the <a href="{{ site.url }}{{ site.baseurl }}/policies.html">Policies</a> for more details.-->

<!--#### Acknowledgments-->
