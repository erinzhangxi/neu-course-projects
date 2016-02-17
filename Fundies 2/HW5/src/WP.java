import tester.Tester;

//Assignment 5

//Zhang Xi

//xizhang2

//Yifan Xing

//xingyif


/*
 *      +--------------------+
 *      |       WebPage      |
 *      +--------------------+
 *      | String       url   |
 *      | String       title |
 *  --- | LoItem       items |
 *  |   +--------------------+  
 *  |
 *  |
 *  |                                   
    |------>   +--------------------------+
               | ILoItem                 |<----------------+
               +--------------------------+                 |
               +--------------------------+                 |
               |                          |                 |
               +--------------------------+                 |
                          |                                 |
                         / \                                |
                         ---                                |
                          |                                 |
            -----------------------------                   |
            |                           |                   |
+--------------------------+   +--------------------------+ |
| MtLoItem                 |   | ConsLoItem               | |
+--------------------------+   +--------------------------+ |
+--------------------------+ +-| Item first               | |
|                          | | | ILoItem rest             |-+
|                          | | +--------------------------+
|                          | | |                          |
+--------------------------+ | |                          |
                             | +--------------------------+
                             v
                   +---------------+           AItem
                   | IItem         |
                   +---------------+
                   |               |
                   +---------------+
                          |
                         / \                                
                         ---                                
                          |                                 
            ------------------------------------------------------                   
            |                           |                        |  
     +------------------+      +------------------+      +------------------+ 
     |      Text        |      |       Image      |      |      Link        |
     +------------------+      +------------------+      +------------------+
     | String contents  |      | String fileName  |      | String  name     |
     |                  |      | int    size      |      | WebPage page     |
     |                  |      | String fileType  |      |                  |
     |                  |      |                  |      |                  |
     +------------------+      +------------------+      +------------------+
              |
             / \                                
             ---                                
              |                                 
    -------------------------
    |                       |
   Header               Paragraph

 */

// represents a Web Page
class WebPage {
    String url;
    String title;
    ILoItem items;

    // constructor for a Web Page
    WebPage(String url, String title, ILoItem items) {
        this.url = url;
        this.title = title;
        this.items = items;
    }

    // to determine whether two Web pages are the same
    boolean sameWebPage(WebPage that) {
        return this.url.equals(that.url) &&
                this.title.equals(that.title) &&
                this.items.sameLoItem(that.items);
    }
}


// represents a List of Item
interface ILoItem {
    // to check if a list is empty
    boolean isEmpty();

    // to determines if two lists contain the same items
    boolean sameLoItem(ILoItem that);

    // to pass in the first element of that list of items as this list
    boolean sameLoItemHelp(ConsLoItem that);
}


// represents an empty list of item
class MtLoItem implements ILoItem {
    
    // to check if a list is empty
    public boolean isEmpty() {
        return true;
    }

    // to determines if two lists contain the same items
    public boolean sameLoItem(ILoItem that) {
        return that.isEmpty();
    }

    // to pass in the first element of that list of items as this list
    public boolean sameLoItemHelp(ConsLoItem that) {
        return false;
    }
}

// represents a Cons list of Item
class ConsLoItem implements ILoItem {
    IItem first;
    ILoItem rest;

    // constructor for a list of Item
    ConsLoItem(IItem first, ILoItem rest) {
        this.first = first;
        this.rest = rest;
    }
    
    // to check if a list is empty
    public boolean isEmpty() {
        return false;
    }
    
    // to determines if two lists contain the same items
    public boolean sameLoItem(ILoItem that) {
        return that.sameLoItemHelp(this);
    }

    // to pass in the first element of that list of items as this list
    public boolean sameLoItemHelp(ConsLoItem that) {
        return this.first.sameItem(that.first) &&
                this.rest.sameLoItem(that.rest);
    }
}

// represents the interface IItem
interface IItem {

    // to check if this Item is an image
    boolean isImage();

    // to check if this Item is a Link
    boolean isLink();

    // to determine if an item is a text
    boolean isText();
    
    // to determine if two Items are the same
    boolean sameItem(IItem that);

    // to determine if an item is a paragraph
    boolean isParagraph();
    
    // to determine if an item is a header
    boolean isHeader();
}

abstract class AItem implements IItem {
    
    // to check if this Item is an image
    public boolean isImage() {
        return false;
    }

    // to check if this Item is a Link
    public boolean isLink() {
        return false;
    }
    
    // to determine if an item is a text
    public boolean isText() {
        return false;
    }

    // to determine if two Items are the same
    abstract public boolean sameItem(IItem that);
    
    // to determine if an item is a paragraph
    public boolean isParagraph() {
        return false;
    }
    
