package medplatform.services;

import medplatform.entities.Caregiver;
import medplatform.entities.Doctor;
import medplatform.entities.Patient;
import medplatform.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import medplatform.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository<Patient> userRepositoryP;

    @Autowired
    UserRepository<Caregiver> userRepositoryC;

    @Autowired
    UserRepository<Doctor> userRepositoryD;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = userRepositoryP.findByUsername(username).orElse(null);

        Doctor doctor = userRepositoryD.findByUsername(username).orElse(null);

        Caregiver caregiver = userRepositoryC.findByUsername(username).orElse(null);

        User user = new User();
        if(patient != null) {
            user = patient;
            user.setRole("patient");
        }
        else if(doctor != null) {
            user = doctor;
            user.setRole("doctor");
        }
        else if(caregiver != null) {
            user = caregiver;
            user.setRole("caregiver");
        }

        return UserDetailsImpl.build(user);
    }

}
