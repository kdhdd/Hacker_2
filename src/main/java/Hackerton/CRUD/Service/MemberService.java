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
            if (memberDto.getMember_Name() != null) {
                member.setMember_Name(memberDto.getMember_Name());
            }
            // 이메일 수정
            if (memberDto.getMember_EMail() != null) {
                member.setMember_EMail(memberDto.getMember_EMail());
            }
            // 비밀번호 수정
            if (memberDto.getMember_Password() != null) {
                member.setMember_Password(memberDto.getMember_Password());
            }
            if (memberDto.isToday() != member.isToday()) {
                member.setToday(memberDto.isToday());
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
