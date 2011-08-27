package net.sue445.s3tiger.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sue445.s3tiger.Slim3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.datastore.Key;

@RunWith(Slim3.class)
public class CipherModelTest extends AppEngineTestCase {

	private CipherModel model = new CipherModel();

	@Test
	public void test() throws Exception {
		assertThat(model, is(notNullValue()));
	}

	@Test
	public void putAndGet() throws Exception {
		Key key = Datastore.createKey(CipherModel.class, "id");
		model.setKey(key);
		model.setCipherString("aaa");
		Datastore.put(model);

		CipherModel model2 = Datastore.get(CipherModel.class, key);
		assertThat(model2, is(model));
	}
}
