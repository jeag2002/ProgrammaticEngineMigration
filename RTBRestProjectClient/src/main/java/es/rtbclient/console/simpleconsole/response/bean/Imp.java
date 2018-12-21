
package es.rtbclient.console.simpleconsole.response.bean;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "instl",
    "tagid",
    "bidfloor",
    "bidfloorcur",
    "secure",
    "banner"
})
public class Imp implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("instl")
    private Long instl;
    @JsonProperty("tagid")
    private String tagid;
    @JsonProperty("bidfloor")
    private Double bidfloor;
    @JsonProperty("bidfloorcur")
    private String bidfloorcur;
    @JsonProperty("secure")
    private Long secure;
    @JsonProperty("banner")
    private Banner banner;
    private final static long serialVersionUID = 5263183422239637679L;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("instl")
    public Long getInstl() {
        return instl;
    }

    @JsonProperty("instl")
    public void setInstl(Long instl) {
        this.instl = instl;
    }

    @JsonProperty("tagid")
    public String getTagid() {
        return tagid;
    }

    @JsonProperty("tagid")
    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    @JsonProperty("bidfloor")
    public Double getBidfloor() {
        return bidfloor;
    }

    @JsonProperty("bidfloor")
    public void setBidfloor(Double bidfloor) {
        this.bidfloor = bidfloor;
    }

    @JsonProperty("bidfloorcur")
    public String getBidfloorcur() {
        return bidfloorcur;
    }

    @JsonProperty("bidfloorcur")
    public void setBidfloorcur(String bidfloorcur) {
        this.bidfloorcur = bidfloorcur;
    }

    @JsonProperty("secure")
    public Long getSecure() {
        return secure;
    }

    @JsonProperty("secure")
    public void setSecure(Long secure) {
        this.secure = secure;
    }

    @JsonProperty("banner")
    public Banner getBanner() {
        return banner;
    }

    @JsonProperty("banner")
    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("instl", instl).append("tagid", tagid).append("bidfloor", bidfloor).append("bidfloorcur", bidfloorcur).append("secure", secure).append("banner", banner).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(bidfloor).append(tagid).append(instl).append(secure).append(bidfloorcur).append(banner).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Imp) == false) {
            return false;
        }
        Imp rhs = ((Imp) other);
        return new EqualsBuilder().append(id, rhs.id).append(bidfloor, rhs.bidfloor).append(tagid, rhs.tagid).append(instl, rhs.instl).append(secure, rhs.secure).append(bidfloorcur, rhs.bidfloorcur).append(banner, rhs.banner).isEquals();
    }

}
