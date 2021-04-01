package exerise1.question3;

public class TestClassDefExceptions {
    public static void main(String[] args) {
        TestClassDefExceptions testClassDefExceptions = new TestClassDefExceptions();
        testClassDefExceptions.noClassFoundException("Nikhil");
        try {
            testClassDefExceptions.noClassDefException();

        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
        }
    }

    public void noClassFoundException(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void noClassDefException() {
        ClassDef classDef = new ClassDef();
        classDef.status();
    }
}
