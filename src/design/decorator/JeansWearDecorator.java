package design.decorator;

/**
 * 具体装饰-牛仔装
 */
public class JeansWearDecorator extends Decorator{

    public JeansWearDecorator(Person person) {
        super(person);
    }

    @Override
    public void getUp(){
        super.getUp();
        this.dressUp();
    }

    public void dressUp() {
        System.out.println("牛仔裤出门！");
    }

}
