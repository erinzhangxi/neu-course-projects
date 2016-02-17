

import tester.Tester;

// Zhang Xi
// xizhang2
// Laniado Jonathan
// laniadoj

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
                   +---------------+
                   | Item          |
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

 */

// a Web Page
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

    /* TEMPLATE:
     * FIELDS:
     * ...this.url...  -- String
     * ...this.title...  -- String
     * ...this.items... -- ILoItem
     * METHODS:
     * ...totalImagesSize()... -- int
     * ...textLength()...    -- int
     * ...images()...        -- String
     * METHODS ON FIELDS:
     * ...this.items.totalImageSize()... -- int
     * ...this.title.textLength()...       -- int
     * ...this.items.textLength()...       -- int
     * ...this.items.images()...           -- String
     */

    // to compute the total images size of this web page
    int totalImageSize() {
        return this.items.totalImageSizeLoItem();
    }

    // to compute the number of letters in all text on this web site
    int textLength() {
        return this.title.length() + this.items.textLengthLoItem();
    }

    // to produce a new String that has all names of images on this web site
    String images() {
        return this.items.imagesLoItem();

    }
}


// a List of Item
interface ILoItem {
    // compute the total size of all images in this list of Item
    int totalImageSizeLoItem();

    // to compute the number of letters in all text on this list of Item
    int textLengthLoItem();

    // to produce a new String that has all names of images on this list of items
    String imagesLoItem();

    // to check if a list is empty
    boolean isEmpty();

    // to check if an image is in a list
    boolean hasImage();

}


// an empty list of item
class MtLoItem implements ILoItem {

    // compute the total size of all images in this empty list of item
    public int totalImageSizeLoItem() {
        return 0;
    }

    // to compute the number of letters in all text on this web site
    public int textLengthLoItem() {
        return 0;
    }

    // to produce a new String that has all names of images on this empty list of item
    public String imagesLoItem() {
        return "";
    }

    // to check if a list is empty
    public boolean isEmpty() {
        return true;
    }

    // to check if an image is in a list
    public boolean hasImage() {
        return false;
    }
}

// a Cons list of Item
class ConsLoItem implements ILoItem {
    IItem first;
    ILoItem rest;

    // constructor for a list of Item
    ConsLoItem(IItem first, ILoItem rest) {
        this.first = first;
        this.rest = rest;
    }

    /* TEMPLATE:
     * FIELDS:
     * ...this.fst... -- IItem
     * ...this.rst... -- ILoItem
     * METHODS:
     * ...totalImageSizeLoItem()...  -- int
     * ...textLengthLoItem()... -- int
     * ...imagesLoItem()...     -- String
     * METHODS ON FIELDS:
     * ...this.fst.textLengthLoItem()... -- int
     * ...this.rst.textLengthLoItem()... -- int
     * ...this.fst.imagesLoItem()...     -- String
     * ...this.rst.imagesLoItem()...     -- String
     */

    // compute the total size of all images in this list of items
    public int totalImageSizeLoItem() {
        return this.first.imageSize() + this.rest.totalImageSizeLoItem();
    }   

    // to compute the number of letters in all text on this web site
    public int textLengthLoItem() {
        return this.first.textLengthItem() + this.rest.textLengthLoItem();
    }

    // to check if a list is empty
    public boolean isEmpty() {
        return false;
    }

    // to check if an image is in a list
    public boolean hasImage() {
        return this.first.isImage() || this.rest.hasImage();
    }


    // to produce a new String that has all names of images on this list of items    
    public String imagesLoItem() {
        /* if(this.rest.hasImage()) {
            return this.first.imagesItem() + ", " + this.rest.imagesLoItem();
        }
        else {
            return this.first.imagesItem();
        }
    }
    
    if (this.first.isImage() || this.first.isLink()) {
            if (this.rest.imagesLoItem().equals("")) {
                return this.first.imagesItem();
            }
            else {
            return this.first.imagesItem() + ", " + this.rest.imagesLoItem();
            }
        }
        else {
            return this.rest.imagesLoItem();
    
         */
        if (this.first.isImage() || this.first.isLink()) {
            if (this.rest.imagesLoItem().equalsIgnoreCase("")) {
                return this.first.imagesItem();
            }
            else {
                return this.first.imagesItem() + ", " + this.rest.imagesLoItem();
            }
        }
        else {
           return this.rest.imagesLoItem();
        }
    }
}


interface IItem {

    // to calculate the size of this item
    int imageSize();

    // to calculate the length of this item
    int textLengthItem();

    // to produce a new String that has its name and file types of this item 
    String imagesItem();

    // to check if this Item is an image
    boolean isImage();
    
    // to check if this Item is a Link
    boolean isLink();

}

// a Text class
class Text implements IItem {
    String contents;

    // constructor for a text class
    Text(String contents) {
        this.contents = contents;
    }

    /* TEMPLATES:
     * FIELDS:
     * ... this.contents... -- String
     */

    // to calculate the size of this text
    public int imageSize() {
        return 0;
    }

    // to calculate the length of this text
    public int textLengthItem() {
        return this.contents.length();
    }

    // to produce a new String that has its name and file types of this Text 
    public String imagesItem() {
        return "";
    }

    // to check if this Item is an Image
    public boolean isImage() {
        return false;
    }
    
    // to check if this Item is a Link
    public boolean isLink() {
        return false;
    }

}

// an Image class
class Image implements IItem {
    String fileName;
    int size;
    String fileType;

    // constructor for an image class
    Image(String fileName, int size, String fileType) {
        this.fileName = fileName;
        this.size = size;
        this.fileType = fileType;
    }

    /* TEMPLATE:
     * FIELDS:
     * ... this.fileName...  -- String
     * ... this.size... -- int
     * ... this.fileType... -- String
     */

