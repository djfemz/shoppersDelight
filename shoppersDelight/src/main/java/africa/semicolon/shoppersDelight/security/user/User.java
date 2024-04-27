package africa.semicolon.shoppersDelight.security.user;

import africa.semicolon.shoppersDelight.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@AllArgsConstructor
public class User implements UserDetails {

    private final Customer customer;

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customer.getRoles()
                       .stream()
                       .map(role -> new SimpleGrantedAuthority(role.name()))
                       .toList();
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
}
