
package com.demo.fpo.apidatafetcher.stub;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PostalService.ServiceDetails.ServicePhysicalLimits complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PostalService.ServiceDetails.ServicePhysicalLimits"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MaxHeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="MaxLenght" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="MaxWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="MaxWidth" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="MinHeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="MinLenght" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="MinWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="MinWidth" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalService.ServiceDetails.ServicePhysicalLimits", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", propOrder = {
    "maxHeight",
    "maxLenght",
    "maxWeight",
    "maxWidth",
    "minHeight",
    "minLenght",
    "minWeight",
    "minWidth"
})
public class PostalServiceServiceDetailsServicePhysicalLimits {

    @XmlElementRef(name = "MaxHeight", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> maxHeight;
    @XmlElementRef(name = "MaxLenght", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> maxLenght;
    @XmlElementRef(name = "MaxWeight", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> maxWeight;
    @XmlElementRef(name = "MaxWidth", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> maxWidth;
    @XmlElementRef(name = "MinHeight", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> minHeight;
    @XmlElementRef(name = "MinLenght", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> minLenght;
    @XmlElementRef(name = "MinWeight", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> minWeight;
    @XmlElementRef(name = "MinWidth", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> minWidth;

    /**
     * Gets the value of the maxHeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMaxHeight() {
        return maxHeight;
    }

    /**
     * Sets the value of the maxHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMaxHeight(JAXBElement<BigDecimal> value) {
        this.maxHeight = value;
    }

    /**
     * Gets the value of the maxLenght property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMaxLenght() {
        return maxLenght;
    }

    /**
     * Sets the value of the maxLenght property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMaxLenght(JAXBElement<BigDecimal> value) {
        this.maxLenght = value;
    }

    /**
     * Gets the value of the maxWeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMaxWeight() {
        return maxWeight;
    }

    /**
     * Sets the value of the maxWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMaxWeight(JAXBElement<BigDecimal> value) {
        this.maxWeight = value;
    }

    /**
     * Gets the value of the maxWidth property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMaxWidth() {
        return maxWidth;
    }

    /**
     * Sets the value of the maxWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMaxWidth(JAXBElement<BigDecimal> value) {
        this.maxWidth = value;
    }

    /**
     * Gets the value of the minHeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMinHeight() {
        return minHeight;
    }

    /**
     * Sets the value of the minHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMinHeight(JAXBElement<BigDecimal> value) {
        this.minHeight = value;
    }

    /**
     * Gets the value of the minLenght property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMinLenght() {
        return minLenght;
    }

    /**
     * Sets the value of the minLenght property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMinLenght(JAXBElement<BigDecimal> value) {
        this.minLenght = value;
    }

    /**
     * Gets the value of the minWeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMinWeight() {
        return minWeight;
    }

    /**
     * Sets the value of the minWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMinWeight(JAXBElement<BigDecimal> value) {
        this.minWeight = value;
    }

    /**
     * Gets the value of the minWidth property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMinWidth() {
        return minWidth;
    }

    /**
     * Sets the value of the minWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMinWidth(JAXBElement<BigDecimal> value) {
        this.minWidth = value;
    }

}
