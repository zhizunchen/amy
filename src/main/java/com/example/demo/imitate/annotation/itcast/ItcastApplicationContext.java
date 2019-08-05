package com.example.demo.imitate.annotation.itcast;

import com.example.demo.imitate.annotation.service.PersonService;
import com.example.demo.imitate.annotation.service.impl.PersonServiceImpl;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Created by chenhe
 * @Date 2019-08-03 16:18
 * @Description 模拟spring容器 加载并拼接bean对象之间的关联关系(模拟xml加载bean)
 */
public class ItcastApplicationContext {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private Map<String, Object> sigletons = new HashMap<>();

    public ItcastApplicationContext(String xmlfile) {
        this.readXml(xmlfile);
        this.instanceBeans();
        this.annotationInject();

    }

    //关联关系
    private void annotationInject(){
        for (String beanName  : sigletons.keySet()) {
            try {
                Object bean = sigletons.get(beanName);
                //通过属性setter方法注入value
                PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
                for (PropertyDescriptor p : ps) {
                    Method setter = p.getWriteMethod();// 获取属性的setter方法
                    if(null != setter && setter.isAnnotationPresent(ItcastResource.class)){
                        ItcastResource resource = setter.getAnnotation(ItcastResource.class);
                        Object value = null;
                        if(!resource.name().isEmpty()){
                            value = sigletons.get(resource.name());
                        }else{
                            value = sigletons.get(p.getName());
                            if(null == value){
                                for (String key : sigletons.keySet()) {
                                    // isAssignableFrom(xxx)方法判断propertyDesc.getPropertyType()获得的类型是否是xxx的接口或父类，或者是xxx本身
                                    if(p.getPropertyType().isAssignableFrom(sigletons.get(key).getClass())){
                                        value = sigletons.get(key);
                                        break;
                                    }
                                }
                            }

                        }
                        setter.setAccessible(true);//允许访问私有setter方法
                        setter.invoke(bean, value);//引用对象注入到属性中
                    }
                }
                //通过field 属性注入value
                Field[] fields = bean.getClass().getDeclaredFields();
                for (Field field: fields) {
                    if(field.isAnnotationPresent(ItcastResource.class)){
                        ItcastResource resource = field.getAnnotation(ItcastResource.class);
                        Object value = null;
                        if(!resource.name().isEmpty()){
                            value = sigletons.get(field.getName());
                        }else{
                            value = sigletons.get(resource.name());
                            if(null == value){
                                for (String key:sigletons.keySet()){
                                    if(field.getType().isAssignableFrom(sigletons.get(key).getClass())){
                                        value = sigletons.get(key);
                                        break;
                                    }
                                }
                            }
                        }
                        field.setAccessible(true);
                        field.set(bean, value);
                    }
                }

            }catch (Exception e){}
        }
    }

    //实例化加载的bean
    private void instanceBeans(){
        for (BeanDefinition beanDefinition  :beanDefinitions) {
            try {
                if(!beanDefinition.getClassName().isEmpty()){
                    sigletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
                }
            }catch (Exception e){}
        }
    }

    //解析bean文件
    private void readXml(String xmlfile){
        try {
            //Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws org.xml.sax.SAXException {
                }

                @Override
                public void error(SAXParseException exception) throws org.xml.sax.SAXException {
                }

                @Override
                public void fatalError(SAXParseException exception) throws org.xml.sax.SAXException {
                }
            });

            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(xmlfile);
            Document document = builder.parse(is);

            //XPath
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            Node root = (Node) xPath.evaluate("/beans", document, XPathConstants.NODE);

            NodeList list = (NodeList) xPath.evaluate("/beans/bean", root, XPathConstants.NODESET);
            for (int i = 0; i< list.getLength(); i ++){
                Node node = list.item(i);

                NamedNodeMap nodeMap = node.getAttributes();
                Map<String, String> attMap = new HashMap<>();
                for (int j = 0; j < nodeMap.getLength(); j++) {
                    Node node1 = nodeMap.item(j);
                    attMap.put(node1.getNodeName(), node1.getNodeValue());
                }

                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setId(attMap.get("id"));
                beanDefinition.setClassName(attMap.get("class"));

                beanDefinitions.add(beanDefinition);
            }

        }catch (Exception e){
            System.out.println("===异常" + e.getMessage());
        }
    }

    /**
     * 获取bean实例
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        return this.sigletons.get(beanName);
    }

    public static void main(String[] args) {
        String fileUrl = "beans.xml";

        ItcastApplicationContext context = new ItcastApplicationContext(fileUrl);
        PersonService service = (PersonServiceImpl) context.getBean("personService");
        System.out.println(service.saySomeThing());

    }

}
