package ru.calories.nikolay.service.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.calories.nikolay.MealTestData;
import ru.calories.nikolay.Profiles;
import ru.calories.nikolay.UserTestData;
import ru.calories.nikolay.model.Meal;
import ru.calories.nikolay.service.AbstractMealServiceTest;
import ru.calories.nikolay.util.exception.NotFoundException;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {
    @Test
    public void getWithUser() {
        Meal adminMeal = service.getWithUser(MealTestData.ADMIN_MEAL_ID, UserTestData.ADMIN_ID);
        MealTestData.MEAL_MATCHER.assertMatch(adminMeal, MealTestData.adminMeal1);
        UserTestData.USER_MATCHER.assertMatch(adminMeal.getUser(), UserTestData.admin);
    }

    @Test
    public void getWithUserNotFound() {
        Assert.assertThrows(NotFoundException.class,
                () -> service.getWithUser(MealTestData.NOT_FOUND, UserTestData.ADMIN_ID));
    }
}
