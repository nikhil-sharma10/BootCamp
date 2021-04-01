package exerise1.question5;

public class ObjectCloningUsingClonable implements Cloneable{
    int id;
    String name;

    ObjectCloningUsingClonable(int id, String name, int age, int number){
        this.id = id;
        this.name = name;
        dummyClass.age = age;
        dummyClass.number = number;
    }

    DummyClass dummyClass = new DummyClass();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


