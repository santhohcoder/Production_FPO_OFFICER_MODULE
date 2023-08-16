
package com.demo.fpo.apidatafetcher.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPostalService.ServiceDetails.ServiceStandards complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPostalService.ServiceDetails.ServiceStandards"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PostalService.ServiceDetails.ServiceStandards" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}PostalService.ServiceDetails.ServiceStandards" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPostalService.ServiceDetails.ServiceStandards", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", propOrder = {
    "postalServiceServiceDetailsServiceStandards"
})
public class ArrayOfPostalServiceServiceDetailsServiceStandards {

    @XmlElement(name = "PostalService.ServiceDetails.ServiceStandards", nillable = true)
    protected List<PostalServiceServiceDetailsServiceStandards> postalServiceServiceDetailsServiceStandards;

    /**
     * Gets the value of the postalServiceServiceDetailsServiceStandards property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postalServiceServiceDetailsServiceStandards property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostalServiceServiceDetailsServiceStandards().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PostalServiceServiceDetailsServiceStandards }
     * 
     * 
     */
    public List<PostalServiceServiceDetailsServiceStandards> getPostalServiceServiceDetailsServiceStandards() {
        if (postalServiceServiceDetailsServiceStandards == null) {
            postalServiceServiceDetailsServiceStandards = new ArrayList<PostalServiceServiceDetailsServiceStandards>();
        }
        return this.postalServiceServiceDetailsServiceStandards;
    }

}
