package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Board;
import restAssured.RestAssuredBaseTest;

import java.io.IOException;

import static org.hamcrest.Matchers.notNullValue;
import static utility.TestUtil.BOARD_NAME;

public class CreateBoardStepDefinitions extends RestAssuredBaseTest {

    private RequestSpecification request;
    private Response response;
    private Board board;

    @Given("I have valid API credentials")
    public void i_have_valid_credentials() throws IOException {
         initializeResources();
         request = getBaseRequestSpecification()
                .queryParam("name", BOARD_NAME);
    }

    @When("I send a post request to create a board")
    public void i_sent_post_request(){
        response = request.post("/boards/");
    }

    @Then("response status is 200")
    public void response_status_should_be(){
        response
                .then()
                .statusCode(200);
    }

    @And("response should contain id")
    public void response_should_contain_id(){
        response
                .then()
                .body("id", notNullValue())
                .log().all();
    }

    @Then("board is deleted")
    public void delete_board(){
        board = response.body().as(Board.class);
        deleteBoard(board.getId());
    }
}
