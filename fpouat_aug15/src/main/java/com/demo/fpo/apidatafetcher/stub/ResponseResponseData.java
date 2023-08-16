
package com.demo.fpo.apidatafetcher.stub;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Response.ResponseData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Response.ResponseData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ClearanceDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="DecisionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DecisionReasonCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DecisionReasonNm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Dutiable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ExtraFieldNames" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/&gt;
 *         &lt;element name="ExtraFieldValues" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/&gt;
 *         &lt;element name="Taxes" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}ArrayOfResponse.ResponseData.Tax" minOccurs="0"/&gt;
 *         &lt;element name="TotalFee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="TotalFeeCurrencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response.ResponseData", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", propOrder = {
    "clearanceDt",
    "decisionCd",
    "decisionReasonCd",
    "decisionReasonNm",
    "dutiable",
    "extraFieldNames",
    "extraFieldValues",
    "taxes",
    "totalFee",
    "totalFeeCurrencyCd"
})
public class ResponseResponseData {

    @XmlElementRef(name = "ClearanceDt", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> clearanceDt;
    @XmlElementRef(name = "DecisionCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> decisionCd;
    @XmlElementRef(name = "DecisionReasonCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> decisionReasonCd;
    @XmlElementRef(name = "DecisionReasonNm", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> decisionReasonNm;
    @XmlElement(name = "Dutiable")
    protected Boolean dutiable;
    @XmlElementRef(name = "ExtraFieldNames", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> extraFieldNames;
    @XmlElementRef(name = "ExtraFieldValues", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> extraFieldValues;
    @XmlElementRef(name = "Taxes", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfResponseResponseDataTax> taxes;
    @XmlElementRef(name = "TotalFee", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> totalFee;
    @XmlElementRef(name = "TotalFeeCurrencyCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> totalFeeCurrencyCd;

    /**
     * Gets the value of the clearanceDt property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getClearanceDt() {
        return clearanceDt;
    }

    /**
     * Sets the value of the clearanceDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setClearanceDt(JAXBElement<XMLGregorianCalendar> value) {
        this.clearanceDt = value;
    }

    /**
     * Gets the value of the decisionCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDecisionCd() {
        return decisionCd;
    }

    /**
     * Sets the value of the decisionCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDecisionCd(JAXBElement<String> value) {
        this.decisionCd = value;
    }

    /**
     * Gets the value of the decisionReasonCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDecisionReasonCd() {
        return decisionReasonCd;
    }

    /**
     * Sets the value of the decisionReasonCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDecisionReasonCd(JAXBElement<String> value) {
        this.decisionReasonCd = value;
    }

    /**
     * Gets the value of the decisionReasonNm property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDecisionReasonNm() {
        return decisionReasonNm;
    }

    /**
     * Sets the value of the decisionReasonNm property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDecisionReasonNm(JAXBElement<String> value) {
        this.decisionReasonNm = value;
    }

    /**
     * Gets the value of the dutiable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDutiable() {
        return dutiable;
    }

    /**
     * Sets the value of the dutiable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDutiable(Boolean value) {
        this.dutiable = value;
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
     * Gets the value of the taxes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfResponseResponseDataTax }{@code >}
     *     
     */
    public JAXBElement<ArrayOfResponseResponseDataTax> getTaxes() {
        return taxes;
    }

    /**
     * Sets the value of the taxes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfResponseResponseDataTax }{@code >}
     *     
     */
    public void setTaxes(JAXBElement<ArrayOfResponseResponseDataTax> value) {
        this.taxes = value;
    }

    /**
     * Gets the value of the totalFee property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getTotalFee() {
        return totalFee;
    }

    /**
     * Sets the value of the totalFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setTotalFee(JAXBElement<BigDecimal> value) {
        this.totalFee = value;
    }

    /**
     * Gets the value of the totalFeeCurrencyCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTotalFeeCurrencyCd() {
        return totalFeeCurrencyCd;
    }

    /**
     * Sets the value of the totalFeeCurrencyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTotalFeeCurrencyCd(JAXBElement<String> value) {
        this.totalFeeCurrencyCd = value;
    }

}
