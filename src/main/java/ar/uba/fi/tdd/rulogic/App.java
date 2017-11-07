package ar.uba.fi.tdd.rulogic;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;
import ar.uba.fi.tdd.rulogic.model.KnowledgeBaseTest;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) throws IOException {
	
		KnowledgeBase knowb = new KnowledgeBase("src/main/resources/rules.db");
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String line = buffer.readLine();
		
		while (!line.equals("#")) {
			System.out.println(knowb.answer(line));
		}
    }
}
