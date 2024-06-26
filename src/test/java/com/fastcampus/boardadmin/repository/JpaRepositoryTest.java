package com.fastcampus.boardadmin.repository;


import com.fastcampus.boardadmin.domain.UserAccount;
import com.fastcampus.boardadmin.domain.constant.RoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import({JpaRepositoryTest.TestJpaConfig.class})
@DataJpaTest
class JpaRepositoryTest {
    private final UserAccountRepository userAccountRepository;


    public JpaRepositoryTest(
            @Autowired UserAccountRepository userAccountRepository
    ) {
        this.userAccountRepository = userAccountRepository;
    }


    @DisplayName("회원 정보 select 테스트")
    @Test
    void givenUserAccounts_whenSelecting_thenWorksFine() {
        //Given

        //When
        List<UserAccount> userAccounts = userAccountRepository.findAll();

        //Then
        assertThat(userAccounts)
                .isNotNull()
                .hasSize(4);
    }

    @DisplayName("회원 정보 insert 테스트")
    @Test
    void givenUserAccount_whenInserting_thenWorksFine() {
        //Given
        long prevCount = userAccountRepository.count();
        UserAccount userAccount = UserAccount.of("testId", "testpwd", Set.of(RoleType.DEVELOPER),"test@mail.com",null,null);


        //When
        userAccountRepository.save(userAccount);

        //Then
        assertThat(userAccountRepository.count()).isEqualTo(prevCount + 1);

    }

    @DisplayName("회원 정보 update 테스트")
    @Test
    void givenUserAccountInfoAndRoleType_whenUpdating_thenWorksFine() {
        //Given

        UserAccount userAccount = userAccountRepository.getReferenceById("twonezero");

        userAccount.addRoleType(RoleType.DEVELOPER);
        userAccount.addRoleTypes(List.of(RoleType.ADMIN, RoleType.USER, RoleType.USER));
        userAccount.removeRoleType(RoleType.ADMIN);



        //When
        var updatedUser =userAccountRepository.save(userAccount);

        //Then
        assertThat(updatedUser)
                .hasFieldOrPropertyWithValue("userId","twonezero")
                .hasFieldOrPropertyWithValue("roleTypes",Set.of(RoleType.DEVELOPER, RoleType.USER));
    }

    @DisplayName("회원 정보 delete 테스트")
    @Test
    void givenUserAccount_whenDeleting_thenWorksFine() {
        //Given
        long prevCount = userAccountRepository.count();
        var userAccount = userAccountRepository.getReferenceById("twonezero");

        //When
        userAccountRepository.delete(userAccount);

        //Then
        assertThat(userAccountRepository.count()).isEqualTo(prevCount - 1);
    }


    @EnableJpaAuditing
    @TestConfiguration
    static class TestJpaConfig {
        @Bean
        AuditorAware<String> auditorAware(){return () -> Optional.of("twonezero");}
    }

}

