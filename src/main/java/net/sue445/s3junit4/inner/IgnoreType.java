package net.sue445.s3junit4.inner;

import java.lang.reflect.Method;

import net.sue445.s3junit4.IgnoreDevelopment;
import net.sue445.s3junit4.IgnoreJUnit;
import net.sue445.s3junit4.IgnoreProduction;
import net.sue445.s3junit4.IgnoreServer;

import org.slim3.util.AppEngineUtil;

public enum IgnoreType {
	PRODUCTION(Strategy.PRODUCTION),
	DEVELOPMENT(Strategy.DEVELOPMENT),
	SERVER(Strategy.SERVER),
	JUNIT(Strategy.JUNIT),
	NOT_IGNORE(Strategy.NOT_IGNORE),
	;

	private final Strategy strategy;

	private IgnoreType(Strategy strategy){
		this.strategy = strategy;
	}

	public boolean isIgnore() {
		return strategy.isIgnore();
	}

	private enum Strategy{
		PRODUCTION{
			@Override
			boolean isIgnore() {
				return AppEngineUtil.isProduction();
			}
		},
		DEVELOPMENT{
			@Override
			boolean isIgnore() {
				return AppEngineUtil.isDevelopment();
			}
		},
		SERVER{
			@Override
			boolean isIgnore() {
				return AppEngineUtil.isServer();
			}
		},
		JUNIT{
			@Override
			boolean isIgnore() {
				return !AppEngineUtil.isServer();
			}
		},
		NOT_IGNORE{
			@Override
			boolean isIgnore() {
				return false;
			}
		},
		;

		abstract boolean isIgnore();
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
		if(clazz.getAnnotation(IgnoreJUnit.class) != null){
			return JUNIT;
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
		if(method.getAnnotation(IgnoreJUnit.class) != null){
			return JUNIT;
		}
		return NOT_IGNORE;
	}
}
