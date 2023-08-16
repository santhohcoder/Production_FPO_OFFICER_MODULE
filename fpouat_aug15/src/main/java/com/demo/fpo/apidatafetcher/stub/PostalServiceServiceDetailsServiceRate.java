
package com.demo.fpo.apidatafetcher.stub;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PostalService.ServiceDetails.ServiceRate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PostalService.ServiceDetails.ServiceRate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ItemMaxWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="RateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RatePeKg" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Base}Value" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalService.ServiceDetails.ServiceRate", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", propOrder = {
    "itemMaxWeight",
    "rateCode",
    "ratePeKg"
})
public class PostalServiceServiceDetailsServiceRate {

    @XmlElementRef(name = "ItemMaxWeight", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> itemMaxWeight;
    @XmlElementRef(name = "RateCode", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> rateCode;
    @XmlElementRef(name = "RatePeKg", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<Value> ratePeKg;

    /**
     * Gets the value of the itemMaxWeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getItemMaxWeight() {
        return itemMaxWeight;
    }

    /**
     * Sets the value of the itemMaxWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setItemMaxWeight(JAXBElement<BigDecimal> value) {
        this.itemMaxWeight = value;
    }

    /**
     * Gets the value of the rateCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRateCode() {
        return rateCode;
    }

    /**
     * Sets the value of the rateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRateCode(JAXBElement<String> value) {
        this.rateCode = value;
    }

    /**
     * Gets the value of the ratePeKg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Value }{@code >}
     *     
     */
    public JAXBElement<Value> getRatePeKg() {
        return ratePeKg;
    }

    /**
     * Sets the value of the ratePeKg property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Value }{@code >}
     *     
     */
    public void setRatePeKg(JAXBElement<Value> value) {
        this.ratePeKg = value;
    }

}
