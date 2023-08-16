
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
 *         &lt;element name="GetWatchdogItemInfoResult" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}WatchdogInfo" minOccurs="0"/&gt;
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
    "getWatchdogItemInfoResult"
})
@XmlRootElement(name = "GetWatchdogItemInfoResponse")
public class GetWatchdogItemInfoResponse {

    @XmlElementRef(name = "GetWatchdogItemInfoResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<WatchdogInfo> getWatchdogItemInfoResult;

    /**
     * Gets the value of the getWatchdogItemInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link WatchdogInfo }{@code >}
     *     
     */
    public JAXBElement<WatchdogInfo> getGetWatchdogItemInfoResult() {
        return getWatchdogItemInfoResult;
    }

    /**
     * Sets the value of the getWatchdogItemInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link WatchdogInfo }{@code >}
     *     
     */
    public void setGetWatchdogItemInfoResult(JAXBElement<WatchdogInfo> value) {
        this.getWatchdogItemInfoResult = value;
    }

}
