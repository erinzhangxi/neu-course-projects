/*

// Practice 7
// Problem 14.7 on page 140

interface Grocery {
    double unitPrice();
    boolean lowerUnitPrice(double that);
    boolean cheaperThan();
    
}


class coffee implements Grocery{
    String brand;
    double weight;
    double price;
    boolean decaffeinated;
    
        coffee(String brand, double weight, double price, 
                boolean decaffeinated) {
            this.brand = brand;
            this.weight = weight;
            this.price = price;
            this.decaffeinated = decaffeinated;
        }
        
        // to compute the unit price of this coffee
        public double unitPrice() {
        	return this.price / this.weight;
        }
        
        // to determine whether the unit price of this coffee
        // is lower than the given number
        public boolean lowerUnitPrice(double that) {
        	return this.unitPrice() < that;
        }
}

class juice implements Grocery {
    String brand;
    double weight;
    double price;
    String flavor;
    String packaged;
   
        juice(String brand, double weight, double price, String flavor,
                String packaged) {
            this.brand = brand;
            this.weight = weight;
            this.price = price;
            this.flavor = flavor;
            this.packaged = packaged;
        }
        
        // to compute the unit price of this juice
        public double unitPrice() {
        	return this.price / this.weight;
        }
        
        // to determine whether the unit price of this juice
        // is lower than the given number
        public boolean lowerUnitPrice(double that) {
        	return this.unitPrice() < that;
}

class iceCream implements Grocery {
    String brand;
    double weight;
    double price;
    String flavor;
        
        iceCream(String brand, double weight, double price, String flavor) {
            this.brand = brand;
            this.weight = weight;
            this.price = price;
            this.flavor = flavor;
            }
        
        // to compute the unit price of this ice cream
        public double unitPrice() {
        	return this.price / this.weight;
        }
        
     // to determine whether the unit price of this ice cream
        // is lower than the given number
        public boolean lowerUnitPrice(double that) {
        	return this.unitPrice() < that;
}


boolean cheaperThan() {
    return this.price < that.price;
}

*/
