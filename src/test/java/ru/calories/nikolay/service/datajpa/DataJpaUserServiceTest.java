package ru.calories.nikolay.service.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.calories.nikolay.MealTestData;
import ru.calories.nikolay.Profiles;
import ru.calories.nikolay.UserTestData;
import ru.calories.nikolay.model.User;
import ru.calories.nikolay.service.AbstractUserServiceTest;
import ru.calories.nikolay.util.exception.NotFoundException;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void getWithMeals() {
        User user = service.getWithMeals(UserTestData.USER_ID);
        UserTestData.USER_MATCHER.assertMatch(user, UserTestData.user);
        MealTestData.MEAL_MATCHER.assertMatch(user.getMeals(), MealTestData.meals);
    }

    @Test
    public void getWithMealsNotFound() {
        Assert.assertThrows(NotFoundException.class,
                () -> service.getWithMeals(UserTestData.NOT_FOUND));
    }
}