    // to determine if an item is a header
    public boolean isHeader() {
        return false;
    }
}

// represents a Text class
class Text extends AItem {
    String contents;

    // constructor for a text class
    Text(String contents) {
        this.contents = contents;
    }

    // to determine if two Items are the same
    public boolean sameItem(IItem that) {
        if (that.isText()) {
            return (this.sameContents((Text) that));
        }
        else {
            return false;
        }
    } 
    
    // to determine if two headers are the same
    public boolean sameContents(Text that) {
        return this.contents.equals(that.contents);
    }
    
    // to determine if an item is a text
    public boolean isText() {
        return true;
    }
}


// represents a subclass of Text
class Paragraph extends Text {
    // the constructor
    Paragraph(String contents) {
        super(contents);
    }
    
    // to determine if an item is a paragraph
    public boolean isParagraph() {
        return true;
    }
    
    // to determine if this item is a text
    public boolean isText() {
        return false;
    }
    
    // to determine if two items are the same
    public boolean sameItem(IItem that) {
        if (that.isParagraph()) {
            return this.sameParagraph((Paragraph) that);
        }
        else {
            return false;
        }
    }
    
    // to determine if two headers are the same
    public boolean sameParagraph(Paragraph that) {
        return this.contents.equals(that.contents);
    }
}

// represent a subclass of Text
class Header extends Text {
    // the constructor
    Header(String contents) {
        super(contents);
    }
    
    // to determine if an item is a header
    public boolean isHeader() {
        return true;
    }
    
    // to determine if this item is a text
    public boolean isText() {
        return false;
    }
    
    // to determine if two items are the same
    public boolean sameItem(IItem that) {
        if (that.isHeader()) {
            return this.sameHeader((Header) that);
        }
        else {
            return false;
        }
    }
    
    // to determine if two headers are the same
    public boolean sameHeader(Header that) {
        return this.contents.equals(that.contents);
    }
}

// represents an Image class
class Image extends AItem {
    String fileName;
    int size;
    String fileType;

    // constructor for an image class
    Image(String fileName, int size, String fileType) {
        this.fileName = fileName;
        this.size = size;
        this.fileType = fileType;
    }

    // to check if this Item is an Image
    public boolean isImage() {
        return true;
    }

    // to determine if two Items are the same
    public boolean sameItem(IItem that) {
        if (that.isImage()) {
            return this.sameImage((Image) that);
        }
        else {
            return false;
        }
    }

    // to determine whether two images are the same
    boolean sameImage(Image that) {
        return (this.fileName.equals(that.fileName)) &&
                this.size == that.size &&
                this.fileType.equals(that.fileType);
    }
}


// represents a Link class
class Link extends AItem {
    String name;
    WebPage page;

    // constructor for a Link class
    Link(String name, WebPage page) {
        this.name = name;
        this.page = page;
    }

    // to check if this Item is a Link
    public boolean isLink() {
        return true;
    }

    // to determine if two Items are the same
    public boolean sameItem(IItem that) {
        if (that.isLink()) {
            return this.sameLink((Link) that);
        }
        else {
            return false;
        }
    }

    // to determine if two links are the same
    boolean sameLink(Link that) {
        return this.name.equals(that.name) &&
                this.page.sameWebPage(that.page);
    }

}

// represents the examples class
class ExamplesWebPage {
    ILoItem mtlist = new MtLoItem();
    IItem i1 = new Text("Welcome to my website.");
    IItem i2 = new Image("Bear", 100, "jpeg");
    IItem i3 = new Image("Coffee", 500, "png");
    IItem i7 = new Link("Link4", new WebPage("a new Webpage", "Class", this.mtlist));
    IItem i8 = new Text("Welcome to my website.");
    IItem i9 = new Text("The first paragraph.");
    IItem i10 = new Text("The first header.");
    
    Image im1 = new Image("Bear", 100, "jpeg");
    Image im2 = new Image("Dog", 200, "jpeg");
    
    Text t1 = new Text("Happy");
    Text t2 = new Text("Sad");
    
    Link l1 = new Link("Link4", new WebPage("a new Webpage", "Class", this.mtlist));
    Link l2 = new Link("Link3", new WebPage("an old Webpage", "Class", this.mtlist));
    
    IItem p1 = new Paragraph("The first paragraph.");
    IItem h1 = new Header("The first header.");
    IItem h2 = new Header("The first header.");
    IItem p2 = new Paragraph("The first paragraph.");
    
    ILoItem listf = new ConsLoItem(this.p1, new ConsLoItem(this.h1,
            new ConsLoItem(this.i1, new ConsLoItem(this.i2, 
                    new ConsLoItem(this.i7, this.mtlist)))));
    ILoItem listg = new ConsLoItem(this.p1, new ConsLoItem(this.h1,
            new ConsLoItem(this.i1, new ConsLoItem(this.i2, 
                    new ConsLoItem(this.i7, this.mtlist)))));

