package design.decorator;

/**
 * @author zhipeng zhai
 * @date 2019/8/1 0001
 */

/**
 * 具体构建-程序员类
 */
public class Progammer implements Person{

    private String name;

    public Progammer(String name) {
        this.name = name;
    }

    @Override
    public void getUp() {
        System.out.println(name + "起床啦！");
    }
}
