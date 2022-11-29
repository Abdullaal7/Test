package eg.gov.moj.election.service;

import eg.gov.moj.election.entity.Player;
import eg.gov.moj.election.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerService {
    private PlayerRepository playerRepository;

    public Page<Player> getAll(Integer page, Integer size) {
        return playerRepository.findAll(PageRequest.of(page, size));
    }

}