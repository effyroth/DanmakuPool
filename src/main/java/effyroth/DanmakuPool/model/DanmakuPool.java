package effyroth.DanmakuPool.model;

import java.util.Date;

import lombok.Data;

import org.codehaus.jackson.annotate.JsonProperty;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "danmakupools", noClassnameStored = true)
@Data
public class DanmakuPool {
	@Id
	long id;
	
	int cap;
	
	int num;
	
	@JsonProperty("created_at")
	@Property("created_at")
	private long createdAt;
	
	Date utime;
	
	
}
