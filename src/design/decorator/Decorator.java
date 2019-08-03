package design.decorator;

/**
 * @author zhipeng zhai
 * @date 2019/8/1 0001
 */

/**
 * 抽象装饰
 */
public abstract class Decorator implements Person{

    private Person person;

    public Decorator(Person person) {
        this.person = person;
    }

    @Override
    public void getUp() {
        person.getUp();
    }

}
