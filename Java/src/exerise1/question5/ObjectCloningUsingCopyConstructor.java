package exerise1.question5;

public class ObjectCloningUsingCopyConstructor {

    int id;
    String name;

    ObjectCloningUsingCopyConstructor(ObjectCloningUsingCopyConstructor objectCloningUsingCopyConstructor){
        id = objectCloningUsingCopyConstructor.id;
        name = objectCloningUsingCopyConstructor.name;
    }

    DummyClass dummyClass = new DummyClass();

    public ObjectCloningUsingCopyConstructor(int id, String name, int age, int number){
        this.id = id;
        this.name = name;
        dummyClass.age = age;
        dummyClass.number = number;
    }
}
