
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
 *         &lt;element name="marketedGood" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}MarketedGood" minOccurs="0"/&gt;
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
    "marketedGood"
})
@XmlRootElement(name = "StoreMarketedGood")
public class StoreMarketedGood {

    protected String token;
    @XmlElementRef(name = "marketedGood", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<MarketedGood> marketedGood;

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
     * Gets the value of the marketedGood property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MarketedGood }{@code >}
     *     
     */
    public JAXBElement<MarketedGood> getMarketedGood() {
        return marketedGood;
    }

    /**
     * Sets the value of the marketedGood property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MarketedGood }{@code >}
     *     
     */
    public void setMarketedGood(JAXBElement<MarketedGood> value) {
        this.marketedGood = value;
    }

}
