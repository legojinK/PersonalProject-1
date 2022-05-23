package com.example.backend.controller.member.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Data
@Slf4j
@NoArgsConstructor
public class MemberModifyRequest {

        private String userId;
        private String password;
        private String passwordCheck;
        private String email;
        private String auth;

        public MemberModifyRequest (String userId, String password,String email,String passwordCheck,String auth) {
            this.userId = userId;
            this.password = password;
            this.email = email;
            this.auth = auth;
            this.passwordCheck = passwordCheck;
        }



    }


