package vertx.example;

import io.vertx.core.AbstractVerticle;

@SuppressWarnings({"MagicNumber", "UseOfSystemOutOrSystemErr"})
public final class Auditor extends AbstractVerticle {

  @Override
  public void start() {
    System.out.println("Start auditor");

    vertx.setPeriodic(5000, timer ->
        vertx.sharedData().getAsyncMap("cookies", map ->
            map.result().entries(cookies -> {
              System.out.println("Audition:");
              cookies.result().forEach((stove, cookiesNumber) ->
                  System.out.println(stove + " did " + cookiesNumber + " cookies (auditor)")
              );
            })
        )
    );
  }
}