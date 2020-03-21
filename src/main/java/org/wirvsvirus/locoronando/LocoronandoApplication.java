package org.wirvsvirus.locoronando;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import org.wirvsvirus.locoronando.dealer.DealerService;
import org.wirvsvirus.locoronando.dealer.model.db.Address;
import org.wirvsvirus.locoronando.dealer.model.db.Dealer;
import org.wirvsvirus.locoronando.product.Product;
import org.wirvsvirus.locoronando.product.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class LocoronandoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LocoronandoApplication.class, args);

		ServiceLul bean = context.getBean(ServiceLul.class);
		bean.test();
		bean.test2();

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public ServiceLul serviceLul(DealerService dealerService, ProductRepository productRepository) {
		return new ServiceLul(dealerService, productRepository);
	}

	@RequiredArgsConstructor
	class ServiceLul {
		private final DealerService dealerService;
		private final ProductRepository productRepository;

		@Autowired
		JdbcTemplate jdbcTemplate;

		@Transactional(Transactional.TxType.REQUIRES_NEW)
		public void test() {
//			Dealer dealer = new Dealer();
//			dealer.setName("test");
//			dealer.setRadius((short) 20);
//			Product product = new Product();
//			product.setDescription("test");
//			dealer.setProducts(Collections.list.of(product));
			Dealer dealer = new Dealer();
			Address address = new Address();
			address.setHouseNumber("1");
			address.setPostalCode("74348");
			address.setStreet("Hindemithstraße");
			dealer.setAddress(new Address());
			dealer.setName("Favorite Dealder");
			dealer.setRadius((short) 1);

			Product product = new Product();
			product.setDealer(dealer);
			product.setTitle("Fancy Product");

			this.productRepository.save(product);
			this.dealerService.create(dealer);

		}

		@Transactional(Transactional.TxType.REQUIRES_NEW)
		public void test2() {

			log.info(String.valueOf(this.productRepository.findAll()));

			log.info(String.valueOf(this.dealerService.findAll()));

		}
	}

}
