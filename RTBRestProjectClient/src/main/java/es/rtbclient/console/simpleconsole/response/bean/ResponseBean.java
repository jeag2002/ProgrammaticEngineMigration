
package es.rtbclient.console.simpleconsole.response.bean;

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
    "imp",
    "device",
    "test",
    "at",
    "tmax",
    "badv",
    "app",
    "user"
})
public class ResponseBean implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("imp")
    private List<Imp> imp = null;
    @JsonProperty("device")
    private Device device;
    @JsonProperty("test")
    private Long test;
    @JsonProperty("at")
    private Long at;
    @JsonProperty("tmax")
    private Long tmax;
    @JsonProperty("badv")
    private List<Object> badv = null;
    @JsonProperty("app")
    private App app;
    @JsonProperty("user")
    private User user;
    private final static long serialVersionUID = -3139388880776886673L;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("imp")
    public List<Imp> getImp() {
        return imp;
    }

    @JsonProperty("imp")
    public void setImp(List<Imp> imp) {
        this.imp = imp;
    }

    @JsonProperty("device")
    public Device getDevice() {
        return device;
    }

    @JsonProperty("device")
    public void setDevice(Device device) {
        this.device = device;
    }

    @JsonProperty("test")
    public Long getTest() {
        return test;
    }

    @JsonProperty("test")
    public void setTest(Long test) {
        this.test = test;
    }

    @JsonProperty("at")
    public Long getAt() {
        return at;
    }

    @JsonProperty("at")
    public void setAt(Long at) {
        this.at = at;
    }

    @JsonProperty("tmax")
    public Long getTmax() {
        return tmax;
    }

    @JsonProperty("tmax")
    public void setTmax(Long tmax) {
        this.tmax = tmax;
    }

    @JsonProperty("badv")
    public List<Object> getBadv() {
        return badv;
    }

    @JsonProperty("badv")
    public void setBadv(List<Object> badv) {
        this.badv = badv;
    }

    @JsonProperty("app")
    public App getApp() {
        return app;
    }

    @JsonProperty("app")
    public void setApp(App app) {
        this.app = app;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("imp", imp).append("device", device).append("test", test).append("at", at).append("tmax", tmax).append("badv", badv).append("app", app).append("user", user).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(app).append(tmax).append(badv).append(test).append(imp).append(at).append(device).append(user).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ResponseBean) == false) {
            return false;
        }
        ResponseBean rhs = ((ResponseBean) other);
        return new EqualsBuilder().append(id, rhs.id).append(app, rhs.app).append(tmax, rhs.tmax).append(badv, rhs.badv).append(test, rhs.test).append(imp, rhs.imp).append(at, rhs.at).append(device, rhs.device).append(user, rhs.user).isEquals();
    }

}
