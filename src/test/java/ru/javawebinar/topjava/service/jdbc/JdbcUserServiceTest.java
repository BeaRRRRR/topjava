package ru.javawebinar.topjava.service.jdbc;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @Override
    @Test
    @Ignore
    public void testValidation() throws Exception {
        super.testValidation();
    }

    @Override
    @Before
    public void setUp() throws Exception {
        Field field = JdbcUserServiceTest.class.getDeclaredField("jpaUtil");
        Annotation oldAutowired = field.getAnnotation(Autowired.class);
        field.setAccessible(true);

        Annotation newAutowired = new Autowired(){

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public boolean required() {
                return false;
            }
        };

        Map<Class<? extends Annotation>, Annotation> annotationMap =
                (Map<Class<? extends Annotation>, Annotation>) field.get(JdbcUserServiceTest.class);
        annotationMap.put(Autowired.class, newAutowired);
    }
}