package spring.chapter2.dtdXsd;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class NoteBeanParser extends AbstractSingleBeanDefinitionParser {
    @SuppressWarnings("rawtypes")
    protected Class getBeanClass(Element element) {
        return Note.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");
        if (StringUtils.hasText(userName)) {
            bean.addPropertyValue("userName", userName);
        }
        if (StringUtils.hasText(email)){
            bean.addPropertyValue("email", email);
        }

    }
}
