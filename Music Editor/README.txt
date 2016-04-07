----------------------
CONTENTS OF THIS FILE
----------------------

* Introduction
* Functionalities
* Implementations
* Changes

----------------------
    INTRODUCTION
----------------------
This program constructs a music editor in the classic Model-View-Controller architecture. Users can input a song and the song will be displayed and played
in this music editor model.
In the view of this music editor model, higher pitches are higher vertically, and time increases to the right. Every row is a particular pitch, and beat numbers are marked at the top. Vertical bars mark measure lines every four beats.
User can also make changes to the song and import more songs to be played in
the model.
When pass in arguments in main, it takes in a file name and a string name for a view
- ìmidiî       display sound of the song
- ìguiî        display pixels of the song
- ìconsoleî    display text of the song
- ìcombinedî   display a combined version including sound and pixels(midi and gui)


----------------------
    FUNCTIONALITIES
----------------------
- write music one note at a time.
- edit or remove existing notes.
- combine two pieces of music such that they play simultaneously.
- combine two pieces of music such that they play consecutively.

----------------------
    IMPLEMENTATIONS
----------------------

MODEL

I. Pitch
This class represents a pitch. There are 12 distinct pitches.

II. Note interface

III. NoteImpl implements Note
This class represents a note. Fields are all private, so that users are unable to
mutate our data. But users can use getters to get the values of the fields if necessary. A note is generally constructed given a pitch, an octave, starting beat, ending beat, instrument, and volume. The constructor converted the given pitch and
octave into one pitch index and store it as a field. The constructor also ensures
that all note are valid. Equals and hashCode method are override, so we can compare
two notes with extensional equality.

IV. MusicEditorModel
This interface provides an outline for any music editor that implement
this model. It promises that any class implements this model must also implement some of the following general-purpose methods. i.e. add, edit, remove note, move, or combine songs.

V. MusicEditorImpl implements MusicEditorModel
This class is an implementation of the interface MusicEditorModel. It is given a list of notes and the constructor will convert all the notes into a music editor. The highest and lowest pitch, highest and lowest beat of the song are recorded as the fields of this model.

----------------------------------------------------------------------------------

VIEW

I. View interface
II. ViewCreator
  factory method to create any view type
III. MidiView extends View
IV. MidiViewImpl implements MidiView
V. GuiView extends View
VI. GuiViewGrame implements GuiView
VII. ConcreteGuiViewPanel
VIII. CombinedView extends GuiView
IX. CombinedViewImpl implements CombinedView
X. ConsoleViewImpl implements View

----------------------------------------------------------------------------------

CONTROLLER

I. Controller Interface
II. GuiController
	works for every type of view


----------------------
       CHANGES for HW6
----------------------
1. added a static method to get Pitch given an integer
2. added an interface Note for NoteImpl to implement to avoid leaking implementation
details.
3. added getNotesAtBeat(int beat) to retrieve notes from model
4. changed the range of octave to include -1 for arithmetic calculation for pitch index in MIDI
5. added getMap() to interface so it will be accessible by the view/controller and correct this method.	

-----------------------
	CHANGES FOR HW7
-----------------------

In HW 7 we changed the implementation of the Midi view to support the time synchronization of the gui and midi.
From Thread.sleep() we changed to the TimeTask() which played notes at each bit and at the same time moved the red line indicating the current beat position. In addition, we changed the way gui view represents notes. In the new implementation, notes are represented by separate objects as well as the line.

We later added more methods to the GuiViewFrame (Gui View Implementation class) to
support the controller and UI interactions.


-----------------------
	COMMANDS
-----------------------
 - Delete note: 
	1) Press R
	2) Left-click on a note beginning
 - Add note: 
	1) Press A
	2) Left-click on a note beginning
	3) Enter the duration in console
 - Move note: 
	1) Press M 
	2) Left-click press on note you want to move and release at the destination
 - Scroll up:
	1) Press UP arrow
 - Scroll down:
	1) Press DOWN arrow
 - Scroll left:
	1) Press LEFT arrow
 - Scroll right:
	1) Press RIGHT arrow
 - Pause/Resume:
	1) Press SPACE
 - Beginning frame
	1) Press H
 - End frame
	1) Press ENTER
