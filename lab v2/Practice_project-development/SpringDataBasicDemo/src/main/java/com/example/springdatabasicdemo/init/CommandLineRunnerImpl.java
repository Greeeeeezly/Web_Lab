package com.example.springdatabasicdemo.init;

import com.example.springdatabasicdemo.enums.Role;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.models.UserRole;
import com.example.springdatabasicdemo.repositories.UserRepo;
import com.example.springdatabasicdemo.repositories.UserRoleRepo;
import com.example.springdatabasicdemo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserRepo userRepository;
    private final UserRoleRepo userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public CommandLineRunnerImpl(UserRepo userRepository, UserRoleRepo userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initUsers();
        init();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var normalUserRole = new UserRole(Role.User);
            var adminRole = new UserRole(Role.Admin);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalUserRole);
        }
    }

    private void initUsers() {

        if (userRepository.count() == 0) {

        initAdmin();
        initNormalUser();
        }
    }

    private void initAdmin() {
        var adminRole = userRoleRepository.
                findUserRoleByRole(Role.Admin).orElseThrow();

        var adminUser = new User("admin", passwordEncoder.encode("admin"), "Admin", "Adminovich",true, "22222222");
        adminUser.setUserRole(adminRole);

        userRepository.save(adminUser);
    }


    private void initNormalUser() {
        var adminRole = userRoleRepository.
                findUserRoleByRole(Role.User).orElseThrow();

        var adminUser = new User("user", passwordEncoder.encode("user"), "User", "Userovich", true,"22222222");
        adminUser.setUserRole(adminRole);

        userRepository.save(adminUser);
    }

    @Autowired
    AuthService authService;
    @Autowired
    BrandService<UUID> brandService;
    @Autowired
    ModelService<UUID> modelService;
    @Autowired
    OfferService<UUID> offerService;
    @Autowired
    UserService<UUID> userService;
    @Autowired
    UserRoleService<UUID> roleService;
    @Autowired
    PageViewService pageViewService;

    /*@Override
    public void run(String... args) throws Exception {
    }*/


    public void init() {


        Map<String, Long> counts = pageViewService.countOccurrences();
        for (String key : counts.keySet()) {
            System.out.println(key + ": " + counts.get(key));
        }
       /* UserRoleDto ur = new UserRoleDto(null,Role.User);
        UserRoleDto ur1 = new UserRoleDto(null,Role.Admin);

        ur = roleService.register(ur);
        ur1 = roleService.register(ur1);

        UserDto user1 = new UserDto(null,"Mike97", "password1", "Mike", "Doe", "image1.jpg", ur1);
        UserDto user2 = new UserDto(null,"johnDoe", "password123", "John", "Doe", "john.jpg", ur);
        UserDto user3 = new UserDto(null,"admin1", "adminPass", "Admin", "User", "admin.jpg", ur);
        UserDto user4 = new UserDto(null,"janeDoe", "jane123", "Jane", "Doe", "jane.jpg", ur);
        UserDto user5 = new UserDto(null,"user5", "pass5", "User", "Five", "user5.jpg", ur1);

        user1 = userService.register(user1);
        user2 = userService.register(user2);
        user3 = userService.register(user3);
        user4 = userService.register(user4);
        user5 = userService.register(user5);*/

        /*BrandDto brand1 = new BrandDto(null, "Brand 1");
        BrandDto brand2 = new BrandDto(null, "Brand 2");
        BrandDto brand3 = new BrandDto(null, "Brand 3");
        BrandDto brand4 = new BrandDto(null, "Brand 4");
        BrandDto brand5 = new BrandDto(null, "Brand 5");

        brand1 = brandService.register(brand1);
        brand2 = brandService.register(brand2);
        brand3 = brandService.register(brand3);
        brand4 = brandService.register(brand4);
        brand5 = brandService.register(brand5);

        ModelDto model1 = new ModelDto(null, "Model 1", Category.Buss, "model1.jpg", 2020, 2023, brand1);
        ModelDto model2 = new ModelDto(null, "Model 2", Category.Buss, "model2.jpg", 2019, 2022, brand2);
        ModelDto model3 = new ModelDto(null, "Model 3", Category.Truck, "model3.jpg", 2022, 2024, brand3);
        ModelDto model4 = new ModelDto(null, "Model 4", Category.Motorcycle, "model4.jpg", 2021, 2025, brand4);
        ModelDto model5 = new ModelDto(null, "Model 5", Category.Car, "model5.jpg", 2023, 2026, brand5);

        modelService.registerM(model1);
        modelService.registerM(model2);
        modelService.registerM(model3);
        modelService.registerM(model4);
        modelService.registerM(model5);

        OfferDto offer1 = new OfferDto(null, "Offer 1", Engine.GASOLINE, "offer1.jpg", 10000, BigDecimal.valueOf(20000), Transmission.MANUAL, 2022,modelService.getAll().get(0) , userService.getAll().get(0));
        OfferDto offer2 = new OfferDto(null, "Offer 2", Engine.DIESEL, "offer2.jpg", 15000, BigDecimal.valueOf(25000), Transmission.AUTOMATIC, 2020, modelService.getAll().get(1), userService.getAll().get(0));
        OfferDto offer3 = new OfferDto(null, "Offer 3", Engine.GASOLINE, "offer3.jpg", 8000, BigDecimal.valueOf(18000), Transmission.MANUAL, 2021, modelService.getAll().get(2), userService.getAll().get(0));
        OfferDto offer4 = new OfferDto(null, "Offer 4", Engine.ELECTRIC, "offer4.jpg", 5000, BigDecimal.valueOf(30000), Transmission.AUTOMATIC, 2023, modelService.getAll().get(3),userService.getAll().get(1));
        OfferDto offer5 = new OfferDto(null, "Offer 5", Engine.HYBRID, "offer5.jpg", 12000, BigDecimal.valueOf(22000), Transmission.AUTOMATIC, 2022, modelService.getAll().get(4), userService.getAll().get(0));

        offerService.register(offer1);
        offerService.register(offer2);
        offerService.register(offer3);
        offerService.register(offer4);
        offerService.register(offer5);
*/
    }
}

/*
<!--<div><span th:text="'Last update of Database:'"></span> 
                    <span th:text="${#dates.createNow()}"></span>
                </div>
                &lt;!&ndash; Advertisement Section &ndash;&gt;
                <div class="mt-5">
                    <h2>Discover Our Latest Offers!</h2>
                    <p>Check out our latest deals on a wide range of vehicles. Don't miss out on these limited-time offers!</p>
                    <a href="/models/all" class="btn btn-primary">View Offers</a>
                </div>
                <div class="mt-5">
                    <h2>Find Your Dream Car!</h2>
                    <p>Explore our extensive inventory of high-quality vehicles. Your dream car is just a click away!</p>
                    <a href="/offers/all" class="btn btn-primary">Browse Inventory</a>
                </div>-->Set - минимальный котракт, используем его, а не лист
*   СЕРВИСЫ:
* Репозиторий внедряем через сеттер мапер и валидацию через конструктор
* репозиторий через сеттер для того чтобы вариативно подставлять нужную нам реализацию репозитория
* DTO
* @NotNull
* @NotEmpty
* .SaveAndFlush - позволяет не сохранять объект в контексте а сразу сохранить в бд
* Iterable гарантирует что массив колекции уже лежит у вас в памяти+
* */

