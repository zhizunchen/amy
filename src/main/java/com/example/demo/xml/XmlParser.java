package com.example.demo.xml;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.parsing.PropertyParser;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Created by chenhe
 * @Date 2019-06-18 09:11
 * @Description  DOM解析
 *
 * number 映射为 java.lang.Double
 * string 映射为 java.lang.String
 * boolean 映射为 java.lang.Boolean
 * node-set 映射为 org.w3c.dom.NodeList
 *
 * XPathConstants.NODESET  org.w3c.dom.NodeList
 * XPathConstants.BOOLEAN  java.lang.Boolean
 * XPathConstants.NUMBER
 * XPathConstants.STRING   java.lang.String
 * XPathConstants.NODE
 *
 * Node.ELEMENT_NODE(1)；//元素节点
 *
 * Node.ATTRIBUTE_NODE(2)；//属性节点
 *
 * Node.TEXT_NODE(3)；//文本节点
 *
 * Node.CDATA_SECTION_NODE(4)；//
 *
 * Node.ENTITY_REFERENCE_NODE(5)；//实体引用节点
 *
 * Node.ENTITY_NODE(6)；//实体节点
 *
 * Node.PROCESSING_INSTRUCTION_NODE(7)；进程节点
 *
 * Node.COMMENT_NODE(8)；//注释节点
 *
 * Node.DOCUMENT_NODE(9)；//文档节点
 *
 * Node.DOCUMENT_TYPE_NODE(10)；//文档类型节点
 *
 * Node.DOCUMENT_FRAGMENT_NODE(11)；//框架节点
 *
 * Node.NOTATION_NODE(12)。//

 */
public class XmlParser {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        // 获取DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 通过 DocumentBuilderFactory 获取 DocumentBuilder
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

        // 得到Document文件， 就是XML在JVM中的化身
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis/mapper/UserMapper.xml");
        Document document = builder.parse(is);

        // 以下通过 XPath 来获取对应的信息
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        // 解析 //CD//TITLE//text() ， 就是获取所有CD节点下TITLE子节点的文字内容
//        XPathExpression expression = xPath.compile("//CD//TITLE//text()");
//        Object result = expression.evaluate(document, XPathConstants.NODESET);

        Object result = xPath.evaluate("//CD//TITLE//text()", document, XPathConstants.NODESET);

        NodeList nodeList = (NodeList)result;
        for (int i =0; i < nodeList.getLength(); i++){
            System.out.println(nodeList.item(i).getNodeValue());
        }


//        //
        Node node = (Node)xPath.evaluate("/mapper", document, XPathConstants.NODE);
//
//        //attributes
//        NamedNodeMap attributesNodes = node.getAttributes();
//        if(null != attributesNodes){
//            for (int i=0; i < attributesNodes.getLength(); i++){
//                Node attribute = attributesNodes.item(i);
//                System.out.println(attribute.getNodeName() + "===" + attribute.getNodeValue());
//            }
//        }
//
//        //body
//        NodeList children = node.getChildNodes();
//        for (int i= 0; i < children.getLength(); i++){
//            Node child = children.item(i);
//            if(child.getNodeType() == Node.TEXT_NODE){
//                String data = ((CharacterData) child).getData();
//                System.out.println("--------- " + child.getNodeName());
//                System.out.println("=======889ß===  " + child.getTextContent());
//            }
//        }

        //
        NodeList nodes = (NodeList)xPath.evaluate("/mapper/resultMap", node, XPathConstants.NODESET);

        for (int j = 0; j < nodes.getLength(); j++) {
            //attributes
            Node node1 = nodes.item(j);
            NamedNodeMap attributesNodes = node1.getAttributes();
            if (null != attributesNodes) {
                for (int i = 0; i < attributesNodes.getLength(); i++) {
                    Node attribute = attributesNodes.item(i);
                    System.out.println(attribute.getNodeName() + "===" + attribute.getNodeValue());
                }
            }

            //body
            NodeList children = node1.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);

                NamedNodeMap attributesNodes1 = child.getAttributes();
                if (null != attributesNodes1) {
                    for (int k = 0; k < attributesNodes1.getLength(); k++) {
                        Node attribute = attributesNodes1.item(k);
                        System.out.println(attribute.getNodeName() + "----" + attribute.getNodeValue());
                    }
                }
                if (child.getNodeType() == Node.TEXT_NODE) {
                    String data = ((CharacterData) child).getData();
                    System.out.println("--------- " + data);
                }
            }
        }
    }
}
