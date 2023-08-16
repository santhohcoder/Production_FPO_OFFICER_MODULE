
package com.demo.fpo.apidatafetcher.stub;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Response.ResponseData.Tax complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Response.ResponseData.Tax"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="CurrencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ExtraFieldNames" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/&gt;
 *         &lt;element name="ExtraFieldValues" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/&gt;
 *         &lt;element name="HS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="NetWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response.ResponseData.Tax", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", propOrder = {
    "amount",
    "currencyCd",
    "description",
    "extraFieldNames",
    "extraFieldValues",
    "hs",
    "netWeight",
    "number",
    "rate",
    "type"
})
public class ResponseResponseDataTax {

    @XmlElementRef(name = "Amount", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> amount;
    @XmlElementRef(name = "CurrencyCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> currencyCd;
    @XmlElementRef(name = "Description", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> description;
    @XmlElementRef(name = "ExtraFieldNames", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> extraFieldNames;
    @XmlElementRef(name = "ExtraFieldValues", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> extraFieldValues;
    @XmlElementRef(name = "HS", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> hs;
    @XmlElementRef(name = "NetWeight", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> netWeight;
    @XmlElementRef(name = "Number", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> number;
    @XmlElementRef(name = "Rate", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> rate;
    @XmlElementRef(name = "Type", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> type;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setAmount(JAXBElement<BigDecimal> value) {
        this.amount = value;
    }

    /**
     * Gets the value of the currencyCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCurrencyCd() {
        return currencyCd;
    }

    /**
     * Sets the value of the currencyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCurrencyCd(JAXBElement<String> value) {
        this.currencyCd = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescription(JAXBElement<String> value) {
        this.description = value;
    }

    /**
     * Gets the value of the extraFieldNames property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getExtraFieldNames() {
        return extraFieldNames;
    }

    /**
     * Sets the value of the extraFieldNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setExtraFieldNames(JAXBElement<ArrayOfstring> value) {
        this.extraFieldNames = value;
    }

    /**
     * Gets the value of the extraFieldValues property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getExtraFieldValues() {
        return extraFieldValues;
    }

    /**
     * Sets the value of the extraFieldValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setExtraFieldValues(JAXBElement<ArrayOfstring> value) {
        this.extraFieldValues = value;
    }

    /**
     * Gets the value of the hs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHS() {
        return hs;
    }

    /**
     * Sets the value of the hs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHS(JAXBElement<String> value) {
        this.hs = value;
    }

    /**
     * Gets the value of the netWeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getNetWeight() {
        return netWeight;
    }

    /**
     * Sets the value of the netWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setNetWeight(JAXBElement<BigDecimal> value) {
        this.netWeight = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNumber(JAXBElement<Integer> value) {
        this.number = value;
    }

    /**
     * Gets the value of the rate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setRate(JAXBElement<BigDecimal> value) {
        this.rate = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setType(JAXBElement<String> value) {
        this.type = value;
    }

}
