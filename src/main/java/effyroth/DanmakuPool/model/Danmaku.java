package effyroth.DanmakuPool.model;

import lombok.Data;

import org.codehaus.jackson.annotate.JsonProperty;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "danmaku", noClassnameStored = true)
@Data
public class Danmaku {
	
	@Id
	long id;
	
	@JsonProperty("pool_id")
	@Property("pool_id")
	long poolId;
	
	@JsonProperty("play_time")
	@Property("play_time")
	float playTime;
	
	int color;
	
	@JsonProperty("msg")
	@Property("msg")
	String msg;
	
	@JsonProperty("font_size")
	@Property("font_size")
	int fontSize;
	
	int mode;
	
	@JsonProperty("created_at")
	@Property("created_at")
	private long createdAt;

}
