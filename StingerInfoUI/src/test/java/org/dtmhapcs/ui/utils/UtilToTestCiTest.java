package org.dtmhapcs.ui.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UtilToTestCiTest {

    @Test
    public void shouldAlwaysReturn42() {
        // given
        UtilToTestCi testCi = new UtilToTestCi();
        // when
        int return42 = testCi.alwaysReturn42();
        assertThat(return42, equalTo(42));
    }

}
