/*
 * Copyright (c) 2019 General Electric Company. All rights reserved.
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package red.diploma.other.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.diploma.accounts.common.Constants;
import red.diploma.accounts.model.Auditable;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private Long taxId;

    @Column(nullable = false, unique = true, length = 12)
    @Pattern(regexp = Constants.PASSPORT_PATTERN)
    private String passport;

    public Client(String fullName, Long taxId, @Pattern(regexp = Constants.PASSPORT_PATTERN) String passport) {
        this.fullName = fullName;
        this.taxId = taxId;
        this.passport = passport;
    }
}
