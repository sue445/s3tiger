package net.sue445.s3tiger;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sue445.s3tiger.internal.AppEngineWebConfigUtil;
import net.sue445.s3tiger.internal.IgnoreType;

import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.slim3.util.AppEngineUtil;

public class Slim3 extends BlockJUnit4ClassRunner {
	private static final Logger log = Logger.getLogger(Slim3.class.getName());

	private final boolean isClassIgnore;


	/**
	 *
	 * @param klass
	 * @throws InitializationError
	 */
	public Slim3(Class<?> klass) throws InitializationError {
		super(klass);
		this.isClassIgnore = isClassIgnore();
	}

	/**
	 *
	 * @return
	 */
	private boolean isClassIgnore(){
		Class<?> javaClass = super.getTestClass().getJavaClass();
		List<IgnoreType> ignoreTypes = IgnoreType.toEnum(javaClass);
		return hasCurrentEnvironment(ignoreTypes);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
		if(isClassIgnore || isMethodIgnore(method)){
			EachTestNotifier eachNotifier= makeNotifier(method, notifier);
			eachNotifier.fireTestStarted();
			eachNotifier.fireTestFinished();
			log.log(Level.FINE, "ignored");
			return;
		}

		Properties beforeSystemProperties = null;

		if(!AppEngineUtil.isServer()){
			beforeSystemProperties = (Properties) System.getProperties().clone();
			System.getProperties().putAll(AppEngineWebConfigUtil.getSystemProperties());
		}

		try{
			super.runChild(method, notifier);

		} finally {
			if(beforeSystemProperties != null){
				System.getProperties().clear();
				System.getProperties().putAll(beforeSystemProperties);
			}
		}
	}

	/**
	 *
	 * @param method
	 * @return
	 */
	private boolean isMethodIgnore(FrameworkMethod method){
		List<IgnoreType> ignoreTypes = IgnoreType.toEnum(method.getMethod());
		return hasCurrentEnvironment(ignoreTypes);
	}

	/**
	 *
	 * @param method
	 * @param notifier
	 * @return
	 */
	private EachTestNotifier makeNotifier(FrameworkMethod method, RunNotifier notifier) {
		Description description= describeChild(method);
		return new EachTestNotifier(notifier, description);
	}

	/**
	 *
	 * @param ignoreTypes
	 * @return
	 */
	protected static boolean hasCurrentEnvironment(List<IgnoreType> ignoreTypes){
		for(IgnoreType ignore : ignoreTypes){
			if(ignore.isCurrentEnvironment()){
				return true;
			}
		}
		return false;
	}

}
