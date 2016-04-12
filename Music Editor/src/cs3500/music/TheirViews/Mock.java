package cs3500.music.view.Mocks;

import javax.sound.midi.*;
import java.util.List;

/**
 * Created by demifilippou on 3/22/16.
 */
public class Mock implements Synthesizer {
  public StringBuilder sb;

  public Mock(StringBuilder sb) {
    this.sb = sb;
  }


  public String getSb() {
    return this.sb.toString();
  }

  /**
   * Obtains the maximum number of notes that this synthesizer can sound simultaneously.
   *
   * @return the maximum number of simultaneous notes
   * @see #getVoiceStatus
   */
  @Override
  public int getMaxPolyphony() {
    return 0;
  }

  /**
   * Obtains the processing latency incurred by this synthesizer, expressed in
   * microseconds.  This latency measures the worst-case delay between the
   * time a MIDI message is delivered to the synthesizer and the time that the
   * synthesizer actually produces the corresponding result.
   * <p>
   * Although the latency is expressed in microseconds, a synthesizer's actual measured
   * delay may vary over a wider range than this resolution suggests.  For example,
   * a synthesizer might have a worst-case delay of a few milliseconds or more.
   *
   * @return the worst-case delay, in microseconds
   */
  @Override public long getLatency() {
    return 0;
  }

