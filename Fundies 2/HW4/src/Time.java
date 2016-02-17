// CS 2510 Fall 2015
// Assignment 4

// Zhang Xi
// xizhang2
// Yifan Xing
// xingyif

import tester.*; 

// represent a Time class 
class Time {
    int hour;
    int minute;
    int second;
    
    // constructor for this Time class
    Time(int hour, int minute, int second) {
        
        if (hour >= 0 && hour <= 23) {
            this.hour = hour;
        }
        else {
            throw new IllegalArgumentException("Invalid hour: " 
                    + Integer.toString(hour));  
        }

        if (minute >= 0 && minute <= 59) {
            this.minute = minute;
        }
        else {
            throw new IllegalArgumentException("Invalid minute: " 
                    + Integer.toString(minute));  
        }
        
        if (second >= 0 && second <= 59) {
            this.second = second;
        }
        else {
            throw new IllegalArgumentException("Invalid second: " 
                    + Integer.toString(second));  
        }
    }
    
    // Convenience Constructor
    Time(int hour, int minute) {
        this(hour, minute, 0);
    }
   
    // Convenience Constructor2
    Time(int hour, int minute, boolean isAM) {
        this(hour, minute);
        
        if ((this.hour == 12 && isAM) || (this.hour != 12 && !isAM)) {
            this.hour = (this.hour + 12) % 24;
        }
    }
    
    // to determine if the given Time is the same as this Time
    // two Times are considered the same if they have the same hour,
    // minute, and second values.
    boolean sameTime(Time that) {
        return this.hour == that.hour &&
                this.minute == that.minute &&
                this.second == that.second;
    }
}

// represent a class of Time examples
class ExamplesTime {
    Time time1 = new Time(9, 10, 40);    
    Time time2 = new Time(14, 59, 10);   
    Time time8 = new Time(11, 20, 0);
    
    Time time3 = new Time(11, 20, true);  
    Time time4 = new Time(2, 59, false);   
    
    Time time5 = new Time(7, 30, 0);
    Time time6 = new Time(14, 20, 0);
    Time time7 = new Time(14, 59, 0);        
    Time time9 = new Time(11, 15, true); 
    
    // test the exception of Time examples
    boolean testException(Tester t) {
        return t.checkConstructorException(new IllegalArgumentException(
                        "Invalid hour: 24"), "Time", 24, 32, 20) && 
                t.checkConstructorException(new IllegalArgumentException(
                        "Invalid minute: 60"), "Time", 13, 60, 39) &&
                t.checkConstructorException(new IllegalArgumentException(
                        "Invalid second: 60"), "Time", 3, 56, 60) && 
                t.checkConstructorException(new IllegalArgumentException(
                        "Invalid hour: -3"), "Time", -3, 13, 14) &&
                t.checkConstructorException(new IllegalArgumentException(
                        "Invalid hour: -5"), "Time", -5, 13, false);
    }
  
    // test the method sameTime for the Time class 
    boolean testSameTime(Tester t) {
        return t.checkExpect(this.time2.sameTime(this.time4), false) &&
                t.checkExpect(this.time1.sameTime(this.time1), true) &&
                t.checkExpect(this.time1.sameTime(this.time3), false) &&
                t.checkExpect(this.time4.sameTime(this.time7), true) &&
                t.checkExpect(this.time1.sameTime(this.time7), false) &&
                t.checkExpect(this.time3.sameTime(this.time8), true);
    }
}