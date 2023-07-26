package lk.uom.minigame;

import lk.uom.minigame.Utility.RandomNumberGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MiniGameApplication {

	public static void main(String[] args) {
		RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.getInstance();
		randomNumberGenerator.setMax(10);
		randomNumberGenerator.setMin(1);
		SpringApplication.run(MiniGameApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
