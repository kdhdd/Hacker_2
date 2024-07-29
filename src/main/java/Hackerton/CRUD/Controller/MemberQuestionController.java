package Hackerton.CRUD.Controller;

import Hackerton.CRUD.domain.MemberQuestion;
import Hackerton.CRUD.dto.MemberQuestionDto;
import Hackerton.CRUD.Service.MemberQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member-questions")
public class MemberQuestionController {

    @Autowired
    private MemberQuestionService memberQuestionService;

    @PostMapping
    public MemberQuestion createMemberQuestion(@RequestBody MemberQuestionDto memberQuestionDto) {
        return memberQuestionService.createMemberQuestion(memberQuestionDto);
    }

    @GetMapping
    public List<MemberQuestion> getAllMemberQuestions() {
        return memberQuestionService.getAllMemberQuestions();
    }

    @GetMapping("/{id}")
    public MemberQuestion getMemberQuestionById(@PathVariable Long id) {
        return memberQuestionService.getMemberQuestionById(id);
    }

    @PutMapping("/{id}")
    public MemberQuestion updateMemberQuestion(@PathVariable Long id, @RequestBody MemberQuestionDto memberQuestionDto) {
        return memberQuestionService.updateMemberQuestion(id, memberQuestionDto);
    }
}
