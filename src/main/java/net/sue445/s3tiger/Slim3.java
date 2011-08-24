package net.sue445.s3tiger;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sue445.s3tiger.inner.IgnoreType;

import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class Slim3 extends BlockJUnit4ClassRunner {
	private static final Logger log = Logger.getLogger(Slim3.class.getName());

	private final boolean isClassIgnore;


	public Slim3(Class<?> klass) throws InitializationError {
		super(klass);
		this.isClassIgnore = isClassIgnore();
	}

	private boolean isClassIgnore(){
		Class<?> javaClass = super.getTestClass().getJavaClass();
		IgnoreType ignoreType = IgnoreType.toEnum(javaClass);
		return ignoreType.isIgnore();
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

		super.runChild(method, notifier);
	}

	private boolean isMethodIgnore(FrameworkMethod method){
		IgnoreType ignoreType = IgnoreType.toEnum(method.getMethod());
		return ignoreType.isIgnore();
	}

	private EachTestNotifier makeNotifier(FrameworkMethod method, RunNotifier notifier) {
		Description description= describeChild(method);
		return new EachTestNotifier(notifier, description);
	}


}
