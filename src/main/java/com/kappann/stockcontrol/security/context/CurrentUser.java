package com.kappann.stockcontrol.security.context;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

@Getter
public class CurrentUser extends User {
    private String uuid;

    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String uuid) {
        super(username, password, authorities);
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentUser that)) return false;
        if (!super.equals(o)) return false;
        return (Objects.equals(this.getUuid(), that.getUuid()) && Objects.equals(this.getUsername(), that.getUsername()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUuid());
    }
}
