package uns.ac.rs.hostplatserver.dto;

import javax.validation.constraints.NotNull;

public class PasswordDTO {

    @NotNull(message = "Old password is required")
    private String oldPassword;

    @NotNull(message = "New password is required")
    private String newPassword;

    public PasswordDTO() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
