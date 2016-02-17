package cs3500.hw01.duration;

/**
 * Abstract base class for implementations of {@link Duration}.
 */
abstract class AbstractDuration implements Duration {
  /**
   * Converts an unpacked hours-minutes-seconds duration to its length in seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @return the duration in seconds
   */
  protected static long inSeconds(int hours, int minutes, int seconds) {
    return 3600 * hours + 60 * minutes + seconds;
  }

  /**
   * Formats an unpacked hours-minutes-seconds duration in the same {@code H:MM:SS} format that
   * {@link Duration#asHms()} returns. Assumes that
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @return formatted duration
   * @throws IllegalArgumentException if any argument is negative
   */
  protected static String asHms(int hours, int minutes, int seconds) {
    return String.format("%d:%02d:%02d", hours, minutes, seconds);
  }

  /**
   * Ensures that the hours, minutes, and seconds are all non-negative. Is factoring this out
   * overkill? Or should we also factor out the {@code inSeconds < 0} check in the two unary
   * constructors? Discuss.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  protected static void ensureHms(int hours, int minutes, int seconds) {
    if (hours < 0 || minutes < 0 || seconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }
  }

  /**
   * Returns the number of whole hours in the given number of seconds.
   *
   * @param inSeconds the total number of seconds
   * @return the number of hours
   * @throws ArithmeticException if the correct result cannot fit in an {@code int}.
   */
  protected static int hoursOf(long inSeconds) {
    if (inSeconds / 3600 > Integer.MAX_VALUE) {
      throw new ArithmeticException("result cannot fit in type");
    }

    return (int) (inSeconds / 3600);
  }

  /**
   * Returns the number of whole minutes in the given number of seconds, less the number of whole
   * hours.
   *
   * @param inSeconds the total number of seconds
   * @return the number of remaining minutes
   */
  protected static int minutesOf(long inSeconds) {
    return (int) (inSeconds / 60 % 60);
  }

  /**
   * Returns the number of seconds remaining after all full minutes are removed from the given
   * number of seconds.
   *
   * @param inSeconds the total number of seconds
   * @return the number of remaining seconds
   */
  protected static int secondsOf(long inSeconds) {
    return (int) (inSeconds % 60);
  }

  /**
   * Constructs a {@link Duration} in a manner selected by concrete subclasses of this class.
   *
   * @param inSeconds the length in seconds
   * @return the new {@code Duration}
   */
  protected abstract Duration fromSeconds(long inSeconds);

  @Override
  public String toString() {
    return asHms();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Duration)) {
      return false;
    }

    return ((Duration) that).inSeconds() == this.inSeconds();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(inSeconds());
  }

  @Override
  public int compareTo(Duration that) {
    return Long.compare(this.inSeconds(), that.inSeconds());
  }

  @Override
  public Duration plus(Duration that) {
    return fromSeconds(this.inSeconds() + that.inSeconds());
  }


  public String format(String template) {
    String result = "";
    if (!(template.contains("%"))) {

      throw new IllegalArgumentException("Incorrect format.");

    } else {

      for (int i = 0; i < template.length(); i++) {

        if ((template.charAt(i) == '%')
                && (i < template.length() - 1)) {

          while ((template.charAt(i + 1)) == '%') {
            result += "%";
            i++;
          }

          result += switch_s(Character.toString(template.charAt(i + 1)));
          i = i + 1;

        } else {
          result += Character.toString(template.charAt(i));
        }
      }
      return result;
    }
  }

  /**
   * Replace the given string with a number(non-negative)
   *
   * @param s the string after "%", throw an exception if it is not one of the given cases
   * @return a number representing hours, minutes, or hours
   */
  public String switch_s(String s) {
    String output = "";
    switch (s) {
      case "h":
        output += Integer.toString(hoursOf(inSeconds()));
        break;
      case "m":
        output += Integer.toString(minutesOf(inSeconds()));
        break;
      case "s":
        output += Integer.toString(secondsOf(inSeconds()));
        break;
      case "H":
        if (hoursOf(inSeconds()) < 10) {
          output += "0" + Integer.toString(hoursOf(inSeconds()));
        } else {
          output += Integer.toString(hoursOf(inSeconds()));
        }
        break;
      case "M":
        if (minutesOf(inSeconds()) < 10) {
          output += "0" + Integer.toString(minutesOf(inSeconds()));
        } else {
          output += Integer.toString(minutesOf(inSeconds()));
        }
        break;
      case "S":
        if (secondsOf(inSeconds()) < 10) {
          output += "0" + Integer.toString(secondsOf(inSeconds()));
        } else {
          output += Integer.toString(secondsOf(inSeconds()));
        }
        break;
      case " ":
        output += " ";
        break;
      case "t":
        output += Long.toString(inSeconds());
        break;
      default:
        throw new IllegalArgumentException("This is malformed.");
    }
    return output;

  }
}


