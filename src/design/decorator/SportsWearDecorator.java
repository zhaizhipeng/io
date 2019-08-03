package design.decorator;

/**
 * 具体装饰-休闲装
 */
public class SportsWearDecorator extends Decorator{

    public SportsWearDecorator(Person person) {
        super(person);
    }

    @Override
    public void getUp() {
        super.getUp();
        this.dressUp();
    }

    public void dressUp(){
        System.out.println("运动装出门！");
    }


}
