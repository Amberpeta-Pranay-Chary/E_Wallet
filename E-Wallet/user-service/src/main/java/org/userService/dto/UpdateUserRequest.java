package org.userService.dto;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * This class is used as a data transfer object for updating user details.
 *
 * @author rpranay665@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
    @NotNull(message = "User id is mandatory.")
    private Integer id;
    @NotBlank(message = "User name is mandatory.")
    private String name;
    @NotBlank(message = "Phone number is mandatory.")
    private String phone;
    private String email;
    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

}