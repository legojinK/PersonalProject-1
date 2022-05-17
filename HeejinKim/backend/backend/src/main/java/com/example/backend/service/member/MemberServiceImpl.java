package com.example.backend.service.member;

import com.example.backend.controller.member.request.MemberRequest;
import com.example.backend.entity.Member;
import com.example.backend.entity.MemberAuth;
import com.example.backend.repository.member.MemberAuthRepository;
import com.example.backend.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberAuthRepository memberAuthRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public void register(MemberRequest memberRequest) {

        //비밀번호 암호화화
       String encodedPassword = passwordEncoder.encode(memberRequest.getPassword());
        memberRequest.setPassword(encodedPassword);

        String encodedPasswordCheck = passwordEncoder.encode(memberRequest.getPasswordCheck());
        memberRequest.setPasswordCheck(encodedPasswordCheck);


        //Member 엔티티에 memberRequest 안에 값들을 vue에서 받아온 값 넣어줌
        Member memberEntity = new Member(memberRequest.getMemberNo(),memberRequest.getUserId(),memberRequest.getPassword(), memberRequest.getPasswordCheck(), memberRequest.getEmail());
        MemberAuth authEntity = new MemberAuth( memberRequest.getAuth());

        memberEntity.addAuth(authEntity);

        memberRepository.save(memberEntity);
        memberAuthRepository.save(authEntity);

    }

    @Transactional
    @Override
    public MemberRequest login(MemberRequest memberRequest) {

        //로그인시 아이디 확인
        Optional<Member> maybeMember = memberRepository.findByUserId(memberRequest.getUserId());

        if(maybeMember.equals(Optional.empty())){
            log.info("There are no person who has this id!");
            return null;
        }
        Member loginMember = maybeMember.get();

        if (!passwordEncoder.matches(memberRequest.getPassword(), loginMember.getPassword())) {
            log.info("Entered wrong password!");
            return null;
        }
        Optional<MemberAuth> maybeMemberAuth =
                memberAuthRepository.findByMemberNo(loginMember.getMemberNo());

        if (maybeMemberAuth.equals(Optional.empty())) {
            log.info("no auth");
            return null;
        }

        MemberAuth loginMemberAuth = maybeMemberAuth.get();


        MemberRequest response = new MemberRequest( loginMember.getMemberNo(),loginMember.getUserId(),
                null,null,loginMember.getEmail(),loginMemberAuth.getAuth());

        return response;
    }

    @Transactional
    @Override
    public Boolean checkUserIdValidation(String userId) {
        Optional<Member> maybeMember = memberRepository.findByUserId(userId);

        if(maybeMember.isPresent()) {
            log.info("login(): there is no id");
            return false;
        }

        else {
            return true;
        }
    }

    @Transactional
    @Override
    public Member memberRead(Long memberNo) {
        Optional<Member> getMemberInfo = memberRepository.findById(Long.valueOf(memberNo));
        return getMemberInfo.get();
    }

    @Transactional
    @Override
    public MemberRequest memberModify(MemberRequest memberRequest) {


        Optional<Member> maybeMember =memberRepository.findByUserId(memberRequest.getUserId());
        Member member =  maybeMember.get();

        //비밀번호 변경(마이페이지에서)
        String encodedPassword = passwordEncoder.encode(memberRequest.getPassword());
        member.setPassword(encodedPassword);
        memberRepository.save(member);

        Member loginMember = maybeMember.get();

        memberRequest.setMemberNo(loginMember.getMemberNo());
        memberRequest.setUserId(loginMember.getUserId());
        loginMember.setEmail(memberRequest.getEmail());

        MemberRequest response = new MemberRequest(
                memberRequest.getMemberNo(),memberRequest.getUserId(),null,null,loginMember.getEmail(),memberRequest.getAuth());
        log.info("info response" + response);

        return response;
    }




    //비번 찾기
    @Override
    public Boolean matchIdEmail(MemberRequest memberRequest) {
        Optional<Member> maybeMember = memberRepository.findByUserId(memberRequest.getUserId());
        if (!maybeMember.isPresent()){
            return false;
        }


        Member member = maybeMember.get();

        String oldEmail = member.getEmail();
        String maybeEmail = memberRequest.getEmail();

        if (oldEmail.equals(maybeEmail)){
            return true;
        }else{
            return false;
        }
    }

    // 비번 수정 (비번 찾기로 들어왔을 때)
    @Override
    public void modifyFindPw(String userId, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        memberRepository.modifyNoLoginPw(userId, encodedPassword);
    }



    @Transactional
    @Override
    public void memberRemove(Long memberNo) {
        memberRepository.deleteById(Long.valueOf(memberNo));
    }
}
