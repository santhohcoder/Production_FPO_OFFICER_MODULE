
package com.demo.fpo.apidatafetcher.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPostalService.ServiceDetails.ServiceRate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPostalService.ServiceDetails.ServiceRate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PostalService.ServiceDetails.ServiceRate" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}PostalService.ServiceDetails.ServiceRate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPostalService.ServiceDetails.ServiceRate", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", propOrder = {
    "postalServiceServiceDetailsServiceRate"
})
public class ArrayOfPostalServiceServiceDetailsServiceRate {

    @XmlElement(name = "PostalService.ServiceDetails.ServiceRate", nillable = true)
    protected List<PostalServiceServiceDetailsServiceRate> postalServiceServiceDetailsServiceRate;

    /**
     * Gets the value of the postalServiceServiceDetailsServiceRate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postalServiceServiceDetailsServiceRate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostalServiceServiceDetailsServiceRate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PostalServiceServiceDetailsServiceRate }
     * 
     * 
     */
    public List<PostalServiceServiceDetailsServiceRate> getPostalServiceServiceDetailsServiceRate() {
        if (postalServiceServiceDetailsServiceRate == null) {
            postalServiceServiceDetailsServiceRate = new ArrayList<PostalServiceServiceDetailsServiceRate>();
        }
        return this.postalServiceServiceDetailsServiceRate;
    }

}
