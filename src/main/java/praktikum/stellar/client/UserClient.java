package praktikum.stellar.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.stellar.model.UserLogin;
import praktikum.stellar.model.UserCreate;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {
    protected static final String USER_URI = BASE_URI + "auth/";

    @Step("Create user {userCreate}")
    public ValidatableResponse create(UserCreate userCreate) {
        return given().spec(getBaseSpec())
                .body(userCreate)
                .when()
                .post(USER_URI + "register")
                .then();
    }

    @Step("Login user {userLogin}")
    public ValidatableResponse login(UserLogin userLogin) {
        return given().spec(getBaseSpec())
                .body(userLogin)
                .when()
                .post(USER_URI + "login")
                .then();
    }

    @Step("Delete user {userDelete}")
    public ValidatableResponse delete(String authorization) {
        return given().spec(getBaseSpec())
                .header("authorization", authorization)
                .when()
                .delete(USER_URI + "user")
                .then();
    }
}
