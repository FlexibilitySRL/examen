package ar.com.flexibility.examen.app.rest.vm;

import ar.com.flexibility.examen.app.api.UserApi;

import javax.validation.constraints.Size;

/**
 * View Model extending the UserApi, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserApi {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
            "} " + super.toString();
    }
}
