package SingleTone.HeavyResource;

 class HeavyResource {
     //these is the class which require too much time to load.
     //suppose 10000 lines of code is there
     // I want to create a single object  of these class only and I want to use that object t through
     //out the session and to create such resource we need a class and that class is called singleton class

     // there is default constructor
     //Step: 1 to create private constructor
     private HeavyResource() {
     }
     //Step: 2
     private static HeavyResource instance = new HeavyResource(); // early/eager initialization

     //this method will create object
     public static HeavyResource getInstance() { //lazy initialization
         if (instance == null) {
             instance = new HeavyResource();
         }
         return instance;
     }

     // there are two kind of singleton class
     //1. egar --- but they will be auto created without calling them
     //2. lazy --- those object which will be create when you are calling them
}

class Main{
    public static void main(String[] args) {
        HeavyResource obj1 = HeavyResource.getInstance();

        HeavyResource obj2 = HeavyResource.getInstance();

        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        // I want single object on multiple call
    }
}
