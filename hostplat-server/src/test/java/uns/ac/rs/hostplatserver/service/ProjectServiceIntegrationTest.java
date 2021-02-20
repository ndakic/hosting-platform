package uns.ac.rs.hostplatserver.service;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import uns.ac.rs.hostplatserver.HostplatServerApplication;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.Project;
import uns.ac.rs.hostplatserver.service.impl.ProjectServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HostplatServerApplication.class)
@Transactional // IMPORTANT: This annotation roll back everything
public class ProjectServiceIntegrationTest {


	@Autowired
	private ProjectServiceImpl projectService;

	
	public static final Long PERSISTED_PROJECT_ID = 15l;
    public static final Long INVALID_PLACE_ID = -1l;
	
//	@Test
//	public void findOne_ProjectSuccess() throws ResourceNotFoundException {
//		Project project = projectService.findOne(PERSISTED_PROJECT_ID);
//		assertEquals(PERSISTED_PROJECT_ID, project.getId());
//	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void findOne_pROJECTFail() throws ResourceNotFoundException {
		projectService.findOne(INVALID_PLACE_ID);
	}
		
}
