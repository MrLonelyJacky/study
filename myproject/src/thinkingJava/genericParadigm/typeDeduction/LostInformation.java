package thinkingJava.genericParadigm.typeDeduction;

import java.util.*;

/**
 * Created by 15151 on 2019/4/28.
 */
public class LostInformation {
    public static void main(String[] args) {
        List<Frob> list = new ArrayList<>();
        Map<Frob,Fronkle> map = new HashMap<>();
        Quark<Fronkle> quark = new Quark<>();
        Particle<Long,Double> p = new Particle<>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
        String s = "a:b";
        System.out.println(s.substring(0,0));
    }
}
