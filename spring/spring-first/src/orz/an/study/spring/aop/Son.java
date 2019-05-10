package orz.an.study.spring.aop;

public class Son {


    public String study() {
        System.out.println("Son : i'm studying ");
        return "Son : study over ";

    }

    public String play() {

        System.out.println("Son : i'm playing ");

        // throw new IllegalArgumentException();

        return "Son : game over ";
    }

    public void wrong() {

        System.out.println("Son : i'm hurt ");

        throw new IllegalArgumentException();

    }

}
