package vertx.example.eventbus;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import vertx.example.Member;

@SuppressWarnings("NotNullNullableValidation")
public final class MemberLauncher {
  public static void main(String[] args) {
    Vertx.clusteredVertx(
        new VertxOptions(),
        vertxResult -> {
          final var vertx = vertxResult.result();
          final var factory = new Member.Factory();
          vertx.registerVerticleFactory(factory);
          final DeploymentOptions optionsMember = new DeploymentOptions().setWorker(true).setInstances(5);
          vertx.deployVerticle(
              factory.prefix() + ':' + Member.class.getName(),
              optionsMember,
              res -> System.out.println("Members deploy result: " + res.succeeded())
          );
        }
    );
  }
}
