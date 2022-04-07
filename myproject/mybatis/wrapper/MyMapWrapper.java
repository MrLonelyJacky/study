package mybatis.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

public class MyMapWrapper extends MapWrapper {



    public MyMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {

        return super.findProperty(name, useCamelCaseMapping);
    }
}
