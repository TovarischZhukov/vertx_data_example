package vertx.example.shared;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import vertx.example.Auditor;

@SuppressWarnings("NotNullNullableValidation")
public final class AuditorLauncher {

  public static void main(String[] args) {
    Vertx.clusteredVertx(
        new VertxOptions(),
        vertxResult -> {
          final var options = new DeploymentOptions().setWorker(true);
          vertxResult.result().deployVerticle(new Auditor(), options);
        }
    );
  }
}
