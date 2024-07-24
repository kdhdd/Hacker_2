package Hackerton.CRUD.Controller;

import Hackerton.CRUD.Service.TeamService;
import Hackerton.CRUD.domain.Team;
import Hackerton.CRUD.dto.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamDto> registerTeam(@RequestBody TeamDto teamDto) {
        Team team = new Team();
        team.setTopic(teamDto.getTopic());
        Team registeredTeam = teamService.registerTeam(team);
        return ResponseEntity.ok(TeamDto.from(registeredTeam));
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        List<TeamDto> teamDtos = new ArrayList<>();
        for (Team team : teams) {
            TeamDto dto = TeamDto.from(team);
            teamDtos.add(dto);
        }
        return ResponseEntity.ok(teamDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable(name="id") Long id) {
        Optional<Team> teamOptional = teamService.getTeamById(id);
        if (teamOptional.isPresent()) {
            TeamDto dto = TeamDto.from(teamOptional.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeamDto> UpdateTeam(@PathVariable Long id, @RequestBody TeamDto teamDto) {
        try {
            Team updatedTeam = teamService.updateTeam(id, teamDto);
            return ResponseEntity.ok(TeamDto.from(updatedTeam));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
