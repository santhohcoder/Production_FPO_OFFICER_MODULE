
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PostalService.ServiceDetails.ServiceOptions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PostalService.ServiceDetails.ServiceOptions"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CODFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DelivConfirmation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="HandlingClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="HoldForPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="OnDemandPickup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="OnTimeDelivGuarantee" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="SignatureRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Tracking" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalService.ServiceDetails.ServiceOptions", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", propOrder = {
    "codFlag",
    "delivConfirmation",
    "handlingClass",
    "holdForPickup",
    "onDemandPickup",
    "onTimeDelivGuarantee",
    "signatureRequired",
    "tracking"
})
public class PostalServiceServiceDetailsServiceOptions {

    @XmlElement(name = "CODFlag")
    protected Boolean codFlag;
    @XmlElement(name = "DelivConfirmation")
    protected Boolean delivConfirmation;
    @XmlElementRef(name = "HandlingClass", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> handlingClass;
    @XmlElement(name = "HoldForPickup")
    protected Boolean holdForPickup;
    @XmlElement(name = "OnDemandPickup")
    protected Boolean onDemandPickup;
    @XmlElement(name = "OnTimeDelivGuarantee")
    protected Boolean onTimeDelivGuarantee;
    @XmlElement(name = "SignatureRequired")
    protected Boolean signatureRequired;
    @XmlElement(name = "Tracking")
    protected Boolean tracking;

    /**
     * Gets the value of the codFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCODFlag() {
        return codFlag;
    }

    /**
     * Sets the value of the codFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCODFlag(Boolean value) {
        this.codFlag = value;
    }

    /**
     * Gets the value of the delivConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDelivConfirmation() {
        return delivConfirmation;
    }

    /**
     * Sets the value of the delivConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDelivConfirmation(Boolean value) {
        this.delivConfirmation = value;
    }

    /**
     * Gets the value of the handlingClass property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHandlingClass() {
        return handlingClass;
    }

    /**
     * Sets the value of the handlingClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHandlingClass(JAXBElement<String> value) {
        this.handlingClass = value;
    }

    /**
     * Gets the value of the holdForPickup property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHoldForPickup() {
        return holdForPickup;
    }

    /**
     * Sets the value of the holdForPickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHoldForPickup(Boolean value) {
        this.holdForPickup = value;
    }

    /**
     * Gets the value of the onDemandPickup property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnDemandPickup() {
        return onDemandPickup;
    }

    /**
     * Sets the value of the onDemandPickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnDemandPickup(Boolean value) {
        this.onDemandPickup = value;
    }

    /**
     * Gets the value of the onTimeDelivGuarantee property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnTimeDelivGuarantee() {
        return onTimeDelivGuarantee;
    }

    /**
     * Sets the value of the onTimeDelivGuarantee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnTimeDelivGuarantee(Boolean value) {
        this.onTimeDelivGuarantee = value;
    }

    /**
     * Gets the value of the signatureRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSignatureRequired() {
        return signatureRequired;
    }

    /**
     * Sets the value of the signatureRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSignatureRequired(Boolean value) {
        this.signatureRequired = value;
    }

    /**
     * Gets the value of the tracking property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTracking() {
        return tracking;
    }

    /**
     * Sets the value of the tracking property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTracking(Boolean value) {
        this.tracking = value;
    }

}
