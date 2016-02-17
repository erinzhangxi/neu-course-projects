/*
import tester.Tester;

// Problem 15.2 & 15.3 on page 149

interface ILog {
    // to compute the total number of miles recorded in this log
    double miles();
    
    // to extract those entries in this log for the given month and year
    ILog oneMonth(int month, int year);
    
    // to compute the total distance run in a given month and year
    double totalDistance(int month, int year);
    
    // to compute the length of his longest run ever
    double LongestRun(Date d1, Date d2);
}

class MTLog implements ILog {
    
    // to compute the total number of miles recorded in this MTLog
    public double miles() {
        return 0;
    }
    
    // to extract those entries in this log for the given month and year
    public ILog oneMonth(int month, int year) {
        return this;
    }
    
    // to compute the total distance run in a given month and year
    public double totalDistance(int month, int year) {
        return 0;
    }
    
    // to compute the length of his longest run ever
    public double LongestRun(Date d1, Date d2) {
        return 0;
    }
    
}


class ConsLog implements ILog {
    Entry fst;
    ILog rst;
    
    ConsLog(Entry fst, ILog rst) {
        this.fst = fst;
        this.rst = rst;
    }
    
    // to compute the total number of miles recorded in this ConsLog
    public double miles() {
        return this.fst.distance + this.rst.miles();    
    }
    
    // to extract those entries in this log for the given month and year
    public ILog oneMonth(int m, int y) {
        if(this.fst.sameMonthAndYear(m, y)) {
            return
                    new ConsLog(this.fst, this.rst.oneMonth(m, y));}
        else {
            return this.rst.oneMonth(m, y);
            }
        }
    
    // to compute the total distance run in a given month and year
    public double totalDistance(int month, int year) {
        if (this.fst.sameMonthAndYear(month, year)) {
            return this.miles();}
        else {
            return this.rst.miles();
        }
    }
    
    
    
    // 15.3
    // to compute the length of his longest run ever
    /*public double LongestRun(Date d1, Date d2) {
        return 
    }
    
    
    
}

class Entry {
    Date d;
    double distance; // miles
    int duration; // minutes
    String comment;
    
    Entry(Date d, double distance, int duration, String comment) {
        this.d = d;
        this.distance = distance;
        this.duration = duration;
        this.comment = comment;
    }
    
    // was this entry made in the given month and year
    boolean sameMonthAndYear(int month, int year) {
        return this.d.sameMonthAndYear(month, year);  
    }
    
}

class Date {
    int day;
    int month;
    int year;
    
    Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    // is this date in the given month and year?
    boolean sameMonthAndYear(int month, int year) {
        return (this.month == month) && (this.year == year);
    }
    
}


class CompositeExamples {
    Date d1 = new Date(5, 5, 2003);
    Date d2 = new Date(6, 6, 2003);
    Date d3 = new Date(23, 6, 2003);
    
    Entry e1 = new Entry(d1, 5.0, 25, "Good");
    Entry e2 = new Entry(d2, 3.0, 24, "Tired");
    Entry e3 = new Entry(d3, 26.0, 156, "Great");
    
    ILog l1 = new MTLog();
    ILog l2 = new ConsLog(e1, l1);
    ILog l3 = new ConsLog(e2, l2);
    ILog l4 = new ConsLog(e3, l3);
    
    boolean testLog(Tester t) {
        return t.checkExpect(l1.oneMonth(6, 2003), l1)
                && t.checkExpect(l3.oneMonth(6, 2003), new ConsLog(e2, l1));
    }
    
}

*/