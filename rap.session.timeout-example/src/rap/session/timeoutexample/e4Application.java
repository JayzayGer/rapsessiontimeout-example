package rap.session.timeoutexample;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.e4.E4ApplicationConfig;
import org.eclipse.rap.e4.E4EntryPointFactory;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.Application.OperationMode;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;

public class e4Application implements ApplicationConfiguration {

	private final static String E4XMI = "platform:/plugin/rap.session.timeout-example/Application.e4xmi";

	@Override
	public void configure(final Application application) {
		Map<String, String> properties = new HashMap<>();
		properties.put(WebClient.PAGE_TITLE, "Hello e4 RAP");
		E4ApplicationConfig config = E4ApplicationConfig.create(E4XMI);
		E4EntryPointFactory entryPointFactory = new E4EntryPointFactory(config);
		application.addEntryPoint("/test", entryPointFactory, properties);
		application.setOperationMode(OperationMode.SWT_COMPATIBILITY);
	}

}
