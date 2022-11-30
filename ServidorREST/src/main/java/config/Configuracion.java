package config;


import domain.errores.ConnectionDBException;
import domain.errores.DataFailureException;
import jakarta.inject.Singleton;
import lombok.Getter;


import java.util.Properties;

@Getter
@Singleton
public class Configuracion {
    private String ruta;
    private String user;
    private String password;
    private String driver;

    public Configuracion() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream(CostantesConfig.CONFIG_YAML));
            this.ruta = properties.getProperty(CostantesConfig.RUTA);
            this.password = properties.getProperty(CostantesConfig.PASSWORD);
            this.user = properties.getProperty(CostantesConfig.USER);
            this.driver = properties.getProperty(CostantesConfig.DRIVER);
        } catch (Exception ignored) {
        }
    }
}