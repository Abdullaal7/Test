package eg.gov.moj.election.service;

import eg.gov.moj.election.entity.Player;
import eg.gov.moj.election.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PlayerServiceTests {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    // Initialize the mocks
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPlayers() {
        // Arrange: Create a list of Player entities to return from the mock repository
        Player player1 = new Player();
        player1.setId(1);
        player1.setName("Player One");

        Player player2 = new Player();
        player2.setId(2);
        player2.setName("Player Two");
        List<Player> players = Arrays.asList(player1, player2);
        Page<Player> page = new PageImpl<>(players, PageRequest.of(0, 2), players.size());

        // Mock the repository's findAll method to return the page of players
        when(playerRepository.findAll(PageRequest.of(0, 2))).thenReturn(page);

        // Act: Call the service method
        Page<Player> result = playerService.getAll(0, 2);

        // Assert: Verify the result
        assertEquals(2, result.getContent().size(), "The result should contain two players.");
        assertEquals("Player One", result.getContent().get(0).getName(), "The first player should be 'Player One'.");
        assertEquals("Player Two", result.getContent().get(1).getName(), "The second player should be 'Player Two'.");
    }
}
