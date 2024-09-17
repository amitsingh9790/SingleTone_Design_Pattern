package SingleTone.HeavyResource;
 
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.spec.ECField;

//class HeavyResource implements Cloneable {
class HeavyResource implements Serializable {
     //this is the class which require too much time to load.
     //suppose 10000 lines of code is there
     // I want to create a single object  of these class only and I want to use that object t through
     //out the session and to create such resource we need a class and that class is called singleton class

     // there is default constructor
     //Step: 1 to create private constructor
     private HeavyResource() {
     }
     //Step: 2
     private static HeavyResource instance;
//     private static HeavyResource instance = new HeavyResource(); // early/eager initialization

     //this method will create object
     public static HeavyResource getInstance() { //lazy initialization
         if (instance == null) {
             instance = new HeavyResource();
         }
         return instance;
     }

     // there are two kind of singleton class
     //1. egar --- but they will be auto created without calling them
     //2. lazy --- those object which will be created when you are calling them
//     @Override
//     public Object clone() throws CloneNotSupportedException{
//         return super.clone();
//     }

}

class Main{
    public static void main(String[] args) throws Exception {
        HeavyResource obj1 = HeavyResource.getInstance();

//        HeavyResource obj2 = HeavyResource.getInstance();

        System.out.println(obj1.hashCode());
        // I want single object on multiple call

        // refection api to break the singleton Pattern-
        //1. cloning
//        HeavyResource newObjectWithCloning =  (HeavyResource)obj1.clone();
//        System.out.println(newObjectWithCloning.hashCode());
        //2. reflection api

        Constructor<HeavyResource> cons = HeavyResource.class.getDeclaredConstructor();
        cons.setAccessible(true);

        HeavyResource heavyResourceNewWithReflex = cons.newInstance();
        System.out.println(heavyResourceNewWithReflex.hashCode());


        //3. serialization or deserialization

        //serialization
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("serial.txt"));
        out.writeObject(obj1);
        out.close();

        //deserialization
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("serial.txt"));
        HeavyResource nayaBySerilization = (HeavyResource) in.readObject();

        System.out.println(nayaBySerilization.hashCode());
        in.close();

    }
}
