package net.sue445.s3tiger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * igonore testcace if this application is running on App Engine server.
 * @author sue445
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface IgnoreServer {
	/**
	 * The optional reason why the test is ignored.
	 */
	String value() default "";

}
