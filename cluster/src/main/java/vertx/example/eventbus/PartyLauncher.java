package vertx.example.eventbus;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import vertx.example.Party;

@SuppressWarnings("NotNullNullableValidation")
public final class PartyLauncher {
  public static void main(String[] args) {
    Vertx.clusteredVertx(
        new VertxOptions().setClusterManager(new HazelcastClusterManager(HazelcastConfigFactory.config(args))),
        vertxResult -> {
          final var options = new DeploymentOptions().setWorker(true);
          vertxResult.result().deployVerticle(new Party(), options);
        }
    );
  }
}
