package vertx.example.eventbus;

import com.hazelcast.config.Config;

@SuppressWarnings("NotNullNullableValidation")
public enum HazelcastConfigFactory {
  ;

  public static Config config(String[] hosts) {
    final var config = new Config();

    for (int i = 1; i < hosts.length; i++) {
      config.getNetworkConfig().getJoin().getTcpIpConfig().addMember(hosts[i]).setEnabled(true);
    }

    config.getNetworkConfig().getInterfaces().addInterface(hosts[0]).setEnabled(true);
    config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);

    return config;
  }
}
