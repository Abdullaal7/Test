package eg.gov.moj.election.repository;

import eg.gov.moj.election.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}