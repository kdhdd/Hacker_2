//package Hackerton.CRUD.Service;
//
//import Hackerton.CRUD.Repository.TeamRepository;
//import Hackerton.CRUD.domain.Team;
//import Hackerton.CRUD.dto.TeamDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional(readOnly = true)
//public class TeamService {
//    private final TeamRepository teamRepository;
//
//    @Autowired
//    public TeamService(TeamRepository teamRepository) {
//        this.teamRepository = teamRepository;
//    }
//    @Transactional
//    public Team registerTeam(Team team) {
//        return teamRepository.save(team);
//    }
//    @Transactional
//    public void deleteTeam(Long id) {
//        teamRepository.deleteById(id);
//    }
//    @Transactional
//    public Team updateTeam(Long id, TeamDto teamDto) {
//        Optional<Team> teamOptional = teamRepository.findById(id);
//        if (teamOptional.isPresent()) {
//            Team team = teamOptional.get();
//            // 주제 수정
//            if (teamDto.getTopic() != null) {
//                team.setTopic(teamDto.getTopic());
//            }
//            return teamRepository.save(team);
//        } else {
//            throw new RuntimeException("Team not found with id " + id);
//        }
//    }
//
//    public List<Team> getAllTeams() {
//        return teamRepository.findAll();
//    }
//    public Optional<Team> getTeamById(Long id) {
//        return teamRepository.findById(id);
//    }
//}
