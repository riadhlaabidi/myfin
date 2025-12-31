package tn.riadh.myfin.domain.user;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import tn.riadh.myfin.domain.common.AbstractEntity;

public class User extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String langKey;
    private String imageUrl;
    private boolean isActivated = false;
    private String activationKey;
    private String resetKey;
    private Instant resetDate = null;
    private Set<Authority> authorities = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{"
                + "firstName=" + firstName
                + ", lastName=" + lastName
                + ", email=" + email
                + ", langKey=" + langKey
                + ", imageUrl=" + imageUrl
                + ", isActivated=" + isActivated
                + ", activationKey=" + activationKey
                + "}";
    }
}
