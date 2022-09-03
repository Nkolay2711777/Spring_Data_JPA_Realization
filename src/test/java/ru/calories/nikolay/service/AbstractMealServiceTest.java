package ru.calories.nikolay.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.calories.nikolay.MealTestData;
import ru.calories.nikolay.UserTestData;
import ru.calories.nikolay.model.Meal;
import ru.calories.nikolay.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertThrows;

public abstract class AbstractMealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;

    @Test
    public void delete() {
        service.delete(MealTestData.MEAL1_ID, UserTestData.USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MealTestData.MEAL1_ID, UserTestData.USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(MealTestData.NOT_FOUND, UserTestData.USER_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(MealTestData.MEAL1_ID, UserTestData.ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(MealTestData.getNew(), UserTestData.USER_ID);
        int newId = created.id();
        Meal newMeal = MealTestData.getNew();
        newMeal.setId(newId);
        MealTestData.MEAL_MATCHER.assertMatch(created, newMeal);
        MealTestData.MEAL_MATCHER.assertMatch(service.get(newId, UserTestData.USER_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, MealTestData.meal1.getDateTime(), "duplicate", 100), UserTestData.USER_ID));
    }

    @Test
    public void get() {
        Meal actual = service.get(MealTestData.ADMIN_MEAL_ID, UserTestData.ADMIN_ID);
        MealTestData.MEAL_MATCHER.assertMatch(actual, MealTestData.adminMeal1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(MealTestData.NOT_FOUND, UserTestData.USER_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(MealTestData.MEAL1_ID, UserTestData.ADMIN_ID));
    }

    @Test
    public void update() {
        Meal updated = MealTestData.getUpdated();
        service.update(updated, UserTestData.USER_ID);
        MealTestData.MEAL_MATCHER.assertMatch(service.get(MealTestData.MEAL1_ID, UserTestData.USER_ID), MealTestData.getUpdated());
    }

    @Test
    public void updateNotOwn() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(MealTestData.getUpdated(), UserTestData.ADMIN_ID));
        Assert.assertEquals("Not found entity with id=" + MealTestData.MEAL1_ID, exception.getMessage());
        MealTestData.MEAL_MATCHER.assertMatch(service.get(MealTestData.MEAL1_ID, UserTestData.USER_ID), MealTestData.meal1);
    }

    @Test
    public void getAll() {
        MealTestData.MEAL_MATCHER.assertMatch(service.getAll(UserTestData.USER_ID), MealTestData.meals);
    }

    @Test
    public void getBetweenInclusive() {
        MealTestData.MEAL_MATCHER.assertMatch(service.getBetweenInclusive(
                        LocalDate.of(2020, Month.JANUARY, 30),
                        LocalDate.of(2020, Month.JANUARY, 30), UserTestData.USER_ID),
                MealTestData.meal3, MealTestData.meal2, MealTestData.meal1);
    }

    @Test
    public void getBetweenWithNullDates() {
        MealTestData.MEAL_MATCHER.assertMatch(service.getBetweenInclusive(null, null, UserTestData.USER_ID), MealTestData.meals);
    }
}