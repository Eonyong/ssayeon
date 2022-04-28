package a204.ssayeon.config.auth;

import a204.ssayeon.db.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@ToString
public class PrincipalDetails implements UserDetails {

    private static final String ROLE_USER = "ROLE_USER";

    private User user;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    public PrincipalDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }
    public static PrincipalDetails create(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(ROLE_USER));

        return new PrincipalDetails(user, authorities);
    }

    public static PrincipalDetails create(User user, Map<String, Object> attributes) {
        PrincipalDetails userPrincipal = PrincipalDetails.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public Long getId(){
        return user.getId();
    }

    public String getEmail(){
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
