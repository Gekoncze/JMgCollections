package cz.mg.collections.text;

import cz.mg.collections.special.VirtualCollection;
import cz.mg.collections.list.List;


public class VirtualCollectionTest {
    public static class Life {
        private final String name;

        public Life(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Plant extends Life {
        public Plant(String name) {
            super(name);
        }
    }

    public static class Animal extends Life {
        public Animal(String name) {
            super(name);
        }
    }

    public static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
    }

    public static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
    }

    public static void main(String[] args) {
        test(new List<Life>(
                new Plant("Rose"),
                new Plant("Dandelion"),
                new Cat("Neko"),
                new Dog("Inu"),
                new Cat("Meow"),
                new Animal("Some animal")
        ));

        test(new List<Life>());
    }

    private static int id = 0;
    public static void test(List<Life> lifeOnEarth){
        System.out.println("#### TEST " + (id++) + " ###");

        VirtualCollection<Plant> plants = new VirtualCollection<>(lifeOnEarth, Plant.class);
        VirtualCollection<Animal> animals = new VirtualCollection<>(lifeOnEarth, Animal.class);
        VirtualCollection<Cat> cats = new VirtualCollection<>(lifeOnEarth, Cat.class);
        VirtualCollection<Dog> dogs = new VirtualCollection<>(lifeOnEarth, Dog.class);

        System.out.println("Plants:");
        for(Plant plant : plants){
            System.out.println("    " + plant.getName());
        }

        System.out.println("Animals:");
        for(Animal animal : animals){
            System.out.println("    " + animal.getName());
        }

        System.out.println("Cats:");
        for(Cat cat : cats){
            System.out.println("    " + cat.getName());
        }

        System.out.println("Dogs:");
        for(Dog dog : dogs){
            System.out.println("    " + dog.getName());
        }

        System.out.println();
        System.out.println();
    }
}
