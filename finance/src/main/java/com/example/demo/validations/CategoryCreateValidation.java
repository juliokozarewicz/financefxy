package com.example.demo.validations;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CategoryCreateValidation(

        @NotEmpty(message="{not_empty}")
        @Size(min=1, message="{not_empty}")
        @Size(max=100, message="{many_characters}")
        String categoryName

) {}