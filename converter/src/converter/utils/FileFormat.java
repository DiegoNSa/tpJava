package converter.utils;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FileFormat {
	public String supportedExtention() default "json";
}
