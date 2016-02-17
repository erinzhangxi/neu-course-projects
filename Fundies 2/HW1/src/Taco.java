// Assignment 1
// Zhang Xi
// xizhang2
// Laniado Jonathan
// laniadoj

interface ITaco {

}

class EmptyShell implements ITaco {
    boolean softShell;
    
    EmptyShell(boolean softShell) {
        this.softShell = softShell;
    }
}

class Filled implements ITaco {
    ITaco taco;
    String filling;

    Filled(ITaco taco, String filling) {
        this.taco = taco;
        this.filling = filling;
    }
}

class ExamplesTaco {
    ITaco order1 = 
        new Filled(new Filled(new Filled(new Filled(new EmptyShell(true), "cheddar cheese"),
                "lettuce"), "salsa"), "carnitas");
    ITaco order2 = 
        new Filled(new Filled(new Filled(new EmptyShell(false), "sour cream"), 
                "guacamole"), "veggies");

}

