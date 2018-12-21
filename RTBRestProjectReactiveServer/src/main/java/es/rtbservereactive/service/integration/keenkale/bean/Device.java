
package es.rtbservereactive.service.integration.keenkale.bean;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ua",
    "geo",
    "dnt",
    "lmt",
    "ip",
    "devicetype",
    "make",
    "model",
    "os",
    "osv",
    "ifa",
    "connectiontype",
    "js",
    "language"
})
public class Device implements Serializable
{

    @JsonProperty("ua")
    private String ua;
    @JsonProperty("geo")
    private Geo geo;
    @JsonProperty("dnt")
    private Long dnt;
    @JsonProperty("lmt")
    private Long lmt;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("devicetype")
    private Long devicetype;
    @JsonProperty("make")
    private String make;
    @JsonProperty("model")
    private String model;
    @JsonProperty("os")
    private String os;
    @JsonProperty("osv")
    private String osv;
    @JsonProperty("ifa")
    private String ifa;
    @JsonProperty("connectiontype")
    private Long connectiontype;
    @JsonProperty("js")
    private Long js;
    @JsonProperty("language")
    private String language;
    private final static long serialVersionUID = -740134724671260426L;

    @JsonProperty("ua")
    public String getUa() {
        return ua;
    }

    @JsonProperty("ua")
    public void setUa(String ua) {
        this.ua = ua;
    }

    @JsonProperty("geo")
    public Geo getGeo() {
        return geo;
    }

    @JsonProperty("geo")
    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @JsonProperty("dnt")
    public Long getDnt() {
        return dnt;
    }

    @JsonProperty("dnt")
    public void setDnt(Long dnt) {
        this.dnt = dnt;
    }

    @JsonProperty("lmt")
    public Long getLmt() {
        return lmt;
    }

    @JsonProperty("lmt")
    public void setLmt(Long lmt) {
        this.lmt = lmt;
    }

    @JsonProperty("ip")
    public String getIp() {
        return ip;
    }

    @JsonProperty("ip")
    public void setIp(String ip) {
        this.ip = ip;
    }

    @JsonProperty("devicetype")
    public Long getDevicetype() {
        return devicetype;
    }

    @JsonProperty("devicetype")
    public void setDevicetype(Long devicetype) {
        this.devicetype = devicetype;
    }

    @JsonProperty("make")
    public String getMake() {
        return make;
    }

    @JsonProperty("make")
    public void setMake(String make) {
        this.make = make;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("os")
    public String getOs() {
        return os;
    }

    @JsonProperty("os")
    public void setOs(String os) {
        this.os = os;
    }

    @JsonProperty("osv")
    public String getOsv() {
        return osv;
    }

    @JsonProperty("osv")
    public void setOsv(String osv) {
        this.osv = osv;
    }

    @JsonProperty("ifa")
    public String getIfa() {
        return ifa;
    }

    @JsonProperty("ifa")
    public void setIfa(String ifa) {
        this.ifa = ifa;
    }

    @JsonProperty("connectiontype")
    public Long getConnectiontype() {
        return connectiontype;
    }

    @JsonProperty("connectiontype")
    public void setConnectiontype(Long connectiontype) {
        this.connectiontype = connectiontype;
    }

    @JsonProperty("js")
    public Long getJs() {
        return js;
    }

    @JsonProperty("js")
    public void setJs(Long js) {
        this.js = js;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("ua", ua).append("geo", geo).append("dnt", dnt).append("lmt", lmt).append("ip", ip).append("devicetype", devicetype).append("make", make).append("model", model).append("os", os).append("osv", osv).append("ifa", ifa).append("connectiontype", connectiontype).append("js", js).append("language", language).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(os).append(model).append(geo).append(osv).append(js).append(dnt).append(ip).append(connectiontype).append(ua).append(devicetype).append(language).append(make).append(ifa).append(lmt).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Device) == false) {
            return false;
        }
        Device rhs = ((Device) other);
        return new EqualsBuilder().append(os, rhs.os).append(model, rhs.model).append(geo, rhs.geo).append(osv, rhs.osv).append(js, rhs.js).append(dnt, rhs.dnt).append(ip, rhs.ip).append(connectiontype, rhs.connectiontype).append(ua, rhs.ua).append(devicetype, rhs.devicetype).append(language, rhs.language).append(make, rhs.make).append(ifa, rhs.ifa).append(lmt, rhs.lmt).isEquals();
    }

}
