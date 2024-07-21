package Hackerton.CRUD.dto;

import Hackerton.CRUD.domain.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDto {
    private Long id;
    private String topic; // 필드명의 첫 글자를 소문자로 변경


    public static GroupDto from(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setTopic(group.getTopic());
        return groupDto;
    }
}
