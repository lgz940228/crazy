package com.lgz.crazy;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FdfsClientConfig.class)
public class CrazyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrazyApplication.class, args);
	}

}

