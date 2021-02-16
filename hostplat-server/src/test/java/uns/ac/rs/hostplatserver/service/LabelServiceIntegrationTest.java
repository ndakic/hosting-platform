package uns.ac.rs.hostplatserver.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uns.ac.rs.hostplatserver.HostplatServerApplication;
import uns.ac.rs.hostplatserver.constant.LabelStatus;
import uns.ac.rs.hostplatserver.exception.ResourceNotExistException;
import uns.ac.rs.hostplatserver.resource.LabelResource;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HostplatServerApplication.class)
@Transactional // IMPORTANT: This annotation roll back everything
public class LabelServiceIntegrationTest {

    private static final String TEST_LABEL_ID = "c4ca4238a0b923820dcc509a6f75849b";

    @Autowired
    public LabelService labelService;

    @Before
    public void setUp() {
    }

    @Test
    public void createLabelTest(){
        LabelResource labelResource = labelService.create(LabelResource.builder()
                .name("BlaBlaTest")
                .build());
        assertNotNull(labelResource);
    }

    @Test
    public void updateLabelTest(){
        LabelResource labelResource = labelService.update(LabelResource.builder()
                .id(TEST_LABEL_ID)
                .name("New Label Name")
                .build());
        assertEquals("New Label Name", labelResource.getName());
    }

    @Test
    public void deleteLabelTest(){
        LabelResource labelResource = labelService.delete(TEST_LABEL_ID);
        assertEquals(labelResource.getStatusId().intValue(), LabelStatus.DELETED.getId());
    }

    @Test
    public void getLabelByIdTest() {
        assertEquals("Test", labelService.get(TEST_LABEL_ID).getName());
    }

    @Test(expected = ResourceNotExistException.class)
    public void getNonExistentLabelTest() {
        labelService.get("blabla123");
    }

    @Test
    public void getAtLeastOneActiveLabelTest() {
        assertNotNull(labelService.getAllActive());
    }

}
