package com.tentinet.healthy.bean;

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
    private int sex;
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
            setName(json.getString("nickname"));
            setSex(json.getInt("sex"));
            setPhone(json.getString("mobile"));
            setToken(json.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
