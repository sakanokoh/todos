package sn.ept.git.seminaire.cicd.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import sn.ept.git.seminaire.cicd.TodoApplication;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TodoApplication.class})
@Transactional
class ServiceBaseTest {

    public static final int DEFAULT_SIZE = 10;
    public static final int DEFAULT_PAGE = 0;
    static final PageRequest PAGE = PageRequest.of(DEFAULT_PAGE, DEFAULT_SIZE);


    @Test
    void defaultPageShouldBeNull() {
        assertThat(PAGE.getPageNumber())
                .isZero()
                .isEqualTo(DEFAULT_PAGE);
    }


    @Test
    void defaultSizeShouldBePositive() {
        assertThat(PAGE.getPageSize())
                .isPositive()
                .isEqualTo(DEFAULT_SIZE);
    }

}