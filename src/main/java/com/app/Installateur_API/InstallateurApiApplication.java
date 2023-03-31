package com.app.Installateur_API;

import com.app.Installateur_API.enmus.BoxStatus;
import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.entity.Company;
import com.app.Installateur_API.entity.Report;
import com.app.Installateur_API.repository.ReportRepository;
import com.app.Installateur_API.service.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class InstallateurApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstallateurApiApplication.class, args);
	}

	@Bean
	CommandLineRunner start(IUserService iUserService,
							IInterventionService iInterventionService,
							IBoxService iBoxService,
							ICompanyService iCompanyService,
							IAdminService iAdminService,
							ReportRepository reportRepository){
		return args -> {

			System.out.println("**********User***********");
			/*Stream.of("sda","chico","khatto").forEach(name->{
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
			/*Company company = iCompanyService.getCompanyById(1L);
			Report report = reportRepository.findById(1L).get();
			Stream.of("ss-dd-ff").forEach(entity->{
				Box newBox = new Box();
				newBox.setId(1L);
				newBox.setEntity("ss-dd-ff");
				newBox.setMatricul(UUID.randomUUID().toString());
				newBox.setStatus(BoxStatus.NOTINSTALLED);
				newBox.setNserie("123456");
				newBox.setCompanyBox(company);
				newBox.setReportBox(report);
				newBox.setCreatAt(new Date());
				newBox.setUpdateAt(new Date());
				iBoxService.creatNewBox(newBox);
			});*/
			System.out.println("**********Box***********");
			System.out.println("**********Intervention***********");
			/*Stream.of("sqfdaea dqsd","dddd ddddeeee","qqqqqqqqqq dddd").forEach(comment->{
				Company company1 = iCompanyService.getCompanyById(1L);
				Intervention newIntervention = new Intervention();
				newIntervention.setComment(comment);
				newIntervention.setStatus(InterStatus.TOPLAN);
				newIntervention.setAppointmentAt(null);
				newIntervention.setUser(null);
				newIntervention.setCompany(company1);
				newIntervention.setCreatAt(new Date());
				newIntervention.setUpdateAt(new Date());
				iInterventionService.creatNewIntervention(newIntervention);
			});*/
			System.out.println("**********Intervention***********");



		};
	}

}