  /**
   * Obtains the set of MIDI channels controlled by this synthesizer.  Each
   * non-null element in the returned array is a <code>MidiChannel</code> that
   * receives the MIDI messages sent on that channel number.
   * <p>
   * The MIDI 1.0 specification provides for 16 channels, so this
   * method returns an array of at least 16 elements.  However, if this synthesizer
   * doesn't make use of all 16 channels, some of the elements of the array
   * might be <code>null</code>, so you should check each element
   * before using it.
   *
   * @return an array of the <code>MidiChannel</code> objects managed by this
   * <code>Synthesizer</code>.  Some of the array elements may be <code>null</code>.
   */
  @Override public MidiChannel[] getChannels() {
    try {
      return MidiSystem.getSynthesizer().getChannels();
    }
    catch (MidiUnavailableException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Obtains the current status of the voices produced by this synthesizer.
   * If this class of <code>Synthesizer</code> does not provide voice
   * information, the returned array will always be of length 0.  Otherwise,
   * its length is always equal to the total number of voices, as returned by
   * <code>getMaxPolyphony()</code>.  (See the <code>VoiceStatus</code> class
   * description for an explanation of synthesizer voices.)
   *
   * @return an array of <code>VoiceStatus</code> objects that supply
   * information about the corresponding synthesizer voices
   * @see #getMaxPolyphony
   * @see VoiceStatus
   */
  @Override public VoiceStatus[] getVoiceStatus() {
    return new VoiceStatus[0];
  }

  /**
   * Informs the caller whether this synthesizer is capable of loading
   * instruments from the specified soundbank.
   * If the soundbank is unsupported, any attempts to load instruments from
   * it will result in an <code>IllegalArgumentException</code>.
   *
   * @param soundbank soundbank for which support is queried
   * @return <code>true</code> if the soundbank is supported, otherwise <code>false</code>
   * @see #loadInstruments
   * @see #loadAllInstruments
   * @see #unloadInstruments
   * @see #unloadAllInstruments
   * @see #getDefaultSoundbank
   */
  @Override public boolean isSoundbankSupported(Soundbank soundbank) {
    return false;
  }

  /**
   * Makes a particular instrument available for synthesis.  This instrument
   * is loaded into the patch location specified by its <code>Patch</code>
   * object, so that if a program-change message is
   * received (or has been received) that causes that patch to be selected,
   * subsequent notes will be played using the sound of
   * <code>instrument</code>.  If the specified instrument is already loaded,
   * this method does nothing and returns <code>true</code>.
   * <p>
   * The instrument must be part of a soundbank
   * that this <code>Synthesizer</code> supports.  (To make sure, you can use
   * the <code>getSoundbank</code> method of <code>Instrument</code> and the
   * <code>isSoundbankSupported</code> method of <code>Synthesizer</code>.)
   *
   * @param instrument instrument to load
   * @return <code>true</code> if the instrument is successfully loaded (or
   * already had been), <code>false</code> if the instrument could not be
   * loaded (for example, if the synthesizer has insufficient
   * memory to load it)
   * @throws IllegalArgumentException if this
   *                                  <code>Synthesizer</code> doesn't
   *                                  support the specified instrument's soundbank
   * @see #unloadInstrument
   * @see #loadInstruments
   * @see #loadAllInstruments
   * @see #remapInstrument
   * @see SoundbankResource#getSoundbank
   * @see MidiChannel#programChange(int, int)
   */
  @Override public boolean loadInstrument(Instrument instrument) {
    return false;
  }

  /**
   * Unloads a particular instrument.
   *
   * @param instrument instrument to unload
   * @throws IllegalArgumentException if this
   *                                  <code>Synthesizer</code> doesn't support the
   *                                  specified instrument's soundbank
   * @see #loadInstrument
   * @see #unloadInstruments
   * @see #unloadAllInstruments
   * @see #getLoadedInstruments
   * @see #remapInstrument
   */
  @Override public void unloadInstrument(Instrument instrument) {

  }

  /**
   * Remaps an instrument. Instrument <code>to</code> takes the
   * place of instrument <code>from</code>.<br>
   * For example, if <code>from</code> was located at bank number 2,
   * program number 11, remapping causes that bank and program location
   * to be occupied instead by <code>to</code>.<br>
   * If the function succeeds,  instrument <code>from</code> is unloaded.
   * <p>To cancel the remapping reload instrument <code>from</code> by
   * invoking one of {@link #loadInstrument}, {@link #loadInstruments}
   * or {@link #loadAllInstruments}.
   *
   * @param from the <code>Instrument</code> object to be replaced
   * @param to   the <code>Instrument</code> object to be used in place
   *             of the old instrument, it should be loaded into the synthesizer
   * @return <code>true</code> if the instrument successfully remapped,
   * <code>false</code> if feature is not implemented by synthesizer
   * @throws IllegalArgumentException if instrument
   *                                  <code>from</code> or instrument <code>to</code> aren't
   *                                  supported by
   *                                  synthesizer or if instrument <code>to</code> is not loaded
   * @throws NullPointerException     if <code>from</code> or
   *                                  <code>to</code> parameters have null value
   * @see #loadInstrument
   * @see #loadInstruments
   * @see #loadAllInstruments
   */
  @Override public boolean remapInstrument(Instrument from, Instrument to) {
    return false;
  }

  /**
   * Obtains the default soundbank for the synthesizer, if one exists.
   * (Some synthesizers provide a default or built-in soundbank.)
   * If a synthesizer doesn't have a default soundbank, instruments must
   * be loaded explicitly from an external soundbank.
   *
   * @return default soundbank, or <code>null</code> if one does not exist.
   * @see #isSoundbankSupported
   */
  @Override public Soundbank getDefaultSoundbank() {
    try {
      return MidiSystem.getSynthesizer().getDefaultSoundbank();
    }
    catch (MidiUnavailableException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Obtains a list of instruments that come with the synthesizer.  These
   * instruments might be built into the synthesizer, or they might be
   * part of a default soundbank provided with the synthesizer, etc.
   * <p>
   * Note that you don't use this method  to find out which instruments are
   * currently loaded onto the synthesizer; for that purpose, you use
   * <code>getLoadedInstruments()</code>.
   * Nor does the method indicate all the instruments that can be loaded onto
   * the synthesizer; it only indicates the subset that come with the synthesizer.
   * To learn whether another instrument can be loaded, you can invoke
   * <code>isSoundbankSupported()</code>, and if the instrument's
   * <code>Soundbank</code> is supported, you can try loading the instrument.
   *
   * @return list of available instruments. If the synthesizer
   * has no instruments coming with it, an array of length 0 is returned.
   * @see #getLoadedInstruments
   * @see #isSoundbankSupported(Soundbank)
   * @see #loadInstrument
   */
  @Override public Instrument[] getAvailableInstruments() {
    return new Instrument[0];
  }

  /**
   * Obtains a list of the instruments that are currently loaded onto this
   * <code>Synthesizer</code>.
   *
   * @return a list of currently loaded instruments
   * @see #loadInstrument
   * @see #getAvailableInstruments
   * @see Soundbank#getInstruments
   */
  @Override public Instrument[] getLoadedInstruments() {
    return new Instrument[0];
  }

  /**
   * Loads onto the <code>Synthesizer</code> all instruments contained
   * in the specified <code>Soundbank</code>.
   *
   * @param soundbank the <code>Soundbank</code> whose are instruments are
   *                  to be loaded
   * @return <code>true</code> if the instruments are all successfully loaded (or
   * already had been), <code>false</code> if any instrument could not be
   * loaded (for example, if the <code>Synthesizer</code> had insufficient memory)
   * @throws IllegalArgumentException if the requested soundbank is
   *                                  incompatible with this synthesizer.
   * @see #isSoundbankSupported
   * @see #loadInstrument
   * @see #loadInstruments
   */
  @Override public boolean loadAllInstruments(Soundbank soundbank) {
    return false;
  }

  /**
   * Unloads all instruments contained in the specified <code>Soundbank</code>.
   *
   * @param soundbank soundbank containing instruments to unload
   * @throws IllegalArgumentException thrown if the soundbank is not supported.
   * @see #isSoundbankSupported
   * @see #unloadInstrument
   * @see #unloadInstruments
   */
  @Override public void unloadAllInstruments(Soundbank soundbank) {

  }

  /**
   * Loads the instruments referenced by the specified patches, from the
   * specified <code>Soundbank</code>.  Each of the <code>Patch</code> objects
   * indicates a bank and program number; the <code>Instrument</code> that
   * has the matching <code>Patch</code> is loaded into that bank and program
   * location.
   *
   * @param soundbank the <code>Soundbank</code> containing the instruments to load
   * @param patchList list of patches for which instruments should be loaded
   * @return <code>true</code> if the instruments are all successfully loaded (or
   * already had been), <code>false</code> if any instrument could not be
   * loaded (for example, if the <code>Synthesizer</code> had insufficient memory)
   * @throws IllegalArgumentException thrown if the soundbank is not supported.
   * @see #isSoundbankSupported
   * @see Instrument#getPatch
   * @see #loadAllInstruments
   * @see #loadInstrument
   * @see Soundbank#getInstrument(Patch)
   * @see Sequence#getPatchList()
   */
  @Override public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
    return false;
  }

  /**
   * Unloads the instruments referenced by the specified patches,
   * from the MIDI sound bank specified.
   *
   * @param soundbank soundbank containing instruments to unload
   * @param patchList list of patches for which instruments should be unloaded
   * @throws IllegalArgumentException thrown if the soundbank is not supported.
   * @see #unloadInstrument
   * @see #unloadAllInstruments
   * @see #isSoundbankSupported
   * @see Instrument#getPatch
   * @see #loadInstruments
   */
  @Override public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {

  }

  /**
   * Obtains information about the device, including its Java class and
   * <code>Strings</code> containing its name, vendor, and description.
   *
   * @return device info
   */
  @Override public Info getDeviceInfo() {
    return null;
  }

  /**
   * Opens the device, indicating that it should now acquire any
   * system resources it requires and become operational.
   * <p>
   * <p>An application opening a device explicitly with this call
   * has to close the device by calling {@link #close}. This is
   * necessary to release system resources and allow applications to
   * exit cleanly.
   * <p>
   * <p>
   * Note that some devices, once closed, cannot be reopened.  Attempts
   * to reopen such a device will always result in a MidiUnavailableException.
   *
   * @throws MidiUnavailableException thrown if the device cannot be
   *                                  opened due to resource restrictions.
   * @throws SecurityException        thrown if the device cannot be
   *                                  opened due to security restrictions.
   * @see #close
   * @see #isOpen
   */
  @Override public void open() throws MidiUnavailableException {

  }

  /**
   * Closes the device, indicating that the device should now release
   * any system resources it is using.
   * <p>
   * <p>All <code>Receiver</code> and <code>Transmitter</code> instances
   * open from this device are closed. This includes instances retrieved
   * via <code>MidiSystem</code>.
   *
   * @see #open
   * @see #isOpen
   */
  @Override public void close() {

  }

  /**
   * Reports whether the device is open.
   *
   * @return <code>true</code> if the device is open, otherwise
   * <code>false</code>
   * @see #open
   * @see #close
   */
  @Override public boolean isOpen() {
    return false;
  }

  /**
   * Obtains the current time-stamp of the device, in microseconds.
   * If a device supports time-stamps, it should start counting at
   * 0 when the device is opened and continue incrementing its
   * time-stamp in microseconds until the device is closed.
   * If it does not support time-stamps, it should always return
   * -1.
   *
   * @return the current time-stamp of the device in microseconds,
   * or -1 if time-stamping is not supported by the device.
   */
  @Override public long getMicrosecondPosition() {
    return 0;
  }

  /**
   * Obtains the maximum number of MIDI IN connections available on this
   * MIDI device for receiving MIDI data.
   *
   * @return maximum number of MIDI IN connections,
   * or -1 if an unlimited number of connections is available.
   */
  @Override public int getMaxReceivers() {
    return 0;
  }

  /**
   * Obtains the maximum number of MIDI OUT connections available on this
   * MIDI device for transmitting MIDI data.
   *
   * @return maximum number of MIDI OUT connections,
   * or -1 if an unlimited number of connections is available.
   */
  @Override public int getMaxTransmitters() {
    return 0;
  }

  /**
   * Obtains a MIDI IN receiver through which the MIDI device may receive
   * MIDI data.  The returned receiver must be closed when the application
   * has finished using it.
   * <p>
   * <p>Usually the returned receiver implements
   * the {@code MidiDeviceReceiver} interface.
   * <p>
   * <p>Obtaining a <code>Receiver</code> with this method does not
   * open the device. To be able to use the device, it has to be
   * opened explicitly by calling {@link #open}. Also, closing the
   * <code>Receiver</code> does not close the device. It has to be
   * closed explicitly by calling {@link #close}.
   *
   * @return a receiver for the device.
   * @throws MidiUnavailableException thrown if a receiver is not available
   *                                  due to resource restrictions
   * @see Receiver#close()
   */
  @Override public Receiver getReceiver() throws MidiUnavailableException {
    return new MockReceiver(this.sb);
  }

  /**
   * Returns all currently active, non-closed receivers
   * connected with this MidiDevice.
   * A receiver can be removed
   * from the device by closing it.
   * <p>
   * <p>Usually the returned receivers implement
   * the {@code MidiDeviceReceiver} interface.
   *
   * @return an unmodifiable list of the open receivers
   * @since 1.5
   */
  @Override public List<Receiver> getReceivers() {
    return null;
  }

  /**
   * Obtains a MIDI OUT connection from which the MIDI device will transmit
   * MIDI data  The returned transmitter must be closed when the application
   * has finished using it.
   * <p>
   * <p>Usually the returned transmitter implements
   * the {@code MidiDeviceTransmitter} interface.
   * <p>
   * <p>Obtaining a <code>Transmitter</code> with this method does not
   * open the device. To be able to use the device, it has to be
   * opened explicitly by calling {@link #open}. Also, closing the
   * <code>Transmitter</code> does not close the device. It has to be
   * closed explicitly by calling {@link #close}.
   *
   * @return a MIDI OUT transmitter for the device.
   * @throws MidiUnavailableException thrown if a transmitter is not available
   *                                  due to resource restrictions
   * @see Transmitter#close()
   */
  @Override public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  /**
   * Returns all currently active, non-closed transmitters
   * connected with this MidiDevice.
   * A transmitter can be removed
   * from the device by closing it.
   * <p>
   * <p>Usually the returned transmitters implement
   * the {@code MidiDeviceTransmitter} interface.
   *
   * @return an unmodifiable list of the open transmitters
   * @since 1.5
   */
  @Override public List<Transmitter> getTransmitters() {
    return null;
  }
}
