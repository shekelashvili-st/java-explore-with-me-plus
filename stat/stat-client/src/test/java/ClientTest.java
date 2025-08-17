import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.stat.StatServer;
import ru.practicum.stat.client.StatClient;
import ru.practicum.stat.dto.EndpointHit;
import ru.practicum.stat.dto.ViewStats;
import ru.practicum.stat.service.StatService;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Transactional
@SpringBootTest(classes = StatServer.class,
        properties = {"server.port=9090", "client.url=http://localhost:9090"},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClientTest {
    private final StatClient client;
    private final StatService service;

    @Test
    public void createEndpointHitsUniqueFalse() {
        EndpointHit hit = new EndpointHit("ewm-main", "/events/1", "192.168.1.1", LocalDateTime.now());
        EndpointHit hit2 = new EndpointHit("ewm-main", "/events/1", "192.168.1.1", LocalDateTime.now());
        client.create(hit);
        client.create(hit2);

        List<ViewStats> stats = service.getStats(LocalDateTime.now().minusHours(1), LocalDateTime.now(), List.of("/events/1"), false);
        assertThat(stats.size(), equalTo(1));
        assertThat(stats.getFirst().getHits(), equalTo(2));
    }

    @Test
    public void createEndpointHitsUniqueTrue() {
        EndpointHit hit = new EndpointHit("ewm-main", "/events/2", "192.168.1.1", LocalDateTime.now());
        EndpointHit hit2 = new EndpointHit("ewm-main", "/events/2", "192.168.1.1", LocalDateTime.now());
        client.create(hit);
        client.create(hit2);

        List<ViewStats> stats = service.getStats(LocalDateTime.now().minusHours(1), LocalDateTime.now(), List.of("/events/2"), true);
        assertThat(stats.size(), equalTo(1));
        assertThat(stats.getFirst().getHits(), equalTo(1));
    }

    @Test
    public void getStatsForEndpointUniqueFalse() {
        EndpointHit hit = new EndpointHit("ewm-main", "/events/3", "192.168.1.1", LocalDateTime.now());
        EndpointHit hit2 = new EndpointHit("ewm-main", "/events/3", "192.168.1.1", LocalDateTime.now());
        client.create(hit);
        client.create(hit2);

        List<ViewStats> stats = client.getStats(LocalDateTime.now().minusHours(1), LocalDateTime.now(), List.of("/events/3"), false);
        assertThat(stats.size(), equalTo(1));
        assertThat(stats.getFirst().getHits(), equalTo(2));
    }
}
