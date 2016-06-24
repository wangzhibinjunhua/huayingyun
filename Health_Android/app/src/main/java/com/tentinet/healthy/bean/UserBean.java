package com.tentinet.healthy.bean;

import com.tentinet.healthy.util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户数据模型.
 *
 * @author paladin.
 */
public class UserBean extends BaseBean {

    /**
     * 用户id.
     */
    private String id;
    /**
     * 用户账户.
     */
    private String account;
    /**
     * 用户头像地址.
     */
    private String portrait;
    /**
     * 用户昵称.
     */
    private String name;
    /**
     * 用户手机号码.
     */
    private String phone;
    /**
     * 用户性别.
     */
    private int sex=2;
    /**
     * 用户登录token.
     */
    private String token;
    /**
     * 是否登录.
     */
    private boolean isLogin = false;

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getSex() {
        return sex;
    }

    public String getToken() {
        return token;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 登录.
     */
    public void login() {
        isLogin = true;
    }

    /**
     * 登出.
     */
    public void logout() {
        isLogin = false;
        setId("");
        setAccount("");
        setPortrait("");
        setName("");
        setPhone("");
        setSex(0);
        setToken("");
        setPortrait("");
    }

    public boolean isLogin() {
        return isLogin;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", portrait='" + portrait + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", token='" + token + '\'' +
                ", isLogin=" + isLogin +
                '}';
    }

    @Override
    protected void setParams(JSONObject json) {
        try {
//            setId(json.getString("id"));
            setAccount(json.getString("username"));
            setName(json.getString("name"));
            setSex(json.getInt("sex"));
            setPhone(json.getString("mobile"));
            setToken(json.getString("token"));
            setPortrait(json.getString("avatar"));
            setNickname(json.getString("nickname"));
            setBirthday(json.getString("birthday"));
            setCertificateNumber(json.getString("certificateNumber"));
            setNation(json.getString("nation"));
            setEducation(Integer.parseInt(StringUtil.isEmpty(json.getString("education"))?"1":json.getString("education")));
            setMarry(Integer.parseInt(StringUtil.isEmpty(json.getString("marry"))?"1":json.getString("marry")));
            setProfession(json.getString("profession"));
            setCertificateType(Integer.parseInt(StringUtil.isEmpty(json.getString("certificateType"))?"1":json.getString("certificateType")));
            setSsNumber(json.getString("ssNumber"));
            setAddress(json.getString("address"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getMarry() {
        return marry;
    }

    public void setMarry(int marry) {
        this.marry = marry;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(int certificateType) {
        this.certificateType = certificateType;
    }

    public String getSsNumber() {
        return ssNumber;
    }

    public void setSsNumber(String ssNumber) {
        this.ssNumber = ssNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String nickname;
    private String username;
    private String birthday;
    private String certificateNumber;
    private String nation;
    private int education;
    private  int marry;
    private String profession;
    private int certificateType;
    private  String ssNumber;
    private String address;


}
