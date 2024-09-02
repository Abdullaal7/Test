package eg.gov.moj.election.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;

    @Column(name = "name")
    private String name;

    @Column(name = "nick_name")
    private String nickname;
}
