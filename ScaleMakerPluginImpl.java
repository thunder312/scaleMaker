/**
 * ServiceLoader-Implementierung des {@link ScaleMakerPlugin}-Interfaces.
 *
 * <p>Diese Klasse wird vom Java {@link java.util.ServiceLoader} automatisch entdeckt,
 * wenn das Plugin-JAR im Classpath des Hauptprogramms liegt. Die Registrierung
 * erfolgt über die Datei {@code META-INF/services/ScaleMakerPlugin}.</p>
 *
 * <h2>Verwendung im Hauptprogramm:</h2>
 * <pre>{@code
 * ServiceLoader<ScaleMakerPlugin> loader = ServiceLoader.load(ScaleMakerPlugin.class);
 * ScaleMakerPlugin plugin = loader.findFirst()
 *     .orElseThrow(() -> new RuntimeException("ScaleMaker-Plugin nicht gefunden"));
 *
 * plugin.initialize();
 * ScaleMakerApi api = plugin.getApi();
 *
 * Scale cDur = api.majorScale("C");
 * Chord[] stufenakkorde = api.getDiatonicChords(cDur);
 *
 * plugin.shutdown();
 * }</pre>
 *
 * <p><strong>Hinweis:</strong> Diese Klasse ist nicht für die direkte Instanziierung
 * durch das Hauptprogramm gedacht. Immer den ServiceLoader verwenden.</p>
 *
 * @author ScaleMaker
 * @version 1.0
 * @see ScaleMakerPlugin
 * @see ScaleMakerApi
 */
public class ScaleMakerPluginImpl implements ScaleMakerPlugin {

    private ScaleMakerApi api;

    /**
     * Öffentlicher No-Arg-Konstruktor, erforderlich für den {@link java.util.ServiceLoader}.
     */
    public ScaleMakerPluginImpl() {
        // ServiceLoader benötigt einen öffentlichen parameterlosen Konstruktor
    }

    @Override
    public String getName() {
        return "ScaleMaker";
    }

    @Override
    public String getVersion() {
        return ScaleMaker.getVersion();
    }

    @Override
    public String getDescription() {
        return "Musiktheorie-Bibliothek für Tonleitern, Akkorde und Intervalle";
    }

    /**
     * Initialisiert das Plugin und erstellt die API-Instanz.
     * Muss vor {@link #getApi()} aufgerufen werden.
     */
    @Override
    public void initialize() {
        api = new ScaleMakerApiImpl();
    }

    /**
     * Fährt das Plugin herunter und gibt die API-Instanz frei.
     */
    @Override
    public void shutdown() {
        api = null;
    }

    /**
     * Gibt die initialisierte API-Instanz zurück.
     *
     * @return Die {@link ScaleMakerApi}-Instanz
     * @throws IllegalStateException wenn {@link #initialize()} noch nicht aufgerufen wurde
     */
    @Override
    public ScaleMakerApi getApi() {
        if (api == null) {
            throw new IllegalStateException(
                "ScaleMaker-Plugin ist nicht initialisiert. initialize() zuerst aufrufen."
            );
        }
        return api;
    }

    @Override
    public String toString() {
        return getName() + " v" + getVersion();
    }
}
