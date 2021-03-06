package net.sue445.s3tiger.internal;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.lang.reflect.Method;

import net.sue445.s3tiger.IgnoreDevelopment;
import net.sue445.s3tiger.IgnoreNotServer;
import net.sue445.s3tiger.IgnoreProduction;
import net.sue445.s3tiger.IgnoreServer;
import net.sue445.s3tiger.internal.IgnoreType;

import org.junit.Test;


public class IgnoreTypeTest {
	@Test
	public void toEnumClass_Production() throws Exception {
		assertThat(IgnoreType.toEnum(IgnoreProductionClass.class), hasItem(IgnoreType.PRODUCTION));
	}

	@IgnoreProduction
	private static class IgnoreProductionClass{

	}

	@Test
	public void toEnumClass_Development() throws Exception {
		assertThat(IgnoreType.toEnum(IgnoreDevelopmentClass.class), hasItem(IgnoreType.DEVELOPMENT));
	}

	@IgnoreDevelopment
	private static class IgnoreDevelopmentClass{

	}

	@Test
	public void toEnumClass_Server() throws Exception {
		assertThat(IgnoreType.toEnum(IgnoreServerClass.class), hasItem(IgnoreType.SERVER));
	}

	@IgnoreServer
	private static class IgnoreServerClass{

	}

	@Test
	public void toEnumClass_NotServer() throws Exception {
		assertThat(IgnoreType.toEnum(IgnoreNotServerClass.class), hasItem(IgnoreType.NOT_SERVER));
	}

	@IgnoreNotServer
	private static class IgnoreNotServerClass{

	}

	@Test
	public void toEnumClass_All() throws Exception {
		assertThat(IgnoreType.toEnum(IgnoreAllClass.class), hasItems(IgnoreType.PRODUCTION, IgnoreType.DEVELOPMENT, IgnoreType.SERVER, IgnoreType.NOT_SERVER));
	}

	@IgnoreProduction
	@IgnoreDevelopment
	@IgnoreServer
	@IgnoreNotServer
	private static class IgnoreAllClass{

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

		@IgnoreNotServer
		public void notServer(){
		}

		@IgnoreProduction
		@IgnoreDevelopment
		@IgnoreServer
		@IgnoreNotServer
		public void all(){
		}
	}

	@Test
	public void toEnumMethod_Production() throws Exception {
		Method method = IgnoreMethodClass.class.getMethod("production");
		assertThat(IgnoreType.toEnum(method), hasItem(IgnoreType.PRODUCTION));
	}

	@Test
	public void toEnumMethod_Development() throws Exception {
		Method method = IgnoreMethodClass.class.getMethod("development");
		assertThat(IgnoreType.toEnum(method), hasItem(IgnoreType.DEVELOPMENT));
	}

	@Test
	public void toEnumMethod_Server() throws Exception {
		Method method = IgnoreMethodClass.class.getMethod("server");
		assertThat(IgnoreType.toEnum(method), hasItem(IgnoreType.SERVER));
	}

	@Test
	public void toEnumMethod_NotServer() throws Exception {
		Method method = IgnoreMethodClass.class.getMethod("notServer");
		assertThat(IgnoreType.toEnum(method), hasItem(IgnoreType.NOT_SERVER));
	}

	@Test
	public void toEnumMethod_All() throws Exception {
		Method method = IgnoreMethodClass.class.getMethod("all");
		assertThat(IgnoreType.toEnum(method), hasItems(IgnoreType.PRODUCTION, IgnoreType.DEVELOPMENT, IgnoreType.SERVER, IgnoreType.NOT_SERVER));
	}
}
