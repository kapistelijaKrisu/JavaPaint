## My Paint

### Subject
Simple application for image manipulation.
<br />**Difference with windows paint is that it will contain alpha channel and will run on anything that has a mouse and java 1.8 or above.**

### Users
The artist

### Actions
Create, modify and save images as png or jpg.*(if saved as jpg-file alpha channel disappears)*

### Instructions
Look at buttons and choose what do you want to do with the image. Use mouse buttons by dragging or pressing around to draw.<br>
Mouse shortcuts not listed but exist: <br>
right click mouse sets a random color. <br><br>
Key shortcuts not listed in buttons <br>
Z-go undo, X-go redo. <br>
"+" thicken brush, "-" thin brush<br>

### Program design
Target image is held in MyImage object that holds long-term settings of brush, graphics, and bufferedimage. This makes it easier to manipulate or preserve settings.<br>
ControlUnit holds the CommandMap which has mapped each type of image manipulation object that implements CMD. By setting active command (which is capsuled from misuse) the user decides which specific kind of manipulaiton he wants done on the image. ControlUnit also holds log object that contains history and redo stacks for going back and forth of changes on the image. User may choose when to use it.<br>
UI simply gives an Area object to commands and they will figure out what to do with image based on that using the brush settings of MyImage as well.


### Class Diagram

![Class Diagram](https://github.com/kapistelijaKrisu/JavaPaint/blob/master/Documentation/diagrams/Class-Diagram.png)


### Sequence Diagram drawline
![Sequence Diagram drawline](https://github.com/kapistelijaKrisu/JavaPaint/blob/master/Documentation/diagrams/sequence-diagram-draw.png)

### Sequence Diagram change color
![Sequence Diagram change color](https://github.com/kapistelijaKrisu/JavaPaint/blob/master/Documentation/diagrams/sequence-diagram-change-color.png)
