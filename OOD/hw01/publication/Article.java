////
//// YOU SHOULD NOT MODIFY THIS FILE
////
//// (You don't need to submit it, either.)
////
package cs3500.hw01.publication;

/**
 * Represents bibliographic information for journal articles.
 */
public class Article implements Publication {
    private final String title, author, journal;
    private final int volume, issue, year;

    /**
     * Constructs an article.
     *
     * @param title the title of the article
     * @param author the author of the article
     * @param journal the journal in which the article appears
     * @param volume the volume of the journal
     * @param issue the issue of the journal
     * @param year the year of the journal
     */
    public Article(String title, String author, String journal, int volume,
                   int issue, int year)
    {
        this.title = title;
        this.author = author;
        this.journal = journal;
        this.volume = volume;
        this.issue = issue;
        this.year = year;
    }

    @Override
    public String citeApa() {
        return author + " (" + year + "). " + title + ". " +
                journal + ", " + volume + "(" + issue + ").";
    }

    @Override
    public String citeMla() {
        return author + ". \"" + title + ".\" " + journal + " " +
                volume + "." + issue + " (" + year + ").";
    }
}
