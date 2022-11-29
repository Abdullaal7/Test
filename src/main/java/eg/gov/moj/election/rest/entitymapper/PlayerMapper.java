package eg.gov.moj.election.rest.entitymapper;

import eg.gov.moj.election.entity.Player;
import eg.gov.moj.election.rest.dto.PlayerDto;
import eg.gov.moj.election.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper extends JPAEntityMapper<Player, PlayerDto> {
}
