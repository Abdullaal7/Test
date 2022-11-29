package eg.gov.moj.election.rest.handler;

import eg.gov.moj.election.entity.Player;
import eg.gov.moj.election.rest.dto.PlayerDto;
import eg.gov.moj.election.rest.dto.common.PaginatedResultDto;
import eg.gov.moj.election.rest.entitymapper.PlayerMapper;
import eg.gov.moj.election.rest.entitymapper.common.PaginationMapper;
import eg.gov.moj.election.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PlayerHandler {

    private PlayerService playerService;
    private PlayerMapper playerMapper;
    private PaginationMapper paginationMapper;


    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Player> playerPage = playerService.getAll(page, size);
        List<PlayerDto> playerDtoList = playerMapper.toDto(playerPage.getContent());
        PaginatedResultDto<PlayerDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(playerDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(playerPage));
        return ResponseEntity.ok(paginatedResult);
    }
}
