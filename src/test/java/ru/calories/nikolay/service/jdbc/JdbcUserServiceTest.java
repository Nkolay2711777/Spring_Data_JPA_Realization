package ru.calories.nikolay.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.calories.nikolay.Profiles;
import ru.calories.nikolay.service.AbstractUserServiceTest;

@ActiveProfiles(Profiles.JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}