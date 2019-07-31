package orz.an.design.pattern.decorator;

public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component){
        super(component);
    }


    private  void myMethod(){

    }


    public void operation(){

        myMethod(); //  修饰

        super.operation();

    }

}
