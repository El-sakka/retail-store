package org.retail.store.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.retail.store.model.enums.UserTypeEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {


    @NotEmpty(message = "Phone Number cannot be empty")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String phoneNumber;

    private String firstName;

    private String lastName;

    private UserTypeEnum type = UserTypeEnum.NORMAL;

}
