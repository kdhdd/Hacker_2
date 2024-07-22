package Hackerton.CRUD.Controller;

import Hackerton.CRUD.Service.GroupService;
import Hackerton.CRUD.domain.Group;
import Hackerton.CRUD.dto.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<GroupDto> registerGroup(@RequestBody GroupDto groupDto) {
        Group group = new Group();
        group.setTopic(groupDto.getTopic());
        Group registeredGroup = groupService.registerGroup(group);
        return ResponseEntity.ok(GroupDto.from(registeredGroup));
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        List<GroupDto> groupDtos = new ArrayList<>();
        for (Group group : groups) {
            GroupDto dto = GroupDto.from(group);
            groupDtos.add(dto);
        }
        return ResponseEntity.ok(groupDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable(name="id") Long id) {
        Optional<Group> groupOptional = groupService.getGroupById(id);
        if (groupOptional.isPresent()) {
            GroupDto dto = GroupDto.from(groupOptional.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GroupDto> UpdateGroup(@PathVariable Long id, @RequestBody GroupDto groupDto) {
        try {
            Group updatedGroup = groupService.updateGroup(id, groupDto);
            return ResponseEntity.ok(GroupDto.from(updatedGroup));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
