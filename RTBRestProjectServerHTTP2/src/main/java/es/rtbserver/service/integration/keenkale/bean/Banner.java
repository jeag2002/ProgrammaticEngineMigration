
package es.rtbserver.service.integration.keenkale.bean;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "w",
    "h",
    "mimes",
    "battr",
    "wmax",
    "hmax",
    "wmin",
    "hmin",
    "api"
})
public class Banner implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("w")
    private Long w;
    @JsonProperty("h")
    private Long h;
    @JsonProperty("mimes")
    private List<String> mimes = null;
    @JsonProperty("battr")
    private List<Object> battr = null;
    @JsonProperty("wmax")
    private Long wmax;
    @JsonProperty("hmax")
    private Long hmax;
    @JsonProperty("wmin")
    private Long wmin;
    @JsonProperty("hmin")
    private Long hmin;
    @JsonProperty("api")
    private List<Long> api = null;
    private final static long serialVersionUID = -1670781510473179240L;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("w")
    public Long getW() {
        return w;
    }

    @JsonProperty("w")
    public void setW(Long w) {
        this.w = w;
    }

    @JsonProperty("h")
    public Long getH() {
        return h;
    }

    @JsonProperty("h")
    public void setH(Long h) {
        this.h = h;
    }

    @JsonProperty("mimes")
    public List<String> getMimes() {
        return mimes;
    }

    @JsonProperty("mimes")
    public void setMimes(List<String> mimes) {
        this.mimes = mimes;
    }

    @JsonProperty("battr")
    public List<Object> getBattr() {
        return battr;
    }

    @JsonProperty("battr")
    public void setBattr(List<Object> battr) {
        this.battr = battr;
    }

    @JsonProperty("wmax")
    public Long getWmax() {
        return wmax;
    }

    @JsonProperty("wmax")
    public void setWmax(Long wmax) {
        this.wmax = wmax;
    }

    @JsonProperty("hmax")
    public Long getHmax() {
        return hmax;
    }

    @JsonProperty("hmax")
    public void setHmax(Long hmax) {
        this.hmax = hmax;
    }

    @JsonProperty("wmin")
    public Long getWmin() {
        return wmin;
    }

    @JsonProperty("wmin")
    public void setWmin(Long wmin) {
        this.wmin = wmin;
    }

    @JsonProperty("hmin")
    public Long getHmin() {
        return hmin;
    }

    @JsonProperty("hmin")
    public void setHmin(Long hmin) {
        this.hmin = hmin;
    }

    @JsonProperty("api")
    public List<Long> getApi() {
        return api;
    }

    @JsonProperty("api")
    public void setApi(List<Long> api) {
        this.api = api;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("w", w).append("h", h).append("mimes", mimes).append("battr", battr).append("wmax", wmax).append("hmax", hmax).append("wmin", wmin).append("hmin", hmin).append("api", api).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(w).append(id).append(battr).append(mimes).append(hmin).append(wmax).append(api).append(wmin).append(h).append(hmax).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Banner) == false) {
            return false;
        }
        Banner rhs = ((Banner) other);
        return new EqualsBuilder().append(w, rhs.w).append(id, rhs.id).append(battr, rhs.battr).append(mimes, rhs.mimes).append(hmin, rhs.hmin).append(wmax, rhs.wmax).append(api, rhs.api).append(wmin, rhs.wmin).append(h, rhs.h).append(hmax, rhs.hmax).isEquals();
    }

}
