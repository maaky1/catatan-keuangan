package com.github.maaky1.catatan_keuangan.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryRs {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long categoryId;
    private String categoryName;
}
