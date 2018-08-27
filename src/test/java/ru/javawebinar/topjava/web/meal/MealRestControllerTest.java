package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.web.json.JsonUtil.writeIgnoreProps;

class MealRestControllerTest extends AbstractControllerTest {



    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(writeIgnoreProps(MealTestData.MEAL1, "registered")));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + MealTestData.ADMIN_MEAL_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        MealTestData.assertMatch(mealService.getAll(100001),MealTestData.ADMIN_MEAL2);
    }

    @Test
    void getAll() throws Exception {
        TestUtil.print(mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)));
    }

    @Test
    void create() throws Exception {
        Meal expected = new Meal(LocalDateTime.now(),"new Meal",300);
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated())
                .andDo(print());
        Meal actual = TestUtil.readFromJson(actions,Meal.class);
        MealTestData.assertMatch(actual,expected);


    }

    @Test
    void update() throws Exception {
        Meal updated = new Meal(MealTestData.MEAL1);
        updated.setCalories(400);
        updated.setDescription("Updated description");
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MealTestData.assertMatch(mealService.get(updated.getId(),100000),updated);
    }

    @Test
    void getBetween() {
    }
}