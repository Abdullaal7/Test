package eg.gov.moj.election.rest.controller.admin;

import eg.gov.moj.election.rest.handler.PlayerHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@AllArgsConstructor
@Tag(name = "player", description = "REST API for player")
public class PlayerController {

    private PlayerHandler playerHandler;

    @GetMapping
    @Operation(summary = "get All players")
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return playerHandler.getAll(page, size);
    }
}
