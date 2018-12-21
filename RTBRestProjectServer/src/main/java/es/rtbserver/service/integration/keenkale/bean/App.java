
package es.rtbserver.service.integration.keenkale.bean;

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
    "name",
    "publisher",
    "bundle",
    "storeurl"
})
public class App implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("publisher")
    private Publisher publisher;
    @JsonProperty("bundle")
    private String bundle;
    @JsonProperty("storeurl")
    private String storeurl;
    private final static long serialVersionUID = 5699148320220102625L;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("publisher")
    public Publisher getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("bundle")
    public String getBundle() {
        return bundle;
    }

    @JsonProperty("bundle")
    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    @JsonProperty("storeurl")
    public String getStoreurl() {
        return storeurl;
    }

    @JsonProperty("storeurl")
    public void setStoreurl(String storeurl) {
        this.storeurl = storeurl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("publisher", publisher).append("bundle", bundle).append("storeurl", storeurl).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(storeurl).append(name).append(bundle).append(publisher).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof App) == false) {
            return false;
        }
        App rhs = ((App) other);
        return new EqualsBuilder().append(id, rhs.id).append(storeurl, rhs.storeurl).append(name, rhs.name).append(bundle, rhs.bundle).append(publisher, rhs.publisher).isEquals();
    }

}
