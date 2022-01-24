package thinkingJava.serializable;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by 15151 on 2018/11/22.
 */
class LabeledPoint implements Serializable
{
    private String label;
    transient private Point2D.Double point;    //因为不可被序列化，所以需要加transient关键字

    public LabeledPoint(String str, double x, double y)//构造方法
    {
        label = str;
        point = new Point2D.Double(x, y);  //此类Point2D.Double不可被序列化
    }

    //因为Point2D.Double不可被序列化，所以需要实现下面两个方法
    private void writeObject(ObjectOutputStream out) throws IOException
    {

        out.defaultWriteObject();
        out.writeDouble(point.getX());
        out.writeDouble(point.getY());
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {

        in.defaultReadObject();
        double x = in.readDouble() + 1.0;
        double y = in.readDouble() + 1.0;
        point = new Point2D.Double(x, y);
    }

    //重写toString方法
    @Override
    public String toString()
    {
        return getClass().getName()+ "[label = " + label+ ", point.getX() = " + point.getX()+ ", point.getY() = " +

                point.getY()+ "]";
    }

}
