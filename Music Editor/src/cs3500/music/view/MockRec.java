package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Created by ErinZhang on 3/17/16.
 */

/**
 * Log records the information of each note, containing start beat, end beat,
 * instrument, pitch index, and volume in order.
 */
public class MockRec implements Receiver {
  StringBuilder result =  new StringBuilder();

  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage msg = (ShortMessage) message;

    if (msg.getCommand() == ShortMessage.NOTE_ON) {
      result.append("Pitch: " + Integer.toString(msg.getData1() + 12) + " " +
              "Instrument: " + msg.getChannel() + " is on at time: " + timeStamp / 1000 + " " +
              "Volume: " + msg.getData2() + "\n");
    }
    else {
      result.append("Pitch: " + msg.getData1() + " " +
              "Instrument: " + msg.getChannel() + " is off at time: " + timeStamp / 1000 + " " +
              "Volume: " + msg.getData2() + "\n");
    }
  }

  @Override
  public void close() {
  }
}
