package com.example.rentit.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * Role class for authorization
 *
 * @author Shimi Sadaka
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public String getName() {
        return name.name();
    }
}
