
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
 *         &lt;element name="GetHSCodesResult" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}ArrayOfHSLookupInfo" minOccurs="0"/&gt;
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
    "getHSCodesResult"
})
@XmlRootElement(name = "GetHSCodesResponse")
public class GetHSCodesResponse {

    @XmlElementRef(name = "GetHSCodesResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfHSLookupInfo> getHSCodesResult;

    /**
     * Gets the value of the getHSCodesResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfHSLookupInfo }{@code >}
     *     
     */
    public JAXBElement<ArrayOfHSLookupInfo> getGetHSCodesResult() {
        return getHSCodesResult;
    }

    /**
     * Sets the value of the getHSCodesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfHSLookupInfo }{@code >}
     *     
     */
    public void setGetHSCodesResult(JAXBElement<ArrayOfHSLookupInfo> value) {
        this.getHSCodesResult = value;
    }

}
