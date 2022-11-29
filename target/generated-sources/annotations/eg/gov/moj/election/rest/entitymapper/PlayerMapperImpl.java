package eg.gov.moj.election.rest.entitymapper;

import eg.gov.moj.election.entity.Player;
import eg.gov.moj.election.rest.dto.PlayerDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-06T16:02:01+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public Player toEntity(PlayerDto s) {
        if ( s == null ) {
            return null;
        }

        Player player = new Player();

        player.setId( s.getId() );
        player.setVersion( s.getVersion() );
        player.setName( s.getName() );
        player.setNickname( s.getNickname() );

        return player;
    }

    @Override
    public PlayerDto toDto(Player t) {
        if ( t == null ) {
            return null;
        }

        PlayerDto playerDto = new PlayerDto();

        playerDto.setId( t.getId() );
        playerDto.setVersion( t.getVersion() );
        playerDto.setName( t.getName() );
        playerDto.setNickname( t.getNickname() );

        return playerDto;
    }

    @Override
    public List<Player> toEntity(List<PlayerDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Player> list = new ArrayList<Player>( dtoList.size() );
        for ( PlayerDto playerDto : dtoList ) {
            list.add( toEntity( playerDto ) );
        }

        return list;
    }

    @Override
    public List<PlayerDto> toDto(List<Player> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PlayerDto> list = new ArrayList<PlayerDto>( dtoList.size() );
        for ( Player player : dtoList ) {
            list.add( toDto( player ) );
        }

        return list;
    }

    @Override
    public Player updateEntityFromDto(PlayerDto s, Player t) {
        if ( s == null ) {
            return null;
        }

        t.setId( s.getId() );
        t.setVersion( s.getVersion() );
        t.setName( s.getName() );
        t.setNickname( s.getNickname() );

        return t;
    }
}
