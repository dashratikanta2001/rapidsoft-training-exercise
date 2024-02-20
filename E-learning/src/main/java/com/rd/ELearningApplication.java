package com.rd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rd.dao.AuthorDao;
import com.rd.dao.VideoDao;
import com.rd.entity.Author;

@SpringBootApplication
public class ELearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningApplication.class, args);
	}


    @Bean
    CommandLineRunner commandLineRunner(
            AuthorDao authorDao,
            VideoDao videoDao
    ) {
		return args->{
			for(int i=1;i<50;i++)
			{
				
				Author author = new Author("Ratikanta"+i, "Dash", "rati"+i+"@mail.com", 24+i);
				authorDao.save(author);
			}
			
			
//			Video video = new Video();
//			video.setName("abc");
//			video.setLength(10);
//			
//			videoDao.save(video);
		};
	}
}
