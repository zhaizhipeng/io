package design.decorator;

import org.junit.Test;

/**
 * @author zhipeng zhai
 * @date 2019/8/1 0001
 */
public class MainTest {

    @Test
    public void test(){

        Progammer progammer = new Progammer("小Z");
        progammer.getUp();

        JeansWearDecorator jeansWearDecorator = new JeansWearDecorator(progammer);
        jeansWearDecorator.getUp();

        SportsWearDecorator sportsWearDecorator = new SportsWearDecorator(progammer);
        sportsWearDecorator.getUp();
    }

}
