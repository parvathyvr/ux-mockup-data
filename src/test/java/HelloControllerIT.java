package hello;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {

    @LocalServerPort
    private int port;

    private URI base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URI("http://localhost:" + port + "/");
    }

    @Test
    public void getHello() throws Exception {
		AsyncClientHttpRequestFactory  asyncFactory = new HttpComponentsAsyncClientHttpRequestFactory();
        AsyncClientHttpRequest asynReq = asyncFactory.createAsyncRequest(base.toString(), HttpMethod.GET);
        ListenableFuture<ClientHttpResponse> future = asynReq.executeAsync();
        ClientHttpResponse response = future.get();
        assertThat(response.getBody(), equalTo(200));

    }
}
