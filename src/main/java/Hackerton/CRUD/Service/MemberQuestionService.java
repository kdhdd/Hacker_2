package Hackerton.CRUD.Service;

import Hackerton.CRUD.domain.Member;
import Hackerton.CRUD.domain.MemberQuestion;
import Hackerton.CRUD.domain.Question;
import Hackerton.CRUD.dto.MemberQuestionDto;
import Hackerton.CRUD.Repository.MemberQuestionRepository;
import Hackerton.CRUD.Repository.MemberRepository;
import Hackerton.CRUD.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberQuestionService {

    private final MemberQuestionRepository memberQuestionRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    public MemberQuestion createMemberQuestion(MemberQuestionDto memberQuestionDto) {
        MemberQuestion memberQuestion = new MemberQuestion();
        Member member = memberRepository.findById(memberQuestionDto.getMemberId()).orElse(null);
        Question question = questionRepository.findById(memberQuestionDto.getQuestionId()).orElse(null);
        if (member == null || question == null) {
            throw new RuntimeException("회원 또는 질문을 찾을 수 없습니다");
        }
        memberQuestion.setMember(member);
        memberQuestion.setQuestion(question);
        memberQuestion.setSelectedAt(memberQuestionDto.getSelectedAt());
        return memberQuestionRepository.save(memberQuestion);
    }

    public List<MemberQuestion> getAllMemberQuestions() {
        return memberQuestionRepository.findAll();
    }

    public MemberQuestion getMemberQuestionById(Long id) {
        return memberQuestionRepository.findById(id).orElse(null);
    }

    public MemberQuestion updateMemberQuestion(Long id, MemberQuestionDto memberQuestionDto) {
        MemberQuestion memberQuestion = getMemberQuestionById(id);
        if (memberQuestion != null) {
            Member member = memberRepository.findById(memberQuestionDto.getMemberId()).orElse(null);
            Question question = questionRepository.findById(memberQuestionDto.getQuestionId()).orElse(null);
            if (member == null || question == null) {
                throw new RuntimeException("회원 또는 질문을 찾을 수 없습니다");
            }
            memberQuestion.setMember(member);
            memberQuestion.setQuestion(question);
            memberQuestion.setSelectedAt(memberQuestionDto.getSelectedAt());
            return memberQuestionRepository.save(memberQuestion);
        }
        return null;
    }

}