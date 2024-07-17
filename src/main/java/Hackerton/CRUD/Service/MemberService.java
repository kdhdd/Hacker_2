package Hackerton.CRUD.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Hackerton.CRUD.Repository.MemberRepository;
import Hackerton.CRUD.domain.Member;
import Hackerton.CRUD.dto.MemberDto;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Transactional
    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
    @Transactional
    public Member updateMember(Long id, MemberDto memberDto) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            // 이름 수정
            if (memberDto.getUser_Name() != null) {
                member.setUser_Name(memberDto.getUser_Name());
            }
            // 비밀번호 수정
            if (memberDto.getUser_Psw() != null) {
                member.setUser_Psw(memberDto.getUser_Psw());
            }
            // 이메일 수정
            if (memberDto.getUser_EMail() != null) {
                member.setUser_EMail(memberDto.getUser_EMail());
            }
            // 닉네임 수정
            if (memberDto.getUser_Nickname() != null) {
                member.setUser_Nickname(memberDto.getUser_Nickname());
            }
            return memberRepository.save(member);
        } else {
            throw new RuntimeException("Member not found with id " + id);
        }
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

}