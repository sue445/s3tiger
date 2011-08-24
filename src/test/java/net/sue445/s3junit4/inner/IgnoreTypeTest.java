package net.sue445.s3junit4.inner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Method;

import net.sue445.s3junit4.IgnoreDevelopment;
import net.sue445.s3junit4.IgnoreJUnit;
import net.sue445.s3junit4.IgnoreProduction;
import net.sue445.s3junit4.IgnoreServer;

import org.junit.Test;


public class IgnoreTypeTest {
	@Test
	public void toEnumClass_Production() throws Exception {
		assertThat(IgnoreType.toEnum(IgnoreProductionClass.class), is(IgnoreType.PRODUCTION));
	}

	@IgnoreProduction
	private static class IgnoreProductionClass{

	}

	@Test
	public void toEnumClass_Development() throws Exception {
		assertThat(IgnoreType.toEnum(IgnoreDevelopmentClass.class), is(IgnoreType.DEVELOPMENT));
	}

	@IgnoreDevelopment
	private static class IgnoreDevelopmentClass{

	}

	@Test
	public void toEnumClass_Server() throws Exception {
		assertThat(IgnoreType.toEnum(IgnoreServerClass.class), is(IgnoreType.SERVER));
	}

	@IgnoreServer
	private static class IgnoreServerClass{

	}

	@Test
	public void toEnumClass_JUnit() throws Exception {
		assertThat(IgnoreType.toEnum(IgnoreJUnitClass.class), is(IgnoreType.JUNIT));
	}

	@IgnoreJUnit
	private static class IgnoreJUnitClass{

	}

	@Test
	public void toEnumClass_NotIgnore() throws Exception {
		assertThat(IgnoreType.toEnum(IgnoreNotIgnoreClass.class), is(IgnoreType.NOT_IGNORE));
	}

	private static class IgnoreNotIgnoreClass{

	}

	public static class IgnoreMethodClass{
		@IgnoreProduction
		public static void production(){
		}

		@IgnoreDevelopment
		public void development(){
		}

		@IgnoreServer
		public void server(){
		}

		@IgnoreJUnit
		public void junit(){
		}

		public void notIgnore(){
		}
	}

	@Test
	public void toEnumMethod_Production() throws Exception {
		Method method = IgnoreMethodClass.class.getMethod("production");
		assertThat(IgnoreType.toEnum(method), is(IgnoreType.PRODUCTION));
	}

	@Test
	public void toEnumMethod_Development() throws Exception {
		Method method = IgnoreMethodClass.class.getMethod("development");
		assertThat(IgnoreType.toEnum(method), is(IgnoreType.DEVELOPMENT));
	}

	@Test
	public void toEnumMethod_Server() throws Exception {
		Method method = IgnoreMethodClass.class.getMethod("server");
		assertThat(IgnoreType.toEnum(method), is(IgnoreType.SERVER));
	}

	@Test
	public void toEnumMethod_JUnit() throws Exception {
		Method method = IgnoreMethodClass.class.getMethod("junit");
		assertThat(IgnoreType.toEnum(method), is(IgnoreType.JUNIT));
	}

	@Test
	public void toEnumMethod_NotIgnore() throws Exception {
		Method method = IgnoreMethodClass.class.getMethod("notIgnore");
		assertThat(IgnoreType.toEnum(method), is(IgnoreType.NOT_IGNORE));
	}
}
