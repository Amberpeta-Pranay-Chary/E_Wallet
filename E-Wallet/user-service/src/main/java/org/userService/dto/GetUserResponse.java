package org.userService.dto;

import lombok.*;

import java.util.Date;

/**
 * This class is used as a data transfer object for getting user response.
 *
 * @author rpranay665@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserResponse {

    private String name;
    private String phone;
    private String email;
    private Integer age;
    private Date createdOn;
    private Date updatedOn;
}