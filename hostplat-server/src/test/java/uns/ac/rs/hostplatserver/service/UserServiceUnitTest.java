package uns.ac.rs.hostplatserver.service;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.exception.BadRequestException;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.User;
import uns.ac.rs.hostplatserver.repository.UserRepository;
import uns.ac.rs.hostplatserver.service.impl.UserServiceImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceUnitTest {
	
    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;
	


    @Test
    public void findUsers(){
        User lara = new User();
        lara.setId(25L);
        lara.setUsername("lara");
        List<User> users = new ArrayList<>();
        User ana = new User();
        ana.setId(19L);
        ana.setUsername("ana");
        User sara = new User();
        sara.setId(21L);
        sara.setUsername("sara");
        users.add(ana);
        users.add(sara);
        users.add(lara);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<UserDTO> girls = userService.findAll();
        assertEquals(users.size(), girls.size());
        assertEquals(ana.getId(),girls.get(0).getId());
        assertEquals(ana.getUsername(),girls.get(0).getUsername());
        assertEquals(sara.getId(),girls.get(1).getId());
        assertEquals(sara.getUsername(),girls.get(1).getUsername());

    }
    
    @Test
    public void goodId(){
        User dule = new User();
        Long duleId = 33L;
        dule.setId(duleId);
        dule.setUsername("dule");
        dule.setFirstName("dule");
        dule.setLastName("dulic");
        Mockito.when(userRepository.findById(duleId)).thenReturn(Optional.of(dule));
        UserDTO duleDTO = userService.findById(duleId);
        assertEquals(duleId,duleDTO.getId());
        assertEquals("dule",duleDTO.getUsername());
        assertEquals("dule",duleDTO.getFirstName());
        assertEquals("dulic",duleDTO.getLastName());
    }
    
    @Test(expected = BadRequestException.class)
    public void badId(){
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());
        userService.findById(2L);
    }
    


}
