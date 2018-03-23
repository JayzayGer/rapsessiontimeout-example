package rap.session.jettycustomizer;

import java.util.Dictionary;

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
		int port = (int) settings.get("http.port");
		customizeSessionManager(result, port);
		return result;
	}

	private void customizeSessionManager(final Object context, final int port) {
		if (context instanceof ServletContextHandler) {
			ServletContextHandler jettyContext = (ServletContextHandler) context;
			SessionHandler sessionHandler = jettyContext.getSessionHandler();
			if (sessionHandler != null) {
				String cookieName = "JSESSIONID_" + port;
				sessionHandler.setSessionCookie(cookieName);
			}
		}
	}
}