    // to calculate the size of this image
    public int imageSize() {
        return this.size;
    }

    // to calculate the length of this image
    public int textLengthItem() {
        return this.fileName.length() + this.fileType.length();
    }


    // to produce a new String that has its name and file types of this Image 
    public String imagesItem() {
        return new String(this.fileName + "." + this.fileType);
    }

    // to check if this Item is an Image
    public boolean isImage() {
        return true;
    }
    
    // to check if this Item is a Link
    public boolean isLink() {
        return false;
    }

}


// a Link class
class Link implements IItem {
    String name;
    WebPage page;

    // constructor for a Link class
    Link(String name, WebPage page) {
        this.name = name;
        this.page = page;
    }

    /* TEMPLATES:
     * FIELDS:
     * ... this.name ...   -- String
     * ... this.page ...   -- WebPage
     */

    // to calculate the size of this link
    public int imageSize() {
        return this.page.totalImageSize();
    }

    // to calculate the length of this link
    public int textLengthItem() {
        return this.name.length() + this.page.textLength();
    }

    // to produce a new String that has its name and file types of this Link 
    public String imagesItem() {
        return this.page.images();
    }

    // to check if this Item is an Image
    public boolean isImage() {
        return false;
    }
    
    // to check if this Item is a Link
    public boolean isLink() {
        return true;
    }

}


class ExamplesWebPage {
    ILoItem mtlist = new MtLoItem();
    IItem i1 = new Text("Welcome to my website.");
    IItem i2 = new Image("Bear", 100, "jpeg");
    IItem i3 = new Image("Coffee", 500, "png");
    IItem i7 = new Link("Link4", new WebPage("a new Webpage", "Class", this.mtlist));

    ILoItem lista = new ConsLoItem(this.i1, this.mtlist);
    ILoItem listb = new ConsLoItem(this.i2, this.lista);
    WebPage photos = new WebPage("www.google.com/2", "Animal", this.listb);
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

    // A web site that has at least one text, two images, three pages, and four links
    WebPage question1 = new WebPage("www.hello.com", "A Website", this.listall);

    WebPage HtDP = new WebPage("htdp.org", "HtDP", 
            (new ConsLoItem(new Text("How to Design Programs"),
                    (new ConsLoItem(new Image("htdp", 4300, "tiff"), this.mtlist)))));

    ConsLoItem test1 = new ConsLoItem(new Text("Stay classy, Java"),
            new ConsLoItem(new Link("Back to the Future", this.HtDP), new MtLoItem()));

    WebPage OOD = new WebPage("ccs.neu.edu/OOD", "OOD", this.test1);

    WebPage fundiesWP = new WebPage("ccs.neu.edu/Fundies2", "Fundies II",
            (new ConsLoItem(new Text("Home sweet home"), 
                    (new ConsLoItem(new Image("wvh-lab", 400, "png"),
                            (new ConsLoItem(new Text("The staff"),
                                    (new ConsLoItem(new Image("profs", 240, "jpeg"),
                                            (new ConsLoItem(new Link("A Look Back",
                                                    this.HtDP),
                                                    (new ConsLoItem(
                                                            new Link("A Look Ahead",
                                                                    this.OOD), 
                                                            this.mtlist)))))))))))));
    boolean testIsEmpty(Tester t) {
        return t.checkExpect(this.mtlist.isEmpty(), true) 
                && t.checkExpect(this.lista.isEmpty(), false);
    }

    boolean testIsImage(Tester t) {
        return t.checkExpect(this.i1.isImage(), false)
                && t.checkExpect(this.i2.isImage(), true);
    }

    boolean testOne(Tester t) {
        return t.checkExpect(this.test1.first, new Text("Stay classy, Java"));
    }

    boolean testTotalImageSize(Tester t) {
        return t.checkExpect(this.fundiesWP.totalImageSize(), 9240)
                && t.checkExpect(this.HtDP.totalImageSize(), 4300)
                && t.checkExpect(this.mtlist.totalImageSizeLoItem(), 0)
                && t.checkExpect(this.listb.totalImageSizeLoItem(), 100)
                && t.checkExpect(this.i1.imageSize(), 0)
                && t.checkExpect(this.i3.imageSize(), 500)
                && t.checkExpect(this.i4.imageSize(), 0);
    }

    boolean testTextLength(Tester t) {
        return t.checkExpect(this.home.textLength(), 29)
                && t.checkExpect(this.mtPage.textLength(), 2)
                && t.checkExpect(this.mtlist.textLengthLoItem(), 0)
                && t.checkExpect(this.listb.textLengthLoItem(), 30)
                && t.checkExpect(this.i2.textLengthItem(), 8)
                && t.checkExpect(this.i4.textLengthItem(), 34);
    }

    boolean testHasImages(Tester t) {
        return t.checkExpect(this.mtlist.hasImage(), false)
                && t.checkExpect(this.lista.hasImage(), true);
    }
    
    boolean testIsLink(Tester t) {
        return t.checkExpect(this.i1.isLink(), false)
                && t.checkExpect(this.i7.isLink(), true);
    }
    
    boolean testImages(Tester t) {
        return t.checkExpect(this.mtPage.images(), "")
                && t.checkExpect(this.photos.images(), "Bear.jpeg")
                && t.checkExpect(this.mtlist.imagesLoItem(), "")
                && t.checkExpect(this.contact.images(), "Coffee.png, Bear.jpeg")
                && t.checkExpect(this.i4.imagesItem(), "")
                && t.checkExpect(this.i1.imagesItem(), "")
                && t.checkExpect(this.i2.imagesItem(), "Bear.jpeg")
                && t.checkExpect(this.fundiesWP.images(),
                        "wvh-lab.png, profs.jpeg, htdp.tiff, htdp.tiff");
    }
}


