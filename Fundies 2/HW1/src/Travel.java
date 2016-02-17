// Assignment 1
// Zhang Xi
// xizhang2
// Laniado Jonathan
// laniadoj


interface IHabitation {

}

class Planet implements IHabitation {
    String name;
    int population;
    int spaceports;
    
    Planet(String name, int population, int spaceports) {
        this.name = name;
        this.population = population;
        this.spaceports = spaceports;
    }
}

class SpaceStation implements IHabitation {
    String name;
    int population;
    int transporterPads;
    
    SpaceStation(String name, int population, int transporterPads) {
        this.name = name;
        this.population = population;
        this.transporterPads = transporterPads;
    }
}


interface ITransportation {
    
}

class Transporter implements ITransportation {
    IHabitation from;
    IHabitation to;
    
    Transporter(IHabitation from, IHabitation to) {
        this.from = from; 
        this.to = to;
    }
    
}

class Shuttle implements ITransportation {
    IHabitation from;
    IHabitation to;
    int fuel;
    int capacity;
    
    Shuttle(IHabitation from, IHabitation to, int fuel, int capacity) {
        this.from = from;
        this.to = to;
        this.fuel = fuel;
        this.capacity = capacity;
    }
}

class SpaceElevator implements ITransportation {
    IHabitation from;
    IHabitation to;
    int tonnage;
    
    SpaceElevator(IHabitation from, IHabitation to, int tonnage) {
        this.from = from;
        this.to = to;
        this.tonnage = tonnage;
    }
}

class ExamplesTravel {
    
    IHabitation nausicant = new Planet("Nausicant", 6000000, 8);
    IHabitation earth = new Planet("Earth", 9000000, 14);
    IHabitation remus = new Planet("Remus", 18000000, 23);
    IHabitation watcherGrid = new SpaceStation("WatcherGrid", 1, 0);
    IHabitation deepSpace42 = new SpaceStation("Deep Space 42", 7, 8);
    IHabitation mars = new Planet("Mars", 9000000, 10);
    
    ITransportation transporter1 = 
            new Transporter(this.deepSpace42, this.nausicant);
    ITransportation transporter2 = 
            new Transporter(this.earth, this.nausicant);
    ITransportation shuttle1 =
            new Shuttle(this.watcherGrid, this.deepSpace42, 100, 5);
    ITransportation shuttle2 = 
            new Shuttle(this.nausicant, this.earth, 50, 3);
    ITransportation elevator1 = 
            new SpaceElevator(this.mars, this.watcherGrid, 100);
    ITransportation elevator2 = 
            new SpaceElevator(this.deepSpace42, this.earth, 200);
}