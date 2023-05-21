package com.app.Installateur_API;
import com.app.Installateur_API.entity.AppRole;
import com.app.Installateur_API.entity.AppUser;
import com.app.Installateur_API.security.AccountService;
import com.app.Installateur_API.security.RsaKeyProperties;

import com.app.Installateur_API.repository.ReportRepository;
import com.app.Installateur_API.repository.StorageRepository;
import com.app.Installateur_API.service.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Configuration
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class InstallateurApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstallateurApiApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	KeyPair keyPair() throws NoSuchAlgorithmException, IOException {
		KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
		var keyPair=keyPairGenerator.generateKeyPair();
		return keyPair;
	}
	/*@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
	/*@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("http://localhost:4200");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);

		return new CorsFilter(source);
	}*/


	@Bean
	CommandLineRunner start(IUserService iUserService,
							IInterventionService iInterventionService,
							IBoxService iBoxService,
							ICompanyService iCompanyService,
							IAdminService iAdminService,
							ReportRepository reportRepository,
							StorageRepository storageRepository,
							AccountService accountService,
							PasswordEncoder passwordEncoder){
		return args -> {
			accountService.newUser(AppUser.builder().email("admin@gmail.com").password(passwordEncoder.encode("1234")).build());
			accountService.newRole(AppRole.builder().roleName("USER").build());
			accountService.newRole(AppRole.builder().roleName("ADMIN").build());
			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("admin","ADMIN");

			System.out.println("**********User***********");
			/*ImageData imageData = storageRepository.findById(5L).get();
			User user2 = iUserService.getUserById(3L);
			user2.setImageUser(imageData);
			iUserService.modifyUser(user2);

			Stream.of("sda","chico","khatto").forEach(name->{
				User newUser = new User();
				newUser.setFirstName(name);
				newUser.setLastName("ddkk");
				newUser.setEmail("sda@sda.sda");
				newUser.setPassword("123456");
				newUser.setCreatAt(new Date());
				newUser.setUpdateAt(new Date());
				iUserService.creatNewUser(newUser);
			});*/
			System.out.println("***********User**********");
			System.out.println("***********Admin**********");
			/*Stream.of("sd","dd","aere").forEach(name->{
				Admin newAdmin = new Admin();
				newAdmin.setFirstName(name);
				newAdmin.setLastName("ddkk");
				newAdmin.setEmail("admin@sda.sda");
				newAdmin.setPassword("123456");
				newAdmin.setCreatAt(new Date());
				newAdmin.setUpdateAt(new Date());
				iAdminService.creatNewAdmin(newAdmin);
			});*/
			System.out.println("***********Admin**********");
			System.out.println("**********Company***********");
			/*ImageData image = storageRepository.findByName("c0079e5f-92ed-4970-9086-708b9c94667e").get();
			Company company = iCompanyService.getCompanyById(1L);
			company.setImageCompany(image);
			iCompanyService.modifyCompany(company);*/
			/*Stream.of("soda","dota","voka").forEach(name->{
				Company newCompany = new Company();
				newCompany.setName(name);
				newCompany.setLocation("ddkk");
				newCompany.setEmail("admin@sda.sda");
				newCompany.setPhone("123456");
				//newCompany.setImageCompany(null);
				newCompany.setCreatAt(new Date());
				newCompany.setUpdateAt(new Date());
				iCompanyService.creatNewCompany(newCompany);
			});*/
			System.out.println("**********Company***********");
			System.out.println("**********Box***********");
			//Company company = iCompanyService.getCompanyById(3L);
			//Report report = reportRepository.findById(1L).get();
			/*Stream.of("qq-dd-ff","sd-dd-ff","gg-ee-cc","zz-dd-ff","ge-dd-ff").forEach(entity->{
				Box newBox = new Box();
				newBox.setName("strada_222");
				newBox.setEntity(entity);
				newBox.setMatricul("");
				newBox.setBoxValue("");
				newBox.setNserie("123456");
				newBox.setCompanyBox(company);
				newBox.setCreatAt(new Date());
				newBox.setUpdateAt(new Date());
				iBoxService.creatNewBox(newBox);
			});*/
            /*Box box = iBoxService.getBoxById(2L);
            box.setIsSend(false);
            iBoxService.upadateBox(box);*/
			System.out.println("**********Box***********");
			System.out.println("**********Intervention***********");
			/*Stream.of("sqfdaea dqsd dhjjhd kjlqj ldjoiajei djnd osiha  spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning").forEach(comment->{
				Company company1 = iCompanyService.getCompanyById(1L);
				Intervention newIntervention = new Intervention();
				newIntervention.setComment(comment);
				newIntervention.setStatus("TOPLAN");
				newIntervention.setAppointmentAt(null);
				newIntervention.setUser(null);
				newIntervention.setCompany(company1);
				newIntervention.setCreatAt(new Date());
				newIntervention.setUpdateAt(new Date());
				iInterventionService.creatNewIntervention(newIntervention);
			});*/
			System.out.println("**********Intervention***********");
			System.out.println("**********loginuer***********");
			//User user = iUserService.loginUser("sa@sda.sda","123456");
            System.out.println("**********loginuser***********");
            System.out.println("**********imageData***********");
           /* Box box = iBoxService.getBoxById(1L);
            ImageData imageData = storageRepository.findById(1L).get();
            imageData.setBox(box);
            storageRepository.save(imageData);
            ImageData imageData2 = storageRepository.findById(4L).get();
            imageData2.setBox(box);
            storageRepository.save(imageData2);*/
            System.out.println("**********imageData***********");



		};
	}



}
