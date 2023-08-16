
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
 *         &lt;element name="GetDecisionNameAndCategoryFromCodeResult" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/&gt;
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
    "getDecisionNameAndCategoryFromCodeResult"
})
@XmlRootElement(name = "GetDecisionNameAndCategoryFromCodeResponse")
public class GetDecisionNameAndCategoryFromCodeResponse {

    @XmlElementRef(name = "GetDecisionNameAndCategoryFromCodeResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> getDecisionNameAndCategoryFromCodeResult;

    /**
     * Gets the value of the getDecisionNameAndCategoryFromCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getGetDecisionNameAndCategoryFromCodeResult() {
        return getDecisionNameAndCategoryFromCodeResult;
    }

    /**
     * Sets the value of the getDecisionNameAndCategoryFromCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setGetDecisionNameAndCategoryFromCodeResult(JAXBElement<ArrayOfstring> value) {
        this.getDecisionNameAndCategoryFromCodeResult = value;
    }

}
