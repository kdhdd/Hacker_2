package Hackerton.CRUD.dto;

import Hackerton.CRUD.domain.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDto {
    private Long id;
    private String Topic;

    public static GroupDto from(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setTopic(groupDto.getTopic());
        return groupDto;
    }
}
