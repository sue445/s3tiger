package net.sue445.s3tiger.inner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sue445.s3tiger.IgnoreDevelopment;
import net.sue445.s3tiger.IgnoreNotServer;
import net.sue445.s3tiger.IgnoreProduction;
import net.sue445.s3tiger.IgnoreServer;

import org.slim3.util.AppEngineUtil;

public enum IgnoreType {
	PRODUCTION(Strategy.PRODUCTION),
	DEVELOPMENT(Strategy.DEVELOPMENT),
	SERVER(Strategy.SERVER),
	NOT_SERVER(Strategy.NOT_SERVER),
	;

	private final Strategy strategy;

	private IgnoreType(Strategy strategy){
		this.strategy = strategy;
	}

	public boolean isIgnore() {
		return strategy.isIgnore();
	}

	public boolean isCurrentEnvironment() {
		return strategy.isCurrentEnvironment();
	}

	private enum Strategy{
		PRODUCTION{
			@Override
			boolean isCurrentEnvironment() {
				return AppEngineUtil.isProduction();
			}
		},
		DEVELOPMENT{
			@Override
			boolean isCurrentEnvironment() {
				return AppEngineUtil.isDevelopment();
			}
		},
		SERVER{
			@Override
			boolean isCurrentEnvironment() {
				return AppEngineUtil.isServer();
			}
		},
		NOT_SERVER{
			@Override
			boolean isCurrentEnvironment() {
				return !AppEngineUtil.isServer();
			}
		},
		;

		abstract boolean isCurrentEnvironment();

		private boolean isIgnore(){
			return isCurrentEnvironment();
		}
	}

	/**
	 * get {@link IgnoreType}s from class
	 * @param clazz
	 * @return
	 */
	public static List<IgnoreType> toEnum(Class<?> clazz){
		List<IgnoreType> result = new ArrayList<IgnoreType>();

		if(clazz.getAnnotation(IgnoreProduction.class) != null){
			result.add(PRODUCTION);
		}
		if(clazz.getAnnotation(IgnoreDevelopment.class) != null){
			result.add(DEVELOPMENT);
		}
		if(clazz.getAnnotation(IgnoreServer.class) != null){
			result.add(SERVER);
		}
		if(clazz.getAnnotation(IgnoreNotServer.class) != null){
			result.add(NOT_SERVER);
		}

		return result;
	}

	/**
	 * get {@link IgnoreType}s from method
	 * @param method
	 * @return
	 */
	public static List<IgnoreType> toEnum(Method method){
		List<IgnoreType> result = new ArrayList<IgnoreType>();

		if(method.getAnnotation(IgnoreProduction.class) != null){
			result.add(PRODUCTION);
		}
		if(method.getAnnotation(IgnoreDevelopment.class) != null){
			result.add(DEVELOPMENT);
		}
		if(method.getAnnotation(IgnoreServer.class) != null){
			result.add(SERVER);
		}
		if(method.getAnnotation(IgnoreNotServer.class) != null){
			result.add(NOT_SERVER);
		}

		return result;
	}
}
