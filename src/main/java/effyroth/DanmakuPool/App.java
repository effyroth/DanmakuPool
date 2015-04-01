package effyroth.DanmakuPool;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import effyroth.DanmakuPool.handler.DanmakuHandler;
import effyroth.DanmakuPool.handler.DanmakuPoolHandler;

/**
 * Hello world!
 *
 */
@Slf4j
public class App extends AbstractServer {
	
	static{
		// Enable MongoDB logging in general
		System.setProperty("DEBUG.MONGO", "true");
		// Enable DB operation tracing
		System.setProperty("DB.TRACE", "true");
		
		List<String> engines = new ArrayList<String>();
		engines.stream().parallel().map((a)->{
			return engines;}).findAny();
	}

	private static void route() {

		DanmakuHandler danmakuHandler = new DanmakuHandler();
		getJson("/danmakupools/:danmakupool_id/danmakus", danmakuHandler.get);
		getJson("/danmakupools/:danmakupool_id/danmakus/:id", danmakuHandler.get);
		postJson("/danmakupools/:danmakupool_id/danmakus", danmakuHandler.post);
		putJson("/danmakupools/:danmakupool_id/danmakus/:id", danmakuHandler.put);
		deleteJson("/danmakupools/:danmakupool_id/danmakus/:id", danmakuHandler.delete);

		DanmakuPoolHandler danmakuPoolHandler = new DanmakuPoolHandler();
		getJson("/danmakupools", danmakuPoolHandler.get);
		getJson("/danmakupools/:id", danmakuPoolHandler.get);
		postJson("/danmakupools", danmakuPoolHandler.post);
		putJson("/danmakupools/:id", danmakuPoolHandler.put);
		deleteJson("/danmakupools/:id", danmakuPoolHandler.delete);
	}

	public static void main(String[] args) {
		try {
			init();
			configure();
			route();
		} catch (Throwable t) {
			log.error(String.format(STRONG_FMT, "Server start error!"), t);
		}

		log.info(String.format(STRONG_FMT, "Server started!"));
	}
}
