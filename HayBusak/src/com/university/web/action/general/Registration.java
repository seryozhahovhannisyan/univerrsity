package com.university.web.action.general;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.IUserManager;
import com.university.model.account.User;
import com.university.model.account.lcp.Profile;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.io.File;

public class Registration extends ShellAction {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String address;
    private String phone;
    //
    private File photo;
    private String photoFileName;
    private String photoContentType;
    //
    private String deviceId;
    //
    private IUserManager userManager;

    @SkipValidation
    public String preRegistration() {
        return SUCCESS;
    }


    public String execute() {

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhone(phone);
        user.setProfile(Profile.USER);

        try {

            userManager.add(user,photo,photoFileName,photoContentType);

            try {
                storeInSession(ConstantGeneral.SESSION_USER, user);
            } catch (Exception e) {
                storeInSession(ConstantGeneral.ERR_MESSAGE, e.getMessage());
                return ERROR;
            }

            storeInSession(ConstantGeneral.INFO, "Congratulations \t" + user.getName() +
                    "\t your email is" + user.getEmail() +
                    "\t password is" + user.getPassword() +
                    "registered");

            return Profile.USER.getName();
        } catch (InternalErrorException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, e.getMessage());
            return ERROR;
        } catch (DuplicateDataException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, e.getMessage());
            return INPUT;
        }

    }

    public void validate(){

        if(!CommonValidator.isEmailAddress(email)){
            addFieldError("email",getText("errors.invalid"));
        }

        if(CommonValidator.isEmpty(password)){
            addFieldError("password",getText("errors.invalid"));
        }

        if(CommonValidator.isEmpty(name)){
            addFieldError("name",getText("errors.invalid"));
        }

        if(CommonValidator.isEmpty(surname)){
            addFieldError("surname",getText("errors.invalid"));
        }

        if(CommonValidator.isEmpty(address)){
            addFieldError("address",getText("errors.invalid"));
        }

        if(!CommonValidator.isPositiveNumber(phone)){
            addFieldError("phone",getText("errors.invalid"));
        }

        /*if(CommonValidator.isEmpty(deviceId)){
            addFieldError("deviceId",getText("errors.invalid"));
        }*/
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    /*
    public Profile[] getProfile() {
        return Profile.values();
    }

    public void setProfile(String profile) {

        try {
            this.profile = Profile.valueOf(Integer.parseInt(profile));
        }catch (NumberFormatException e){
            storeInSession(ConstantGeneral.ERR_MESSAGE, e.getMessage());
        }

    }*/



    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setUserManager(IUserManager userManager) {
        this.userManager = userManager;
    }
}
