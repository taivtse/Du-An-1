package poly.app.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HibernateXmlUtil {

    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    File xmlFile;
    Document doc;
    XPathFactory xPathfactory;
    XPath xpath;

    public HibernateXmlUtil() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            xmlFile = new File("src/hibernate.cfg.xml");
            doc = builder.parse(xmlFile.getAbsolutePath());
            xPathfactory = XPathFactory.newInstance();
            xpath = xPathfactory.newXPath();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(HibernateXmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(HibernateXmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HibernateXmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Node getUsernameNode() {
        try {
            XPathExpression expr = xpath.compile("/hibernate-configuration/session-factory/property[@name='hibernate.connection.username']/text()");
            NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            return nl.item(0);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(HibernateXmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Node getPasswordNode() {
        try {
            XPathExpression expr = xpath.compile("/hibernate-configuration/session-factory/property[@name='hibernate.connection.password']/text()");
            NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            return nl.item(0);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(HibernateXmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateNode(Node node) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);

            DOMImplementation domImpl = doc.getImplementation();
            DocumentType doctype = domImpl.createDocumentType("doctype",
                    "-//Hibernate/Hibernate Configuration DTD 3.0//EN",
                    "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());

            transformer.transform(source, result);
            return true;
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(HibernateXmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(HibernateXmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
