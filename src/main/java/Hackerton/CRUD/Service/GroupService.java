package Hackerton.CRUD.Service;

import Hackerton.CRUD.Repository.GroupRepository;
import Hackerton.CRUD.domain.Group;
import Hackerton.CRUD.dto.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    @Transactional
    public Group registerGroup(Group group) {
        return groupRepository.save(group);
    }
    @Transactional
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
    @Transactional
    public Group updateGroup(Long id, GroupDto groupDto) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            Group group = groupOptional.get();
            // 주제 수정
            if (groupDto.getTopic() != null) {
                group.setTopic(groupDto.getTopic());
            }
            return groupRepository.save(group);
        } else {
            throw new RuntimeException("Group not found with id " + id);
        }
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }
}
