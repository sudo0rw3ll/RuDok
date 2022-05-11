## RuDok - Application for document management and creation
# This application was made as a project for a Software design subject

RuDok is fully written in Java and demonstrates usage of
the following design patterns:
* Composite
* Observer
* State
* Command
* Factory
* Singletone

![RuDok GUI](/slike/first_printsc.PNG)

This application was inspired by PowerPoint, software for presentation design.
<br>RuDok allows you to create multiple projects inside your workspace and multiple
presentations inside created projects.</br> You can design your own presentation slides
by adding slots. Slots can be either text or images.

Content inside text slot can be edited and stylized by adding simple styles
* Bold
* Italic
* Underline

![RuDok slot editing](/slike/second_ss.PNG)

Slots can be moved around the slide and also they can be deleted.
RuDok supports 2 work modes, editing mode and presentation mode.
Sharing created presentations between projects is also possible.

You can also save your presentations and projects and open them later, this is achieved
using serialization in java.

GUI for RuDok is written using Swing library.
