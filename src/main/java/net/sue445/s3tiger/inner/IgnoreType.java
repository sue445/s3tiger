package net.sue445.s3tiger.inner;

import java.lang.reflect.Method;

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
	NOT_IGNORE(Strategy.NOT_IGNORE),
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
		NOT_IGNORE{
			@Override
			boolean isCurrentEnvironment() {
				return false;
			}
		},
		;

		abstract boolean isCurrentEnvironment();
		private boolean isIgnore(){
			return isCurrentEnvironment();
		}
	}

	/**
	 * get {@link IgnoreType} from class
	 * @param clazz
	 * @return a IgnoreType. if no ignore annotation, return {@link IgnoreType#NOT_IGNORE}.
	 */
	public static IgnoreType toEnum(Class<?> clazz){
		if(clazz.getAnnotation(IgnoreProduction.class) != null){
			return PRODUCTION;
		}
		if(clazz.getAnnotation(IgnoreDevelopment.class) != null){
			return DEVELOPMENT;
		}
		if(clazz.getAnnotation(IgnoreServer.class) != null){
			return SERVER;
		}
		if(clazz.getAnnotation(IgnoreNotServer.class) != null){
			return NOT_SERVER;
		}
		return NOT_IGNORE;
	}

	/**
	 * get {@link IgnoreType} from method
	 * @param method
	 * @return a IgnoreType. if no ignore annotation, return {@link IgnoreType#NOT_IGNORE}.
	 */
	public static IgnoreType toEnum(Method method){
		if(method.getAnnotation(IgnoreProduction.class) != null){
			return PRODUCTION;
		}
		if(method.getAnnotation(IgnoreDevelopment.class) != null){
			return DEVELOPMENT;
		}
		if(method.getAnnotation(IgnoreServer.class) != null){
			return SERVER;
		}
		if(method.getAnnotation(IgnoreNotServer.class) != null){
			return NOT_SERVER;
		}
		return NOT_IGNORE;
	}
}
