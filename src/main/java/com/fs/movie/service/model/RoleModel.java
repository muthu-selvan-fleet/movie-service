package com.fs.movie.service.model;

import com.fs.movie.service.parser.CSVCell;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class RoleModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @CSVCell(label = "Role Name")
    private String name;

    public ERole getERole() {
        if(this.name != null && !this.name.isBlank()) {
            ERole[] values = ERole.values();

            for(int i=0;i<values.length;i++) {
                if(values[i].name().equalsIgnoreCase(this.name)) {
                    return values[i];
                }
            }
        }
        return null;
    }
}
