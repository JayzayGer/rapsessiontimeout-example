package rap.session.jettycustomizer;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.SessionTrackingMode;

import org.eclipse.equinox.http.jetty.JettyCustomizer;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.Jetty;

/**
 * {@link SessionCookieCustomizer} defines the {@link SessionTrackingMode} used by {@link Jetty}. If you want RAP to support multi-tabs use
 * this fragment with {@link Jetty}
 *
 * Following needs to be declared as system property:
 * -Dorg.eclipse.equinox.http.jetty.customizer.class=de.cjt.wasp.raputil.jettycustomizer.SessionCookieCustomizer
 *
 * @author SGanser
 *
 */
public class SessionCookieCustomizer extends JettyCustomizer {

	@Override
	public Object customizeContext(final Object context, final Dictionary<String, ?> settings) {
		Object result = super.customizeContext(context, settings);
		customizeSessionManager(result);
		return result;
	}

	private static void customizeSessionManager(final Object context) {
		if (context instanceof ServletContextHandler) {
			ServletContextHandler jettyContext = (ServletContextHandler) context;
			SessionHandler sessionHandler = jettyContext.getSessionHandler();
			if (sessionHandler != null) {
				Set<SessionTrackingMode> trackingModeSet = new HashSet<>();
				trackingModeSet.add(SessionTrackingMode.URL);
				sessionHandler.setSessionTrackingModes(trackingModeSet);
			}
		}
	}
}
