package eg.gov.moj.election.rest.entitymapper.common;


import eg.gov.moj.election.entity.Player;
import eg.gov.moj.election.rest.dto.PlayerDto;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface JPAEntityMapper<T extends Player, S extends PlayerDto> {

    T toEntity(S s);

    S toDto(T t);

    List<T> toEntity(List<S> dtoList);

    List<S> toDto(List<T> dtoList);

    T updateEntityFromDto(S s, @MappingTarget T t);
}
