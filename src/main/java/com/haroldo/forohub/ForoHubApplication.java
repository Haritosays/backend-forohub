package com.haroldo.forohub;

import com.haroldo.forohub.persistence.entities.PermissionEntity;
import com.haroldo.forohub.persistence.entities.Role;
import com.haroldo.forohub.persistence.entities.RoleEnum;
import com.haroldo.forohub.persistence.entities.UserEntity;
import com.haroldo.forohub.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ForoHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForoHubApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository){
        return args -> {
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();
            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();
            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();
            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();

            Role roleAdmin = Role.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            Role roleUser = Role.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();

            Role roleInvited = Role.builder()
                    .roleEnum(RoleEnum.INVITED)
                    .permissionList(Set.of(readPermission))
                    .build();


            UserEntity userJose = UserEntity.builder()
                    .username("jose")
                    .email("jose@gmail.com")
                    .password("$2a$10$hAerOLQ1L7TERE1FY/udze3h/6C60aL5H9VdGmlVdkiUR2s.DJ6di")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleAdmin))
                    .build();

            UserEntity userPedro = UserEntity.builder()
                    .username("pedro")
                    .email("pedro@gmail.com")
                    .password("$2a$10$hAerOLQ1L7TERE1FY/udze3h/6C60aL5H9VdGmlVdkiUR2s.DJ6di")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleUser))
                    .build();

            UserEntity userLucia = UserEntity.builder()
                    .username("lucia")
                    .email("lucia@gmail.com")
                    .password("$2a$10$hAerOLQ1L7TERE1FY/udze3h/6C60aL5H9VdGmlVdkiUR2s.DJ6di")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleInvited))
                    .build();

            userRepository.saveAll(List.of(userJose, userPedro, userLucia));

        };
    }

}
