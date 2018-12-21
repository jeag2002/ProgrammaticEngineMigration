
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
    "geo"
})
public class User implements Serializable
{

    @JsonProperty("geo")
    private Geo_ geo;
    private final static long serialVersionUID = -123729948958803379L;

    @JsonProperty("geo")
    public Geo_ getGeo() {
        return geo;
    }

    @JsonProperty("geo")
    public void setGeo(Geo_ geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("geo", geo).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(geo).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return new EqualsBuilder().append(geo, rhs.geo).isEquals();
    }

}
