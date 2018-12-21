
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
    "country",
    "lat",
    "lon",
    "type"
})
public class Geo implements Serializable
{

    @JsonProperty("country")
    private String country;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("type")
    private Long type;
    private final static long serialVersionUID = -6674754737148127881L;

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    @JsonProperty("type")
    public Long getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("country", country).append("lat", lat).append("lon", lon).append("type", type).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(lon).append(type).append(lat).append(country).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Geo) == false) {
            return false;
        }
        Geo rhs = ((Geo) other);
        return new EqualsBuilder().append(lon, rhs.lon).append(type, rhs.type).append(lat, rhs.lat).append(country, rhs.country).isEquals();
    }

}
