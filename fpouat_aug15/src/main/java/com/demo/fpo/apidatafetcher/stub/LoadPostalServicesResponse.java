
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
 *         &lt;element name="LoadPostalServicesResult" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}ArrayOfPostalService" minOccurs="0"/&gt;
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
    "loadPostalServicesResult"
})
@XmlRootElement(name = "LoadPostalServicesResponse")
public class LoadPostalServicesResponse {

    @XmlElementRef(name = "LoadPostalServicesResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfPostalService> loadPostalServicesResult;

    /**
     * Gets the value of the loadPostalServicesResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPostalService }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPostalService> getLoadPostalServicesResult() {
        return loadPostalServicesResult;
    }

    /**
     * Sets the value of the loadPostalServicesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPostalService }{@code >}
     *     
     */
    public void setLoadPostalServicesResult(JAXBElement<ArrayOfPostalService> value) {
        this.loadPostalServicesResult = value;
    }

}
