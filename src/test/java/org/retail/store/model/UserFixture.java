package org.retail.store.model;

import org.retail.store.model.entity.User;
import org.retail.store.model.enums.UserTypeEnum;
import org.retail.store.model.request.UserRequest;

public class UserFixture {

    public static UserRequest getUserRequest() {

        return UserRequest.builder()
                                             .phoneNumber("123456789")
                                             .firstName("firstName")
                                             .lastName("lastName")
                                             .type(UserTypeEnum.EMPLOYEE)
                                             .build();
    }

    public static UserRequest getUserRequest(UserTypeEnum userTypeEnum) {

        return UserRequest.builder()
                          .phoneNumber("123456789")
                          .firstName("firstName")
                          .lastName("lastName")
                          .type(userTypeEnum)
                          .build();
    }
    public static User getUserFixture() {

        return  User.builder()
                        .phoneNumber("123456789")
                        .firstName("firstName")
                        .lastName("lastName")
                        .type(UserTypeEnum.EMPLOYEE)
                        .build();
    }

    public static User getUserFixture(UserTypeEnum userTypeEnum) {
        return  User.builder()
                    .phoneNumber("123456789")
                    .firstName("firstName")
                    .lastName("lastName")
                    .type(userTypeEnum)
                    .build();
    }


}
