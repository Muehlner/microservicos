package br.com.caelum.eats.distancia.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.caelum.eats.distancia.RestauranteMongo;
import br.com.caelum.eats.distancia.RestauranteMongoRepository;
import br.com.caelum.eats.distancia.RestaurantesController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestaurantesBase {

	@Autowired
	private RestaurantesController restaurantesController;

	@MockBean
	private RestauranteMongoRepository restauranteMongoRepository;

	@Before
	public void before() {
		RestAssuredMockMvc.standaloneSetup(restaurantesController);

		Mockito.when(restauranteMongoRepository.insert(Mockito.any(RestauranteMongo.class)))
				.thenAnswer((InvocationOnMock invocation) -> {
					RestauranteMongo restauranteMongo = invocation.getArgument(0);
					return restauranteMongo;
				});

	}
}