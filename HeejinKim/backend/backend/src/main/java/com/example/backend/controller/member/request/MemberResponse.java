package com.example.backend.controller.member.request;


import lombok.Data;

@Data
public class MemberResponse {
    private Long memberNo;
    private String auth;
    private String userId;
    private String email;


    public MemberResponse(Long memberNo, String userId,String email,String auth){
        this.memberNo = memberNo;
        this.userId = userId;
        this.email = email;
        this.auth = auth;

    }
}
