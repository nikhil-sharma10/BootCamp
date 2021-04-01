package exerise1.question4;

public class TestSingletonClass {

    public static void main(String[] args) {
        SingletonClass singletonClass1 = SingletonClass.SingletonClass();
        SingletonClass singletonClass2 = SingletonClass.SingletonClass();
        SingletonClass singletonClass3 = SingletonClass.SingletonClass();

        //Changing the case to upper case for only one instance of singletonClass i.e. singletonClass1
        singletonClass1.string = (singletonClass1.string).toUpperCase();

        System.out.println("String from 1st object:"+ singletonClass1.string);
        System.out.println("String from 2nd object:"+ singletonClass2.string);
        System.out.println("String from 3rd object:"+ singletonClass3.string);

        //Now again changing the case to lower case for only instance of singletonClass i.e. singletonClass2
        singletonClass2.string = (singletonClass2.string).toLowerCase();

        System.out.println("String from 1st object:"+ singletonClass1.string);
        System.out.println("String from 2nd object:"+ singletonClass2.string);
        System.out.println("String from 3rd object:"+ singletonClass3.string);
    }
}
