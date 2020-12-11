package com.cj.data;

import lombok.Data;

@Data
public class Users {

    private String userName;
    private String password;
    private String clientCode;
    private String portalCode;

    public Users(){}

    public Users(String userName, String password, String clientCode, String portalCode){
        this.userName = userName;
        this.password = password;
        this.clientCode = clientCode;
        this.portalCode = portalCode;
    }
}
