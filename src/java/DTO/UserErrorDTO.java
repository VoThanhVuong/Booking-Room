/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author vuong
 */
public class UserErrorDTO {
    
    private String userIDError;
    private String userNameError;
    private String passwordError;
    private String confirmError;
    private String phoneError;
    private String emailError;
    private String addressError;
    private String birthdayError;
    private String numberIDCardError;
    private String numberAccountBankError;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String userIDError, String userNameError, String passwordError, String confirmError, String phoneError, String emailError, String addressError, String birthdayError, String numberIDCardError, String numberAccountBankError) {
        this.userIDError = userIDError;
        this.userNameError = userNameError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.phoneError = phoneError;
        this.emailError = emailError;
        this.addressError = addressError;
        this.birthdayError = birthdayError;
        this.numberIDCardError = numberIDCardError;
        this.numberAccountBankError = numberAccountBankError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getBirthdayError() {
        return birthdayError;
    }

    public void setBirthdayError(String birthdayError) {
        this.birthdayError = birthdayError;
    }

    public String getNumberIDCardError() {
        return numberIDCardError;
    }

    public void setNumberIDCardError(String numberIDCardError) {
        this.numberIDCardError = numberIDCardError;
    }

    public String getNumberAccountBankError() {
        return numberAccountBankError;
    }

    public void setNumberAccountBankError(String numberAccountBankError) {
        this.numberAccountBankError = numberAccountBankError;
    }
    
    
}
