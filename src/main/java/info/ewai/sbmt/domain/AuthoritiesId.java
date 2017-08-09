package info.ewai.sbmt.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AuthoritiesId implements Serializable {

    private static final long serialVersionUID = 1L;

    private int user_id;
    private String authority;

    public AuthoritiesId() {
    }

    public AuthoritiesId(int user_id, String authority) {
        super();
        this.user_id = user_id;
        this.authority = authority;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
