package exerise1.question4;

public class SingletonClass {

    private static SingletonClass singleInstance = null;
    public String string ;

    private SingletonClass(){
        string = "Inside singleton class constructor";
    }

    public static SingletonClass SingletonClass(){
        if(singleInstance == null){
            singleInstance = new SingletonClass();
        }
        return singleInstance;
    }
}
