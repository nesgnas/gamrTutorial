**Introduction:**

- motivation:
- Task Allocation

**Technologies:**

- java 17
- java AWT
- javax Swing
- java IO

**OOP Concepts Implemented:**

- Command pattern

(Ex : _controlListKey_)

- Abstract class

(Ex : _Box - \> superObject_)

- Inheritance

(Ex: _InteractBox, Player [extend] Entity)_

- Polymorphism

_(Ex: Credits, Guide, Menu, [extend] Background_)

- Overriding
- Implementing a runnable interface.
- Implementing a comparable interface.

- Running Threads.
- File Handling

_(To create maps based on text file)._

- Exception Handling

_(To ensure correct image and file loading)._

**Command pattern:**

_We use this pattern to encapsulate all the key signal input from the keyboard to control the action of the player._

**Diagram of pattern**

<!-- ![commande](C:\Users\ASUS\Desktop\gamrTutorial\Readme\commandpattern.png) -->
<div style="text-align: center">
<img src = "Readme\commandpattern.png" alt="command pattern">
<img src = "Readme\commandpattern2.png" alt="command pattern2">
<p>(Note as the Command Pattern standar diagram)</p> 
</div>
**Abstract class:**

_A superObject abstract class was created that is open for extension by many new objects. In this case, we just need one object (Box)._

<img src = "Readme\abstractclass1.png" alt="abstract class">
<img src = "Readme\abstractclass2.png" alt="abstract class2">
<img src = "Readme\abstractclass3.png" alt="abstract class3">
>

**Polymorphism**

_Applying a polymorphism attribute so that we_ _have many classes that are related to each other by inheritance._

**Example :**

**InteractBox, Player [extend] Entity**
![](RackMultipart20230531-1-obguq1_html_6edc1401af58dc9a.png)

_( Player and interactBox was inheritance all attribute of Entity class)_

![](RackMultipart20230531-1-obguq1_html_3364819ac65a85a0.png)

_(the idea is transform box[superObject] to be new entity which inheritance all the attribute from Entity)_

**Inheritance:**

_This is used for creating many subclasses from one superclass, this subclass has all attributes and methods from superclass._

**Example:**

**Credits, Guide, Menu [extend] Background**

![](RackMultipart20230531-1-obguq1_html_21093ce4df17371c.png)_(Credits, Guide, Menu was inheritance all method of Background class, and using @Overwrite to setting their own work )_

![](RackMultipart20230531-1-obguq1_html_df1b99f95908e4dd.png) ![](RackMultipart20230531-1-obguq1_html_17943f7d1721f62e.png)

![](RackMultipart20230531-1-obguq1_html_9ddc48f6360c39f2.png)

![](RackMultipart20230531-1-obguq1_html_c5d377d0526b90bb.png)

**Overriding**

_Overriding occurs when a subclass contains the same method as the parent class. The overriding method's name, number and type of parameters, and return type are similar to those of the method it overrides._

**Example:**

**Player [extend] Entity**

In this case, the Players class extends the Entity class, so it overrides the speak method of its parent class ( Entity class).

![](RackMultipart20230531-1-obguq1_html_67b7f16b156cde4.png) ![](RackMultipart20230531-1-obguq1_html_dfe6e951db5cddf4.png)

**Implementing a runnable interface**

_This is used for executing code on a thread when instancing the class_

**Example:**

**gamePanel [implements] Runnable**

![](RackMultipart20230531-1-obguq1_html_3db22b19900be5fc.png)

_(Overriding method in the separately executing thread when a thread is started by the object of any class which is implementing Runnable)_

![](RackMultipart20230531-1-obguq1_html_380717c20ab34bb3.png)

**Implementing a comparable interface**

_This is used to sort objects's positions in the box class by implementing a Comparable interface._

**Example:**

**Box [implements] Comparable**

![](RackMultipart20230531-1-obguq1_html_b9be76418b7b09c.png)

![](RackMultipart20230531-1-obguq1_html_218a9e4a72738ed2.png)

_(Writing the compareTo() method in such a way that, in the same room, if this is less than the passed box's position, then it should return negative, if greater than positive, and zero if equal)_

**Running Threads:**

![](RackMultipart20230531-1-obguq1_html_c03aa29f4540bda1.png) ![](RackMultipart20230531-1-obguq1_html_4fbbae89362349ef.png)

**the method to call a Thread - often use this to make multitask in sametime**

(explain)

**File Handling:**

![](RackMultipart20230531-1-obguq1_html_45bf8cc1440f3d5d.png) ![](RackMultipart20230531-1-obguq1_html_1f7bc1c002f2923c.png)**The loadMap() method loads a map from a file. The method takes a file path as input. The method returns a map. The method works by first opening the file. Then, it reads the contents of the file line by line. For each line, the method splits the line into an array of strings. The method then uses the array of strings to create a tile. The method adds the tile to an array of tiles. Once the method has read all of the lines in the file, it closes the file. Finally, the method returns the array of tiles.**

(explain)

**Exception Handling:**

![](RackMultipart20230531-1-obguq1_html_6e9e9ee881261c4.png)

**the process of responding to unwanted or unexpected events when a computer program runs. Exception handling deals with these events to avoid the program or system crashing, and without this process, exceptions would disrupt the normal operation of a program.**

(explain)

**Launch:**

(explain)

**The project "SANASHIBAMY":**

1) Rule:

1. **Button, Mouse and Moving**

- **Mouse Click:**

- Play Game : This is the bar to start the game when you click this bar.
- Guide: This bar contains instructions on how to play the game when you click this bar.
- Credits: This bar contains the author and developer who made this game when you click this bar.
- Exit: This bar is used to close the game when you click it.
- Subwindow: This sub window contains multiple effects of the game, you can click it to change the effects or reset the room.

- **Button:**

- Up arrow: press the button to move the character up .
- Left arrow: press the key to move the character to the left.
- Right arrow: press the key to move the character to the right
- Down arrow: press the key to move the character down

1. **About the rules to play the game:**

- Player control: You control the player character, typically represented by a small character icon. The character can move horizontally or vertically one square at a time, but cannot move diagonally or pass through walls or boxes.
- Moving Boxes: Boxes are typically represented by large squares. The goal is to push each box onto a target location. You can only push the boxes, never pull them. The boxes can only be pushed one at a time and can't be pushed into walls or other boxes.

- Target Locations: The target locations are usually marked with a different pink color to indicate that a box should be placed there. Once all boxes are correctly placed on the target locations, you have completed the level.

2) Design:

- Background: We get ideas from the game's theme and colors and refer to online sources to create a background with a bright, natural background .
- Map: Using the GIMP and TILED software to create tileset and draw map.
- Sound: sound is added to create an enjoyable atmosphere, making the player more interesting.
- Welcome: The game name "SANASHIBAMY" was added at the beginning. Moreover, we made it more colorful and eye-catching.
- Game attribute:

3) Other:

- Link report
- Presentation: https://www.canva.com/design/DAFj53jtDyQ/9DuNI22YatparYSimiVgqA/edit?utm\_content=DAFj53jtDyQ&utm\_campaign=designshare&utm\_medium=link2&utm\_source=sharebutton.
- Feature

- References:
  - http://surl.li/hhwmy