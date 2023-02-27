package designModel.principle.lsp;


/**
 * 对于resize方法，父类型可以，子类不可以
 * 违反里氏替换原则
 */
public class RectangleDemo {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setLength(20);
        rectangle.setWidth(10);
        //进行扩宽操作
        resize(rectangle);
        printlengthAndWidth(rectangle);
        Square square = new Square();
        square.setLength(10);
        resize(square);
        printlengthAndWidth(square);
    }

    //扩宽方法
    public static void resize(Rectangle rectangle){
        //判断宽如果比长小，进行扩宽的操作
        while (rectangle.getWidth() <= rectangle.getLength()){
            rectangle.setWidth(rectangle.getLength() + 1);
        }
    }

    //打印长和宽
    public static void printlengthAndWidth(Rectangle rectangle){
        System.out.println(rectangle.getLength());
        System.out.println(rectangle.getWidth());
    }

}
