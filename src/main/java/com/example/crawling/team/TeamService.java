package com.example.crawling.team;

import com.example.crawling.exception.CustomException;
import com.example.crawling.exception.ErrorCode;
import com.example.crawling.user.User;
import com.example.crawling.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final UserRepository userRepository;
    private final UserTeamMapRepository userTeamMapRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void saveTeamName(String username, List<String> selectedTeams) {

        User user;

        try {
            user = userRepository.findByUsername(username);
        } catch (CustomException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.NOT_FOUND_USER);
        }

        List<UserTeamMap> existingUserTeams = userTeamMapRepository.findByUser(user);

        userTeamMapRepository.deleteByUserId(user.getId());
        userTeamMapRepository.flush();

        for (UserTeamMap userTeamMap : existingUserTeams) {
            Team team = userTeamMap.getTeam();

            if (!userTeamMapRepository.existsByTeam(team)) {
                teamRepository.delete(team);
            }
        }

        for (String teamName : selectedTeams) {
            Team team = teamRepository.findByTeamName(teamName)
                    .orElseGet(() -> {
                        Team newTeam = new Team();
                        newTeam.setTeamName(teamName);
                        return teamRepository.save(newTeam);
                    });

            UserTeamMap userTeamMap = new UserTeamMap();
            userTeamMap.setUser(user);
            userTeamMap.setTeam(team);
            userTeamMapRepository.save(userTeamMap);
        }
    }
}
