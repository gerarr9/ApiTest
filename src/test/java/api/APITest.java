package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.*;

import java.time.Clock;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;

public class APITest {
    String URL = "https://reqres.in/";

    @Test
    public void firstTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL + "api/users?page=2"), Specifications.statusCode(200));
        List<User> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get()
                .then()
                .log().body()
                .extract().body().jsonPath().getList("data", User.class);

        List<String> names = users.stream().map(x -> x.getFirst_name() + " " + x.getLast_name()).toList();
        List<String> avatars = users.stream().map(User::getAvatar).toList();
        List<String> ids = users.stream().map(x -> x.getId().toString()).toList();
        List<String> nm1 = users.stream().map(User::getFirst_name).toList();
        System.out.println(names);
        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        int a = 0;
    }

    @Test
    public void successReg() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(200));
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessRegister register = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .log().body()
                .extract().as(SuccessRegister.class);
        Assertions.assertEquals(id, register.getId());
    }

    @Test
    public void usSuccessReg() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(400));
        Register user = new Register("sydney@fife", "");
        UnSuccessRegister unSuccessRegister = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .log().body()
                .extract().as(UnSuccessRegister.class);
        Assertions.assertEquals("Missing password", unSuccessRegister.getError());
    }

    @Test
    public void sortedYearsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(200));
        List<ColorData> user = given()
                .when()
                .get("api/unknown")
                .then()
                .log().body()
                .extract().body().jsonPath().getList("data", ColorData.class);
        List<Integer> years = user.stream().map(ColorData::getYear).toList();
        List<Integer> sortedYears = user.stream().map(ColorData::getYear).sorted().toList();
        Assertions.assertEquals(years, sortedYears);
        System.out.println(years);
        System.out.println(sortedYears);
    }

    @Test
    public void checkTime() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(200));
        UserTime userTime = new UserTime("morpheus", "zion resident");
        UserTimeResponse userTimeResponse = given()
                .body(userTime)
                .when()
                .put("api/users/2")
                .then()
                .log().body()
                .extract().as(UserTimeResponse.class);
        String regex = "(.{5})$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        Assertions.assertEquals(currentTime, userTimeResponse.getUpdateAt());
    }

    @Test
    public void checkUser() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(200));
        SungleUser user = given()
                .when()
                .get("api/users/2")
                .then()
                .log().all()
                .extract().as(SungleUser.class);
        Assertions.assertEquals("Janet", user.getUser().getFirst_name());
        int a = -0;
    }

    @Test
    public void userNotFound() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(404));
        given()
                .when()
                .get("api/users/23")
                .then()
                .log().body();
    }

    @Test
    public void getAllUser() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(200));
        UserDataColor userData = given()
                .when()
                .get("api/unknown")
                .then()
                .log().body()
                .extract().as(UserDataColor.class);
        List<Integer> years = userData.getData().stream().map(ColorData::getYear).toList();
        List<Integer> yearsSorted = userData.getData().stream().map(ColorData::getYear).sorted(Collections.reverseOrder()).toList();
        Assertions.assertEquals(years, yearsSorted);
    }

    @Test
    public void getSingleUser() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(200));
        ColorRoot colorRoot = given()
                .when()
                .get("api/unknown/2")
                .then()
                .log().body()
                .extract().as(ColorRoot.class);
        int id = colorRoot.getData().getId();
        String name = colorRoot.getData().getName();
        int year = colorRoot.getData().getYear();
        String color = colorRoot.getData().getColor();
        String pantone_value = colorRoot.getData().getPantone_value();
        Assertions.assertEquals(id, 2);
        Assertions.assertEquals(name, "fuchsia rose");
        Assertions.assertEquals(year, 2001);
        Assertions.assertEquals(color, "#C74375");
        Assertions.assertEquals(pantone_value, "17-2031");
    }

    @Test
    public void getSingleUserNotFound() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(404));
        given()
                .when()
                .get("api/unknown/23")
                .then()
                .log().all();
    }
    @Test
    public  void createUser(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(201));
        UserJob user = new UserJob("Руслан","Иждивенец");
        UserJobCreate create = given()
                .when()
                .body(user)
                .post("api/users")
                .then()
                .log().body()
                .extract().as(UserJobCreate.class);
        String job = create.getJob();
        Assertions.assertEquals("Иждивенец",job);
    }

    @Test
    public void updateUser(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(204));
        UserJob user = new UserJob("morpheus","zion resident");
        UserUpdate create = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then()
                .log().body()
                .extract().as(UserUpdate.class);
            Date updateTime = create.getUpdatedAt();
        System.out.println(updateTime);
    }
    @Test
    public  void deleteUser(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(204));
        given()
                .when()
                .delete("api/users/2")
                .then()
                .log().all();
    }
    @Test
    public  void registerUser(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(200));
        Register reg = new Register("eve.holt@reqres.in","pistol");
        SuccessRegister successRegister = given()
                .body(reg)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(SuccessRegister.class);
    }
    @Test
    public  void registerUnSuccess(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(400));
        Register reg = new Register("sydney@fife","");
        UnSuccessRegister unSuccessRegister = given()
                .body(reg)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(UnSuccessRegister.class);
    }
    @Test
    public  void  loginTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(200));
        Register reg = new Register("eve.holt@reqres.in","cityslicka");
        SuccessLogin login = given()
                .body(reg)
                .when()
                .post("api/login")
                .then()
                .log().body()
                .extract().as(SuccessLogin.class);
        String token = login.getToken();
        Assertions.assertEquals("QpwL5tke4Pnpja7X4",token);
    }
    @Test
    public  void  loginUnSuccessTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.statusCode(400));
        Register reg = new Register("peter@klaven","");
        UnSuccessRegister unSuccessRegister = given()
                .body(reg)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(UnSuccessRegister.class);
        String error = unSuccessRegister.getError();
        Assertions.assertEquals("Missing password",error);
    }
}
