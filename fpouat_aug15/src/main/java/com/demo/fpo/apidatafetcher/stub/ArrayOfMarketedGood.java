
package com.demo.fpo.apidatafetcher.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMarketedGood complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMarketedGood"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MarketedGood" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}MarketedGood" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMarketedGood", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", propOrder = {
    "marketedGood"
})
public class ArrayOfMarketedGood {

    @XmlElement(name = "MarketedGood", nillable = true)
    protected List<MarketedGood> marketedGood;

    /**
     * Gets the value of the marketedGood property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketedGood property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketedGood().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MarketedGood }
     * 
     * 
     */
    public List<MarketedGood> getMarketedGood() {
        if (marketedGood == null) {
            marketedGood = new ArrayList<MarketedGood>();
        }
        return this.marketedGood;
    }

}
