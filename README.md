![sakuli-logo](./docs/pics/sakuli-logo.png) 

*A end-to-end testing tool for web sites and common UIs with full Nagios integration*

# Introduction
## What is Sakuli? 
### Short version
**Sakuli simulates user actions** on graphical user interfaces (web, fat client, citrix, …), and provides the obtained information (runtime, result, screenshots) to **Nagios**-compatible monitoring systems. 

fixme: Grafik mit Nagios und Sakui
###  Long version 
The **Sakuli** project brings together two Open-Source end-to-end testing tools which perfectly fit together: **Sahi** (http://www.sahi.co.in/) for web-based tests (injecting JavaScript code into the browser), as well as the screenshot-based testing tool **Sikuli** (http://www.sikuli.org), which allows the execution of keyboard and mouse actions on screen areas that have been defined by previously recorded screenshots and are recognized using the OpenCV engine. 

Sakuli accesses both tools via its Java API and makes it possible to use them alternately, line by line. For example, web tests can be done very performant with Sahi (where a screenshot-based approach would be at best the second choice), whereas "off-DOM"-content can be catched with Sikuli. Whenever a web test comes into a situation which Sahi can't handle (e.g. a PIN dialogue for a smartcard), use a Sikuli command. On the other hand, pure tests of fat client applications can be easily be setup by using only the Sikuli functions of Sakuli.

## Backgrund: the history of Sakuli
First we only wanted to have the possibility to integrate Sahi web tests into Nagios. This was done with a simple VBscript wrapper (thus, only Windows...), which called Sahi with the correct parameters, and sent the results to Nagios via [NSCA](http://exchange.nagios.org/directory/Addons/Passive-Checks/NSCA--2D-Nagios-Service-Check-Acceptor/details). This brought Sahi tests into the well-known format of OK/WARNING/CRITICAL states in Nagios, including performance data of the test runtimes, which could be feeded into PNP4Nagios. 

But soon it became clear to us that there is more than only "web content". Flash and Java Applets are content which appears in the Document Object Model ([DOM](http://de.wikipedia.org/wiki/Document_Object_Model)) as a "black box", which can't be accessed by Sahi. 
We have looked around for a tool with a totally different approach to "remote control" the user interface: not by its content (like Sahi does with DOM), but by its appearance on the screen. Sikuli bridges this gap perfectly: it is able to control (click, type etc...) everything the user can do on the screen. 
To have a Java application that uses the API of both tools on the one hand, and which uses a modern scripting language for the test definitions (JavaScript) on the other hand, was the motivation to write Sakuli. 

To avoid misunderstandings: "Sakuli" is a portmanteau (in German: "Kofferwort"), formed from the names of the tools "Sahi" and "Sikuli". Whenever we speak of "Sakuli", we are talking about everything that is written about these both tools.  

## Why Sakuli? 
There are already a variety of free end2end/automation tools on the market (Sahi, Selenium, WebInject, Sikuli, CasperJS, AutoIT , ...) , but - especially from the perspective of Nagios-based monitoring - each of them has at least one of the following drawbacks: 

* Too specifically: *pure* web testing tools can only deeal with *pure* web content. Applets, Flash, dialogues generated by OS/browser dialogues etc. are invisible and a insurmountable hurdle for such tools.
* Too generic: screen-based testing tools "see" everything the user sees. They are the best choice for GUI tests, but inappropriate for web tests, because each browser type has its own understanding of how to render and display a web page. 
* Far from reality : There are tools to test web applications on protocol level - but to know whether a web application is working or not requires a test from the user's perspective. 
* Hardly one of these tools brings the ability to integrate into Nagios. Sakuli is prepared for the integration into every Nagios-compatible monitoring system: Nagios itself, Naemon, Shinken, Icinga, op5 Monitor... 

# Resources
## Use Sakuli: 
### Installation 
**OMD**

[OMD Preparation](./docs/installation-omd.md) - How to configure [OMD](http://www.omdistro.org) (containing **Nagios/Icinga/Shinken**) to monitor Sakuli

**Sakuli Client**

[Installation (Windows 7)](./docs/installation-windows.md) - Hot to set up a Windows Sakuli Client on Windows 7

[Installation (Ubuntu)](./docs/installation-ubuntu.md) - How to set up a Ubuntu Sakuli Client (Ubuntu Desktop 14.04 LTS)


### Get started: Example

**Sakuli Client**

[First steps (Windows 7)](./docs/firststeps-windows.md) - Hot to set up the Sakuli example check on Windows 7

[First steos (Ubuntu)](./docs/firststeps-ubuntu.md) - How to set up the Sakuli example check on Ubuntu Desktop 14.04 LTS

**OMD**

[Configuration (OMD)](./docs/installation-omd.md) - How to configure [OMD](http://www.omdistro.org) (containing **Nagios/Icinga/Shinken**) to monitor the Sakuli example check

### Manual 

[Sakuli Manual](./docs/sakuli-manual.md) 


### Troubleshooting

[Nagios Troubleshooting](./docs/troubleshooting-nagios.md) 

[Sakuli Client Troubleshooting](./docs/troubleshooting-sakuli-client.md) 


### Resources
(Auto-generated documentation of all functions)

[Sakuli API](./docs/sakuli-api.md) - an overview about the most important Sakuli functions. 

[Sakuli API (HTML)](http://htmlpreview.github.io/?https://github.com/ConSol/sakuli/blob/master/docs/api/index.html) - an overview about further Sakuli functions.  

## Contribute! 
[Installation instructions for developers](./docs/development/developer_maven_local_repo_instructions.md)

[How to prepare a new release](./docs/development/how-to-release.md)