    ILoItem lista = new ConsLoItem(this.i1, this.mtlist);
    ILoItem listb = new ConsLoItem(this.i2, this.lista);
    WebPage photos = new WebPage("www.google.com/2", "Animal", this.listb);
    WebPage photos2 = new WebPage("www.google.com/2", "Book", this.mtlist);
    IItem i5 = new Link("Link2", this.photos);
    ILoItem listc = new ConsLoItem(this.i3, this.listb);
    WebPage about = new WebPage("www.google.com/3", "Coffee", this.listc);
    IItem i6 = new Link("Link3", this.about);
    ILoItem liste = new ConsLoItem(this.i5, this.lista);
    ILoItem listall = new ConsLoItem(this.i6, new ConsLoItem(this.i7, this.liste));

    WebPage mtPage = new WebPage("mt.com", "Hi", this.mtlist);
    WebPage home = new WebPage("www.google.com/1", "Welcome", this.lista);
    IItem i4 = new Link("Link1", this.home);
    ILoItem listd = new ConsLoItem(this.i4, this.listc);

    WebPage contact = new WebPage("www.google.com/4", "Contact", this.listd);

    // tests the method isImage
    boolean testIsImage(Tester t) {
        return t.checkExpect(this.i1.isImage(), false)
                && t.checkExpect(this.i2.isImage(), true);
    }

    // tests the method isLink
    boolean testIsLink(Tester t) {
        return t.checkExpect(this.i1.isLink(), false)
                && t.checkExpect(this.i7.isLink(), true);
    }

    // test the method sameWebPage
    boolean testSameWebPage(Tester t) {
        return t.checkExpect(this.photos.sameWebPage(this.mtPage), false) &&
                t.checkExpect(this.photos.sameWebPage(this.about), false) &&
                t.checkExpect(this.about.sameWebPage(this.about), true);
    }

    // test the method sameImage
    boolean testSameImage(Tester t) {
        return t.checkExpect(this.im1.sameImage(this.im2), false) &&
                t.checkExpect(this.im1.sameImage(this.im1), true);
    }
    
    // test the method sameLink
    boolean testSameLink(Tester t) {
        return t.checkExpect(this.l1.sameLink(this.l2), false) &&
                t.checkExpect(this.l1.sameLink(this.l1), true);
    }
    
    // test the method isText
    boolean testIsText(Tester t) {
        return t.checkExpect(this.i1.isText(), true) &&
                t.checkExpect(this.i2.isText(), false) &&
                t.checkExpect(this.i3.isText(), false) &&
                t.checkExpect(this.i7.isText(), false) &&
                t.checkExpect(this.h1.isText(), false) &&
                t.checkExpect(this.p1.isText(), false);
    }
    
    // test the method sameItem
    boolean testSameItem(Tester t) {
        return t.checkExpect(this.i1.sameItem(this.i2), false) &&
                t.checkExpect(this.i3.sameItem(this.i7), false) &&
                t.checkExpect(this.i1.sameItem(this.i1), true) &&
                t.checkExpect(this.p1.sameItem(this.h1), false) &&
                t.checkExpect(this.p1.sameItem(this.i1), false) &&
                t.checkExpect(this.h1.sameItem(this.i3), false) &&
                t.checkExpect(this.h1.sameItem(this.h2), true) &&
                t.checkExpect(this.h1.sameItem(this.h1), true) &&
                t.checkExpect(this.p1.sameItem(this.p2), true) &&
                t.checkExpect(this.i1.sameItem(this.i8), true) &&
                t.checkExpect(this.i9.sameItem(this.p1), false) &&
                t.checkExpect(this.i10.sameItem(this.h1), false);
    }
    
    // test the method sameLoItem
    boolean testSameLoItem(Tester t) {
        return t.checkExpect(this.lista.sameLoItem(this.mtlist), false) &&
                t.checkExpect(this.lista.sameLoItem(this.listb), false) &&
                t.checkExpect(this.liste.sameLoItem(this.listall), false) &&
                t.checkExpect(this.lista.sameLoItem(this.lista), true) &&
                t.checkExpect(this.listf.sameLoItem(this.listg), true);
    }
    
    // test the method isEmpty
    boolean testIsEmpty(Tester t) {
        return t.checkExpect(this.mtlist.isEmpty(), true) &&
                t.checkExpect(this.lista.isEmpty(), false);
    }
    
    // test the method isParagraph     TODO
    
    // test the method isHeader     TODO
    
}


