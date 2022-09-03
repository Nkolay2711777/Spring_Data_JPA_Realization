package ru.calories.nikolay.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.calories.nikolay.Profiles;
import ru.calories.nikolay.service.AbstractMealServiceTest;

@ActiveProfiles(Profiles.JPA)
public class JpaMealServiceTest extends AbstractMealServiceTest {
}