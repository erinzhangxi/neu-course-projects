// Assignment 1
// Zhang Xi
// xizhang2
// Laniado Jonathan
// laniadoj

class Person {
    String name;
    int yob;
    String state;
    boolean citizen;
    
    Person(String name, int yob, String state, boolean citizen) {
        this.name = name;
        this.yob = yob; 
        this.state = state;
        this.citizen = citizen;
    }
}

class ExamplesPerson {
    Person noam = new Person("Noam Chomsky", 1928, "MA", true);
    Person yanis = new Person("Yanis Varoufakis", 1961, "TX", false);
    Person erin = new Person("Erin", 1996, "NM", false);
    Person jack = new Person("Jack", 1970, "NY", true);
    Person mike = new Person("Mike", 1990, "AZ", false);
}
