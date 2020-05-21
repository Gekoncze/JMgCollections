package cz.mg.collections.text;

import cz.mg.collections.VirtualCollection;
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
        System.out.println("### TEST 1 ###");
        test(new List<Life>(
                new Plant("Rose"),
                new Plant("Dandelion"),
                new Cat("Neko"),
                new Dog("Inu"),
                new Cat("Meow"),
                new Animal("Unknown")
        ));
        System.out.println();

        System.out.println("### TEST 2 ###");
        test(new List<Life>(
        ));
        System.out.println();
    }

    public static void test(List<Life> lifeOnEarth){
        VirtualCollection<Plant> plants = new VirtualCollection<>(Plant.class, lifeOnEarth);
        VirtualCollection<Cat> cats = new VirtualCollection<>(Cat.class, lifeOnEarth);
        VirtualCollection<Dog> dogs = new VirtualCollection<>(Dog.class, lifeOnEarth);

        System.out.println("Plants:");
        for(Plant plant : plants){
            System.out.println("    " + plant.getName());
        }

        System.out.println("Cats:");
        for(Cat cat : cats){
            System.out.println("    " + cat.getName());
        }

        System.out.println("Dogs:");
        for(Dog dog : dogs){
            System.out.println("    " + dog.getName());
        }
    }
}
