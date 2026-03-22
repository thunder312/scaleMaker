/**
 * Plugin-Interface für ScaleMaker.
 *
 * <p>Dieses Interface definiert den Vertrag zwischen dem Hauptprogramm und dem
 * ScaleMaker-Plugin. Das Hauptprogramm lädt Implementierungen dieses Interfaces
 * über den Java {@link java.util.ServiceLoader}-Mechanismus.</p>
 *
 * <h2>Verwendung im Hauptprogramm:</h2>
 * <pre>{@code
 * import java.util.ServiceLoader;
 *
 * ServiceLoader<ScaleMakerPlugin> loader = ServiceLoader.load(ScaleMakerPlugin.class);
 * ScaleMakerPlugin plugin = loader.findFirst()
 *     .orElseThrow(() -> new RuntimeException("ScaleMaker-Plugin nicht gefunden"));
 *
 * plugin.initialize();
 * ScaleMakerApi api = plugin.getApi();
 *
 * Scale cDur = api.majorScale("C");
 * // ...
 *
 * plugin.shutdown();
 * }</pre>
 *
 * @author ScaleMaker
 * @version 1.0
 * @see ScaleMakerApi
 * @see java.util.ServiceLoader
 */
public interface ScaleMakerPlugin {

    /**
     * Gibt den Namen des Plugins zurück.
     *
     * @return Plugin-Name (z.B. "ScaleMaker")
     */
    String getName();

    /**
     * Gibt die Versionsnummer des Plugins zurück.
     *
     * @return Versionsnummer als String (z.B. "1.0.0")
     */
    String getVersion();

    /**
     * Gibt eine kurze Beschreibung des Plugins zurück.
     *
     * @return Beschreibung des Plugins
     */
    String getDescription();

    /**
     * Initialisiert das Plugin. Muss vor {@link #getApi()} aufgerufen werden.
     *
     * <p>Das Hauptprogramm ruft diese Methode beim Start auf, nachdem das Plugin
     * über den ServiceLoader entdeckt wurde.</p>
     */
    void initialize();

    /**
     * Fährt das Plugin herunter und gibt Ressourcen frei.
     *
     * <p>Das Hauptprogramm ruft diese Methode beim Beenden auf.</p>
     */
    void shutdown();

    /**
     * Gibt die API-Instanz zurück, über die das Hauptprogramm alle
     * Musik-Theorie-Funktionen nutzen kann.
     *
     * @return Die ScaleMaker API
     * @throws IllegalStateException wenn {@link #initialize()} noch nicht aufgerufen wurde
     * @see ScaleMakerApi
     */
    ScaleMakerApi getApi();
}
