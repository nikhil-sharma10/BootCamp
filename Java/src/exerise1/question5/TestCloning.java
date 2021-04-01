package exerise1.question5;

public class TestCloning {
    public static void main(String[] args) {
        usingCopy();
        usingCloneable();
    }

    public static void usingCopy(){

        //Hard cloning
        ObjectCloningUsingCopyConstructor c1 = new ObjectCloningUsingCopyConstructor(23,"Nikhil",21,78);
        ObjectCloningUsingCopyConstructor c2 = new ObjectCloningUsingCopyConstructor(c1);
        c2.id = 4067;
        c2.name = "Ankit";
        c2.dummyClass.number = 45;
        c2.dummyClass.age = 34;
        System.out.println("Inside Copy Constructor");
        System.out.println("Data of 1st object:"+ c1.id + " " + c1.name + " " + c1.dummyClass.age + " " + c1.dummyClass.number);
        System.out.println("Data of 2nd object:"+ c2.id + " " + c2.name + " " + c2.dummyClass.age + " " + c2.dummyClass.number);
    }

    public static void usingCloneable(){
        try{

            //Shallow cloning
            ObjectCloningUsingClonable c1 = new ObjectCloningUsingClonable(24,"Sharma",21,34);
            ObjectCloningUsingClonable c2 = (ObjectCloningUsingClonable)c1.clone();
            System.out.println("Using Cloneable");
            c2.id = 9087;
            c2.name = "Praveen";
            c2.dummyClass.number = 47;
            c2.dummyClass.age = 78;
            System.out.println("Data of 1st object:"+ c1.id + " " + c1.name + " " + c1.dummyClass.age + " " + c1.dummyClass.number);
            System.out.println("Data of 2nd object:"+ c2.id + " " + c2.name + " " + c2.dummyClass.age + " " + c2.dummyClass.number);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
