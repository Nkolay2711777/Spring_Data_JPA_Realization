package ru.calories.nikolay.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.calories.nikolay.Profiles;
import ru.calories.nikolay.service.AbstractMealServiceTest;

@ActiveProfiles(Profiles.JDBC)
public class JdbcMealServiceTest extends AbstractMealServiceTest {
}