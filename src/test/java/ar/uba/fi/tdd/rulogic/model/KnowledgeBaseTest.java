package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KnowledgeBaseTest {

	//@InjectMocks
    KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		knowledgeBase = new KnowledgeBase("src/main/resources/rules.db");
	}

	@Test
	public void testFailAnswerFact() throws IOException {
		Assert.assertFalse(this.knowledgeBase.answer("varon (luis)."));		
		Assert.assertFalse(this.knowledgeBase.answer("mujer (miriam)."));
	}
	
	@Test
	public void testCorrectAnswerFact() throws IOException {
		Assert.assertTrue(this.knowledgeBase.answer("mujer (cecilia)."));
		Assert.assertTrue(this.knowledgeBase.answer("varon (nicolas)."));
	}
	
	@Test
	public void testFailAnswerRule() throws IOException {
		Assert.assertFalse(this.knowledgeBase.answer("padre(mario, je)."));		
		Assert.assertFalse(this.knowledgeBase.answer("hija(maria, roberto)."));
	}

	@Test
	public void testCorrectAnswerRule() throws IOException {
		Assert.assertTrue(this.knowledgeBase.answer("padre(juan, pepe)."));
		Assert.assertTrue(this.knowledgeBase.answer("hijo(pepe, juan)."));
		Assert.assertTrue(this.knowledgeBase.answer("tio(nicolas, alejandro, roberto)."));
	}

}
