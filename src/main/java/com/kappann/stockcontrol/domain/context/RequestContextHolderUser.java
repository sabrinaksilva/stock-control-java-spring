package com.kappann.stockcontrol.domain.context;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Objects;

@Getter
public class RequestContextHolderUser extends User {
    private final String uuid;

    public RequestContextHolderUser (String username, String password, Collection<? extends GrantedAuthority> authorities, String uuid) {
        super(username, password, authorities);
        this.uuid = uuid;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestContextHolderUser that)) return false;
        if (!super.equals(o)) return false;
        return (Objects.equals(this.getUuid(), that.getUuid()) && Objects.equals(this.getUsername(), that.getUsername()));
    }

    @Override
    public int hashCode () {
        return Objects.hash(super.hashCode(), getUuid());
    }
}
