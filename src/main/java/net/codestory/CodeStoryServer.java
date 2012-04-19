package net.codestory;

import com.google.common.util.concurrent.*;
import com.sun.jersey.api.container.httpserver.*;
import com.sun.jersey.api.core.*;
import com.sun.net.httpserver.*;
import org.codehaus.jackson.jaxrs.*;

public class CodeStoryServer extends AbstractIdleService {
	private final int port;
	private HttpServer httpServer;

	public CodeStoryServer(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	@Override
	protected void startUp() throws Exception {
		ResourceConfig config = new DefaultResourceConfig(CodeStoryResource.class, JacksonJsonProvider.class);
		httpServer = HttpServerFactory.create("http://localhost:" + port + "/", config);
		httpServer.start();
	}

	@Override
	protected void shutDown() {
		httpServer.stop(1);
	}

	public static void main(String[] args) {
		new CodeStoryServer(8080).startAndWait();
	}
}