package spring.chapter6;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义属性编辑器
 */
public class DatePropertyEditor extends PropertyEditorSupport {
    private String format ="yyyy-MM-dd";


    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            Date parse = simpleDateFormat.parse(text);
            this.setValue(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
