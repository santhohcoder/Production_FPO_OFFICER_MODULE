
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CDSView complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CDSView"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/PTC.BusinessLayer.Core}Entity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DCResponse" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}Response" minOccurs="0"/&gt;
 *         &lt;element name="Declaration" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}Declaration" minOccurs="0"/&gt;
 *         &lt;element name="MailObject" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}MailObject" minOccurs="0"/&gt;
 *         &lt;element name="Response" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}Response" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CDSView", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", propOrder = {
    "dcResponse",
    "declaration",
    "mailObject",
    "response"
})
public class CDSView
    extends Entity
{

    @XmlElementRef(name = "DCResponse", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<Response> dcResponse;
    @XmlElementRef(name = "Declaration", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<Declaration> declaration;
    @XmlElementRef(name = "MailObject", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<MailObject> mailObject;
    @XmlElementRef(name = "Response", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<Response> response;

    /**
     * Gets the value of the dcResponse property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Response }{@code >}
     *     
     */
    public JAXBElement<Response> getDCResponse() {
        return dcResponse;
    }

    /**
     * Sets the value of the dcResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Response }{@code >}
     *     
     */
    public void setDCResponse(JAXBElement<Response> value) {
        this.dcResponse = value;
    }

    /**
     * Gets the value of the declaration property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     *     
     */
    public JAXBElement<Declaration> getDeclaration() {
        return declaration;
    }

    /**
     * Sets the value of the declaration property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Declaration }{@code >}
     *     
     */
    public void setDeclaration(JAXBElement<Declaration> value) {
        this.declaration = value;
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
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Response }{@code >}
     *     
     */
    public JAXBElement<Response> getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Response }{@code >}
     *     
     */
    public void setResponse(JAXBElement<Response> value) {
        this.response = value;
    }

}
