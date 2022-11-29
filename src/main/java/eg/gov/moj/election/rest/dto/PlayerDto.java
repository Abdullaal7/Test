package eg.gov.moj.election.rest.dto;

import eg.gov.moj.election.rest.validation.InsertValidation;
import eg.gov.moj.election.rest.validation.UpdateValidation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = "id", callSuper = false)
public class PlayerDto {

    private Integer id;
    private Integer version;

    @NotBlank(message = "Player name is mandatory", groups = {InsertValidation.class, UpdateValidation.class})
    @Size(max = 100, message = "Player name's max length allowed is 100 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String name;

    @NotBlank(message = "Player Information is mandatory", groups = {InsertValidation.class, UpdateValidation.class})
    @Size(max = 100, message = "Player Information's max length allowed is 100 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String nickname;
}
