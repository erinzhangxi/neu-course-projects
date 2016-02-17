package cs3500.hw01.duration;

/**
 * Durations represented compactly, with a range of 0 to
 * 2<sup>63</sup>-1 seconds.
 */
public final class CompactDuration extends AbstractDuration {
  /**
   * Constructs a duration in terms of its length in hours, minutes, and
   * seconds.
   *
   * @param hours the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of inSeconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public CompactDuration(int hours, int minutes, int seconds) {
    ensureHms(hours, minutes, seconds);
    this.inSeconds = inSeconds(hours, minutes, seconds);
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   */
  public CompactDuration(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    this.inSeconds = inSeconds;
  }

  private final long inSeconds;

  @Override
  protected Duration fromSeconds(long seconds) {
    return new CompactDuration(seconds);
  }

  @Override
  public long inSeconds() {
    return inSeconds;
  }

  @Override
  public String asHms() {
    return String.format("%d:%02d:%02d",
                          hoursOf(inSeconds),
                          minutesOf(inSeconds),
                          secondsOf(inSeconds));
  }
}
