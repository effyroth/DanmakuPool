package effyroth.DanmakuPool;

import java.io.File;
import java.io.FileNotFoundException;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import spark.ResponseTransformer;
import spark.Route;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;
import effyroth.DanmakuPool.common.FileUtil;
import effyroth.DanmakuPool.conf.Config;
import effyroth.DanmakuPool.server.JacksonTransformer;
import effyroth.DanmakuPool.server.KZFreeMarkerEngine;

@Slf4j
public abstract class AbstractServer {

	@Getter
	protected static String ipAddress;
	
	@Getter
	protected static int port;

	protected final static String STATIC_DIR = "webapp/static";
	protected final static String TEMPLATE_DIR = "webapp/templates";

	protected static FreeMarkerEngine tmplEngine;
	protected static ResponseTransformer jsonTransformer;

	protected static void configStaticFileDir() throws FileNotFoundException {
		File staticDir = FileUtil.getResourceFile(STATIC_DIR);
		Spark.externalStaticFileLocation(staticDir.getPath());
		log.info(String.format("Static File: %s", staticDir));
	}

	protected static void configTemplateEngine() {
//		if ("true".equalsIgnoreCase(Config.get("kuaizhan.debug"))) {
//			tmplEngine = new KZFreeMarkerSegmentEngine(TEMPLATE_DIR);
//		} else {
			tmplEngine = new KZFreeMarkerEngine(TEMPLATE_DIR);
//		}
	}

	protected static void configJsonTransformer() {
		jsonTransformer = new JacksonTransformer();
	}

	protected static void configHTTPServer() {
		AbstractServer.ipAddress = "0.0.0.0";
		AbstractServer.port = Integer.parseInt(Config.get("bindport", "8086"));

		Spark.ipAddress(AbstractServer.ipAddress);
		Spark.port(AbstractServer.port);

	}

	protected static void configure() throws FileNotFoundException {
		configStaticFileDir();

		configTemplateEngine();
		configJsonTransformer();

		configHTTPServer();
	}
	

	public static void init() {
		Config.class.getName();
	}
	
	public static void getJson(final String path, final Route route){
		Spark.get(path, route, jsonTransformer);
	}
	
	public static void postJson(final String path, final Route route){
		Spark.post(path, route, jsonTransformer);
	}
	
	public static void putJson(final String path, final Route route){
		Spark.put(path, route, jsonTransformer);
	}
	
	public static void deleteJson(final String path, final Route route){
		Spark.delete(path, route, jsonTransformer);
	}
	

	protected static final String STRONG_FMT = "*************** %s ***************";
}
