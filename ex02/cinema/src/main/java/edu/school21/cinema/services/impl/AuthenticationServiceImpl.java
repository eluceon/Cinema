package edu.school21.cinema.services.impl;

import edu.school21.cinema.dao.AuthenticationDao;
import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationDao authenticationDao;
    @Override
    @Transactional
    public void add(Authentication authentication) {
        authenticationDao.add(authentication);
    }

    @Override
    public List<Authentication> getUserAuthHistory(Integer userId) {
        return authenticationDao.findAllByUserId(userId);
    }
}
