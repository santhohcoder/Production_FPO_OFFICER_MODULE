
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="token" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid" minOccurs="0"/&gt;
 *         &lt;element name="mailObject" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}MailObject" minOccurs="0"/&gt;
 *         &lt;element name="decl" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}Declaration" minOccurs="0"/&gt;
 *         &lt;element name="evtCd" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="userCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "token",
    "mailObject",
    "decl",
    "evtCd",
    "userCd"
})
@XmlRootElement(name = "StoreDeclaration")
public class StoreDeclaration {

    protected String token;
    @XmlElementRef(name = "mailObject", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<MailObject> mailObject;
    @XmlElementRef(name = "decl", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<Declaration> decl;
    protected Integer evtCd;
    @XmlElementRef(name = "userCd", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> userCd;

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the mailObject property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MailObject }{@code >}
     *     
     */
    public JAXBElement<MailObject> getMailObject() {
        return mailObject;
    }

    /**
     * Sets the value of the mailObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MailObject }{@code >}
     *     
     */
    public void setMailObject(JAXBElement<MailObject> value) {
        this.mailObject = value;
    }

    /**
     * Gets the value of the decl property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     *     
     */
    public JAXBElement<Declaration> getDecl() {
        return decl;
    }

    /**
     * Sets the value of the decl property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     *     
     */
    public void setDecl(JAXBElement<Declaration> value) {
        this.decl = value;
    }

    /**
     * Gets the value of the evtCd property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEvtCd() {
        return evtCd;
    }

    /**
     * Sets the value of the evtCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEvtCd(Integer value) {
        this.evtCd = value;
    }

    /**
     * Gets the value of the userCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUserCd() {
        return userCd;
    }

    /**
     * Sets the value of the userCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUserCd(JAXBElement<String> value) {
        this.userCd = value;
    }

}
