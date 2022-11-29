package eg.gov.moj.election.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Player {
    
    // Players Attripute 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;

    @Column(name = "name")
    private String name;

    @Column(name = "device_info")
    private String nickname;

}
