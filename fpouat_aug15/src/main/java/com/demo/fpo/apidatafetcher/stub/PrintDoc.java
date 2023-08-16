
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
 *         &lt;element name="cdsView" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}CDSView" minOccurs="0"/&gt;
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
    "cdsView",
    "userCd"
})
@XmlRootElement(name = "PrintDoc")
public class PrintDoc {

    protected String token;
    @XmlElementRef(name = "cdsView", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<CDSView> cdsView;
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
     * Gets the value of the cdsView property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CDSView }{@code >}
     *     
     */
    public JAXBElement<CDSView> getCdsView() {
        return cdsView;
    }

    /**
     * Sets the value of the cdsView property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CDSView }{@code >}
     *     
     */
    public void setCdsView(JAXBElement<CDSView> value) {
        this.cdsView = value;
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
