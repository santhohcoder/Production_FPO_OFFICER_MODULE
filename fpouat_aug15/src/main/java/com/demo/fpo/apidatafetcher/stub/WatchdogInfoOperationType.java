
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WatchdogInfo.OperationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WatchdogInfo.OperationType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Clear"/&gt;
 *     &lt;enumeration value="MarkAsBlocked"/&gt;
 *     &lt;enumeration value="MarkWithWarning"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "WatchdogInfo.OperationType", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational")
@XmlEnum
public enum WatchdogInfoOperationType {

    @XmlEnumValue("Clear")
    CLEAR("Clear"),
    @XmlEnumValue("MarkAsBlocked")
    MARK_AS_BLOCKED("MarkAsBlocked"),
    @XmlEnumValue("MarkWithWarning")
    MARK_WITH_WARNING("MarkWithWarning");
    private final String value;

    WatchdogInfoOperationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WatchdogInfoOperationType fromValue(String v) {
        for (WatchdogInfoOperationType c: WatchdogInfoOperationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
