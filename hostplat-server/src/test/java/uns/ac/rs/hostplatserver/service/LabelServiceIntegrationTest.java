package uns.ac.rs.hostplatserver.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uns.ac.rs.hostplatserver.HostplatServerApplication;
import uns.ac.rs.hostplatserver.exception.ResourceNotExistException;

import static org.junit.Assert.*;

/*
    WARNING: This testing is executing on real database.
    TODO: Use in-memory database for create, delete and update tests.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HostplatServerApplication.class)
public class LabelServiceIntegrationTest {

    @Autowired
    public LabelService labelService;

    @Before
    public void setUp() {
    }

    @Test
    public void getAtLeastOneActiveLabelTest() {
        assertNotNull(labelService.getAllActive());
    }

    @Test
    public void getLabelByIdTest() {
        assertEquals("Test", labelService.get("c4ca4238a0b923820dcc509a6f75849b").getName());
    }

    @Test(expected = ResourceNotExistException.class)
    public void getNonExistentLabelTest() {
        labelService.get("blabla123");
    }

}
