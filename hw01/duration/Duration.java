////
//// YOU SHOULD NOT MODIFY THIS FILE
////
//// (You don't need to submit it, either.)
////
package cs3500.hw01.duration;

/**
 * Durations, with a resolution of seconds. All durations are non-negative.
 *
 * <p>Different {@code Duration} implementations should work together,
 * meaning that:
 *
 * <ul>
 *   <li>Two durations must be equal if they have the same number of seconds.
 *   <li>The hash code of a duration is the result of calling
 *        {@link Long#hashCode(long)} on its length in seconds.
 * </ul>
 */
public interface Duration extends Comparable<Duration> {
  /**
   * Formats a duration as a string by substituting for format
   * specifiers in the template.
   *
   * <p>The template is a string that may contain both <i>fixed
   * text</i> and some number of <i>format specifiers</i>, which are
   * special two-character codes starting with a {@code %} character.
   * This method returns a string that is like the template string,
   * with the fixed text copied unchanged and the format specifiers
   * replaced by textual representations of the indicated values for
   * {@code this} duration.
   *
   * <p>More precisely, a template is a sequence of uninterpreted
   * characters and two-character format specifiers. An uninterpreted
   * character may be any character but {@code '%'}. A format specifier
   * may be any of the two-character codes defined in the table below,
   * and nothing else. The result string is the concatenation of the
   * meanings of each of the elements of the template sequence, where
   * the meaning of an uninterpreted character is itself, and the
   * meaning of a format specifier is a string as defined in the table
   * below.
   *
   * <p>The above implies that in cases where two format specifiers
   * appear to overlap (<i>e.g.,</i> {@code "%%t"}), the left-most
   * specifier takes precedence (so the result in this case would be
   * {@code "%t"}). Alternatively, we could say that the format
   * specifiers are interpreted from left to right, and the result of
   * converting a specifier is never interpreted a second time.
   *
   * <p>A template is malformed if it doesn't meet the definition of
   * template above. Another way to think of this is that in a
   * well-formed template, every {@code '%'} character is followed by
   * one of the eight characters that follow {@code %} in the table
   * below, except when it is the following character for another
   * {@code %}.
   *
   * <table>
   *   <thead>
   *     <tr>
   *       <th>Format Specifier</th>
   *       <th>Meaning</th>
   *     </tr>
   *   </thead>
   *   <tr><td>{@code %t}</td><td>the whole duration in seconds</td></tr>
   *   <tr><td>{@code %h}</td><td>the hours component of the decomposed
   *     duration</td></tr>
   *   <tr><td>{@code %H}</td><td>the hours component of the decomposed
   *     duration, padded to 2 digits with leading zeros (<i>e.g.</i>,
   *     {@code 05} or {@code 11})</td></tr>
   *   <tr><td>{@code %m}</td><td>the minutes component of the decomposed
   *     duration</td></tr>
   *   <tr><td>{@code %M}</td><td>the minutes component of the decomposed
   *     duration, padded to 2 digits with leading zeros (<i>e.g.</i>,
   *     {@code 05} or {@code 56})</td></tr>
   *   <tr><td>{@code %s}</td><td>the seconds component of the decomposed
   *     duration</td></tr>
   *   <tr><td>{@code %S}</td><td>the seconds component of the decomposed
   *     duration, padded to 2 digits with leading zeros (<i>e.g.</i>,
   *     {@code 05} or {@code 56})</td></tr>
   *   <tr><td>{@code %%}</td><td>a literal percent sign ({@code %})</td></tr>
   *   <caption>Format specifiers</caption>
   * </table>
   *
   * @param template the template
   * @return the formatted string
   * @throws IllegalArgumentException if {@code template} is malformed
   */
  String format(String template);

  /**
   * Gets the total duration in seconds.
   *
   * @return the number of seconds (non-negative)
   */
  long inSeconds();

  /**
   * Formats this duration in the form {@code H:MM:SS} where the hours and
   * minutes are both zero-padded to two digits, but the hours are not.
   * The duration should be in canonical form, meaning that both the minutes
   * and the seconds are less than 60.
   *
   * @return this duration formatted in hours, minutes, and seconds
   */
  String asHms();

  /**
   * Returns the sum of two durations.
   *
   * @param other the duration to add to {@code this}
   * @return the sum of the durations
   */
  Duration plus(Duration other);
}
